package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.Customer;
import by.academy.springboot.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByPerson(Person person);
    @Query("from Customer order by person.lastName")
    List<Customer> findAllOrderByPersonLastName();
}
