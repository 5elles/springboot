package by.academy.springboot.service.impl;

import by.academy.springboot.model.entity.Customer;
import by.academy.springboot.model.entity.Person;
import by.academy.springboot.model.repository.CustomerRepository;
import by.academy.springboot.model.repository.PersonRepository;
import by.academy.springboot.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Person getPersonById(Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomerById(Integer id) {

    }
}
