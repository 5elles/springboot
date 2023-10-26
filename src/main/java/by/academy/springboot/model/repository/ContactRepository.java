package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.Contact;
import by.academy.springboot.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Contact findContactByPerson(Person person);
}
