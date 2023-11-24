package by.academy.springboot.service;

import by.academy.springboot.dto.*;
import by.academy.springboot.exception.ForbiddenActionException;
import by.academy.springboot.model.entity.Person;
import by.academy.springboot.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonService {
    @Transactional
    void createNewAddress(NewAddressDTO dto) throws ForbiddenActionException;

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
    PersonDTO findPersonDto(UserDetails userDetails);
}
