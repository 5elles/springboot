package by.academy.springboot.service.impl;

import by.academy.springboot.model.entity.Person;
import by.academy.springboot.model.repository.PersonRepository;
import by.academy.springboot.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private  final PersonRepository personRepository;
//    @Override
//    public boolean registerNewSystemUser(String login, String password, String citizenId) throws IllegalArgumentException {
//        Person person = personRepository.findByCitizenIdNumber(citizenId);
//        if (person == null){
//            throw new IllegalArgumentException("illegal argument: " + citizenId);
//        }
//        person.setLogin(login);
//        person.setPassword(password);
//        personRepository.save(person);
//        return true;
//    }
}
