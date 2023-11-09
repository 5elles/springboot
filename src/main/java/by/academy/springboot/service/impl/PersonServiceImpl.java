package by.academy.springboot.service.impl;

import by.academy.springboot.dto.AddressFullDataDTO;
import by.academy.springboot.dto.EmailDTO;
import by.academy.springboot.dto.PersonDTO;
import by.academy.springboot.dto.PhoneNumberDTO;
import by.academy.springboot.mapper.*;
import by.academy.springboot.model.entity.*;
import by.academy.springboot.model.repository.*;
import by.academy.springboot.service.PersonService;
import lombok.RequiredArgsConstructor;
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
    private final StreeetTypeRepository streeetTypeRepository;
    private final ContactRepository contactRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final EmailRepository emailRepository;

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

    @Override
    public AddressFullDataDTO findAddressesFullData() {
        List<Country> countries = countryRepository.findAll();
        List<Region> regions = regionRepository.findAll();
        List<SettlementType> settlementTypes = settlementTypeRepository.findAll();
        List<Settlement> settlements = settlementRepository.findAll();
        List<StreetType> streetTypes = streeetTypeRepository.findAll();
        return AddressFullDataMapper.INSTANCE.toDTO(countries, regions, settlementTypes, settlements, streetTypes);
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
