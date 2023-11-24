package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.Person;
import by.academy.springboot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByCitizenIdNumber(String citizenId);
    List<Person> findAllByLastNameIgnoreCaseAndFirstNameIgnoreCaseAndMiddleNameIgnoreCaseOrderByLastName(String lastName, String firstName, String middleName);
    Person findByEmployeeId(Integer employeeId);
    @Query("from Person where user  = :user")
    Person findPerson(User user);
}
