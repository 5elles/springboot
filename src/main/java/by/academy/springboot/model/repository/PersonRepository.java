package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByLastNameLikeIgnoreCaseOrderByLastName(String lastName);
}
