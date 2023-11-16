package by.academy.springboot.service;

import by.academy.springboot.dto.AddressFullDataDTO;
import by.academy.springboot.dto.EmailDTO;
import by.academy.springboot.dto.PersonDTO;
import by.academy.springboot.dto.PhoneNumberDTO;
import by.academy.springboot.model.entity.Person;

import java.util.List;

public interface PersonService {
    boolean isForbiddenForCreation(EmailDTO dto);
    boolean isForbiddenForCreation(PhoneNumberDTO dto);
    int save(PersonDTO dto);
    List<PersonDTO> findByLastNameAndFirstNameAndMiddleName(String lastName,
                                                            String firstName,
                                                            String middleName
    );
    AddressFullDataDTO findAddressesFullData();

    void createNewPhoneNumber(PhoneNumberDTO dto);
    void createNewContact(Person person);
    void createNewEmail(EmailDTO dto);
}
