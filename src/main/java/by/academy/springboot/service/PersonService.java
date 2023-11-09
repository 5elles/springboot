package by.academy.springboot.service;

import by.academy.springboot.dto.AddressFullDataDTO;
import by.academy.springboot.dto.EmailDTO;
import by.academy.springboot.dto.PersonDTO;
import by.academy.springboot.dto.PhoneNumberDTO;

import java.util.List;

public interface PersonService {
    int save(PersonDTO dto);
    List<PersonDTO> findByLastNameAndFirstNameAndMiddleName(String lastName,
                                                            String firstName,
                                                            String middleName
    );
    AddressFullDataDTO findAddressesFullData();

    boolean createNewPhoneNumber(PhoneNumberDTO dto);
    boolean createNewEmail(EmailDTO dto);
}
