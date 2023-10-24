package by.academy.springboot.service;

import by.academy.springboot.model.entity.Customer;
import by.academy.springboot.model.entity.Employee;
import by.academy.springboot.model.entity.Person;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
    Customer getCustomerById(Integer id);
    Person getPersonById(Integer id);
    void saveCustomer(Customer customer);
    void deleteCustomerById(Integer id);
}
