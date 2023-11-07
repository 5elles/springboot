package by.academy.springboot.service.impl;

import by.academy.springboot.dto.*;
import by.academy.springboot.mapper.*;
import by.academy.springboot.model.entity.*;
import by.academy.springboot.model.repository.*;
import by.academy.springboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;
    private final PaymentOrderRepository paymentOrderRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ContactRepository contactRepository;
    private final CurrencyRepository currencyRepository;
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final SettlementTypeRepository settlementTypeRepository;
    private final SettlementRepository settlementRepository;
    private final StreeetTypeRepository streeetTypeRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final EmailRepository emailRepository;

    @Override
    public List<CustomerDTO> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        customers.sort(Comparator.comparing(o -> o.getPerson().getLastName()));
        return CustomerListMapper.INSTANCE.toDTOList(customers);
    }


    @Override
    public CustomerFullDataDTO findFullData(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return null;
        }
        Contact contact = contactRepository.findByPerson(customer.getPerson());
        List<Currency> currencies = currencyRepository.findAllByCurrencyAbbreviationIsNot("BYN");
        return CustomerFullDataMapper.INSTANCE.modelsToDTO(customer, contact, currencies);
    }

    @Override
    public List<PaymentOrderDTO> findAllOutgoingAndIncoming(String outgoingAccountNumber, String incomingAccountNumber) {
        List<PaymentOrder> orders = paymentOrderRepository
                .findDistinctByFromAccount_AccountNumberOrToAccount_AccountNumberOrderByTimeStamp(outgoingAccountNumber, incomingAccountNumber);
        return orders.stream()
                .map(PaymentOrderMapper.INSTANCE::toDTO)
                .toList();
    }

    @Override
    public PaymentOrderDTO findById(int id) {
        PaymentOrder order = paymentOrderRepository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }
        return PaymentOrderMapper.INSTANCE.toDTO(order);
    }

    @Override
    public List<PaymentOrderDTO> findByFromAccountNumber(String accountNumber) {
        List<PaymentOrder> order = paymentOrderRepository.findPaymentOrdersByFromAccount_AccountNumberOrderByTimeStamp(accountNumber);
        return order.stream()
                .map(PaymentOrderMapper.INSTANCE::toDTO)
                .toList();
    }

    @Override
    public List<PaymentOrderDTO> findByToAccountNumber(String accountNumber) {
        List<PaymentOrder> orders = paymentOrderRepository.findPaymentOrdersByToAccount_AccountNumberOrderByTimeStamp(accountNumber);
        return orders.stream()
                .map(PaymentOrderMapper.INSTANCE::toDTO)
                .toList();
    }


    @Override
    public BankAccountFullDataDTO findBankAccountFullData(int bankAccountId) {
        BankAccount account = bankAccountRepository.findById(bankAccountId).orElse(null);
        if (account == null) {
            return null;
        }
        String accountNumber = account.getAccountNumber();
        List<PaymentOrderDTO> allPayments = findAllOutgoingAndIncoming(accountNumber, accountNumber);
        List<PaymentOrderDTO> outgoingPayments = findByFromAccountNumber(accountNumber);
        List<PaymentOrderDTO> incomingPayments = findByToAccountNumber(accountNumber);
        return BankAccountFullDataMapper.INSTANCE.modelsToDTO(
                account,
                allPayments,
                outgoingPayments,
                incomingPayments
        );
    }

    @Override
    public List<PaymentOrderDTO> findAllPaymentOrders() {
        List<PaymentOrder> orders = paymentOrderRepository.findAll();
        orders.sort(Comparator.comparing(PaymentOrder::getTimeStamp));
        return PaymentOrderListMapper.INSTANCE.toDTOList(orders);
    }

    /**
     * @return new person`s id or -1
     */
    @Override
    @Transactional
    public int save(PersonDTO dto) {
        if (dto == null
                || personRepository.findByCitizenIdNumber(dto.getCitizenIdNumber()) != null) {
            return -1;
        }
        Person model = PersonMapper.INSTANCE.toModel(dto);
        return personRepository.save(model).getId();
    }

    @Override
    public AddressFullDataDTO findFullData() {
        List<Country> countries = countryRepository.findAll();
        List<Region> regions = regionRepository.findAll();
        List<SettlementType> settlementTypes = settlementTypeRepository.findAll();
        List<Settlement> settlements = settlementRepository.findAll();
        List<StreetType> streetTypes = streeetTypeRepository.findAll();
        return AddressFullDataMapper.INSTANCE.toDTO(countries, regions, settlementTypes, settlements, streetTypes);
    }

    @Override
    public List<PersonDTO> findByLastNameAndFirstNameAndMiddleName(String lastName,
                                                                   String firstName,
                                                                   String middleName
    ) {
        List<Person> personList = personRepository.findAllByLastNameIgnoreCaseAndFirstNameIgnoreCaseAndMiddleNameIgnoreCaseOrderByLastName(
                lastName,
                firstName,
                middleName
        );
        List<PersonDTO> dtoList = PersonListMapper.INSTANCE.toDTO(personList);
        return dtoList;
    }

    @Transactional
    @Override
    public boolean closeAccount(int accountId) {
        BankAccount account = bankAccountRepository.findById(accountId).orElse(null);
        if (
                account == null
                        || account.getCurrentBalance() != 0
        ) {
            return false;
        }
        account.setClosureDate(LocalDate.now());
        return true;
    }

    @Override
    @Transactional
    public boolean terminateContract(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null
                || customer.getAgreementDate() == null
                || hasActiveBankAccounts(customerId)
        ) {
            return false;
        }
        customer.setClosureDate(LocalDate.now());
        return true;
    }

    @Override
    public boolean hasActiveBankAccounts(int customerID) {
        List<BankAccount> bankAccounts = bankAccountRepository.findBankAccountsByCustomerId(customerID);
        for (BankAccount account : bankAccounts) {
            if (account.getClosureDate() != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional
    public boolean createNewBankContract(CustomerDTO dto) {
        Customer customer = customerRepository.findById(dto.getId()).orElse(null);
        if (customer == null
                || customer.getClosureDate() == null
        ) {
            return false;
        }
        customer.setAgreementNumber(dto.getAgreementNumber());
        customer.setAgreementDate(dto.getAgreementDate());
        customer.setClosureDate(null);
        return true;
    }

    @Override
    public List<CurrencyDTO> findAllCurrencies() {
        List<Currency> currencies = currencyRepository.findAll();
        currencies.sort(Comparator.comparing(Currency::getCurrencyName));
        return CurrencyListMapper.INSTANCE.toDTO(currencies);
    }

    @Transactional
    @Override
    public boolean createNewBankAccount(BankAccountDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerID()).orElse(null);
        if (customer == null
                || customer.getClosureDate() != null
                || dto.getOpeningDate() == null
                || dto.getAccountNumber() == null
                || dto.getCurrencyID() == null
                || dto.getCustomerID() == null) {
            return false;
        }
        bankAccountRepository.save(BankAccountMapper.INSTANCE.dtoToModel(dto));
        return true;
    }

    /**
     * @return new customer`s id or -1
     */
    @Override
    @Transactional
    public Integer createCustomer(CustomerDTO dto) {
        Person person = personRepository.findById(dto.getPersonId()).orElse(null);
        Customer customer = customerRepository.findCustomerByPerson(person);
        if (person == null
                || customer != null) {
            return -1;
        }
        return customerRepository.save(CustomerMapper.INSTANCE.toModel(dto)).getId();
    }

    /**
     * @return the id-number of the person or -1
     */
    @Override
    public Integer findPersonIdByCustomerId(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null){
            return -1;
        }
        return customer.getPerson().getId();
    }

    @Override
    @Transactional
    public boolean createNewPhoneNumber(PhoneNumberDTO dto) {
        if (dto == null
        || dto.getPersonId() == null){
            return false;
        }
        Person person = personRepository.findById(dto.getPersonId()).orElse(null);
        Contact contact = contactRepository.findByPerson(person);
        if (contact == null){
            Contact newContact = new Contact();
            newContact.setPerson(person);
            contactRepository.save(newContact);
            contact = contactRepository.findByPerson(person);
        }
        dto.setContactId(contact.getId());
        phoneNumberRepository.save(PhoneNumberMapper.INSTANCE.toModel(dto));
        return true;
    }

    @Override
    public boolean createNewEmail(EmailDTO dto) {
        if (dto == null
                || dto.getPersonId() == null){
            return false;
        }
        Person person = personRepository.findById(dto.getPersonId()).orElse(null);
        Contact contact = contactRepository.findByPerson(person);
        if (contact == null){
            Contact newContact = new Contact();
            newContact.setPerson(person);
            contactRepository.save(newContact);
            contact = contactRepository.findByPerson(person);
        }
        dto.setContactId(contact.getId());
        emailRepository.save(EmailMapper.INSTANCE.toModel(dto));
        return true;
    }
}
