package by.academy.springboot.service.impl;

import by.academy.springboot.dto.*;
import by.academy.springboot.exception.ForbiddenActionException;
import by.academy.springboot.exception.IncorrectParameterException;
import by.academy.springboot.mapper.*;
import by.academy.springboot.model.entity.*;
import by.academy.springboot.model.repository.*;
import by.academy.springboot.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final SettlementRepository settlementRepository;
    private final SettlementTypeRepository settlementTypeRepository;
    private final StreetTypeRepository streetTypeRepository;
    private final ContactRepository contactRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final EmailRepository emailRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    /**
     * @return new person`s id
     */
    @Override
    @Transactional
    public int save(PersonDTO dto) throws ForbiddenActionException {
        if (isForbiddenForCreation(dto)) {
            throw new ForbiddenActionException("dto is null or person with such citizen ID exists");
        }
        Person model = PersonMapper.INSTANCE.toModel(dto);
        return personRepository.save(model).getId();
    }

    public boolean isForbiddenForCreation(PersonDTO dto) {
        return dto == null || personRepository.findByCitizenIdNumber(dto.getCitizenIdNumber()) != null;
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
        return PersonListMapper.INSTANCE.toDTO(personList);
    }

    @Override
    public AddressFullDataDTO findAddressesFullData() {
        List<Country> countries = countryRepository.findAll();
        List<Region> regions = regionRepository.findAll();
        List<SettlementType> settlementTypes = settlementTypeRepository.findAll();
        List<Settlement> settlements = settlementRepository.findAll();
        List<StreetType> streetTypes = streetTypeRepository.findAll();
        return AddressFullDataMapper.INSTANCE.toDTO(countries, regions, settlementTypes, settlements, streetTypes);
    }

    @Override
    @Transactional
    public void createNewPhoneNumber(PhoneNumberDTO dto) throws ForbiddenActionException, IncorrectParameterException {
        if (isForbiddenForCreation(dto)) {
            throw new ForbiddenActionException("Something wrong with the phone number. It has not been saved.");
        }
        Person person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new IncorrectParameterException("no such person, id " + dto.getPersonId()));
        Contact contact = contactRepository.findByPerson(person);
        if (contact == null) {
            createNewContact(person);
            contact = contactRepository.findByPerson(person);
        }
        dto.setContactId(contact.getId());
        phoneNumberRepository.save(PhoneNumberMapper.INSTANCE.toModel(dto));
    }

    @Override
    public void createNewContact(Person person) throws ForbiddenActionException {
        Contact contact = contactRepository.findByPerson(person);
        if (contact == null) {
            Contact newContact = new Contact();
            newContact.setPerson(person);
            contactRepository.save(newContact);
        } else {
            throw new ForbiddenActionException("contact exists");
        }
    }

    @Transactional
    @Override
    public void createNewEmail(EmailDTO dto) throws ForbiddenActionException, IncorrectParameterException {
        if (isForbiddenForCreation(dto)) {
            throw new ForbiddenActionException("Something wrong with the email number. It has not been saved.");
        }
        Person person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new IncorrectParameterException("person can not be null!"));
        Contact contact = contactRepository.findByPerson(person);
        if (contact == null) {
            createNewContact(person);
            contact = contactRepository.findByPerson(person);
        }
        dto.setContactId(contact.getId());
        emailRepository.save(EmailMapper.INSTANCE.toModel(dto));
    }

    @Transactional
    @Override
    public void createNewAddress(NewAddressDTO dto) throws ForbiddenActionException {
        Address model = NewAddressMapper.INSTANCE.toModel(dto);
        if (model == null) {
            throw new ForbiddenActionException("Forbidden action! Check your address.");
        }
        addressRepository.save(model);
    }

    @Override
    public boolean isForbiddenForCreation(EmailDTO dto) {
        return dto == null ||
                dto.getPersonId() == null ||
                emailRepository.find(dto.getEmail()) != null;
    }

    @Override
    public boolean isForbiddenForCreation(PhoneNumberDTO dto) {
        return dto == null ||
                dto.getPersonId() == null ||
                phoneNumberRepository.find(dto.getPhoneNumber()) != null;
    }

    @Override
    public PersonDTO findPersonDto(UserDetails userDetails) throws IncorrectParameterException {
        User user = userRepository.getUserByUsername(userDetails.getUsername());
        Person person = user.getPerson();
        if (person == null) {
            throw new IncorrectParameterException("No such person. Check the username " + userDetails.getUsername());
        }
        return PersonMapper.INSTANCE.toDTO(person);
    }
}
