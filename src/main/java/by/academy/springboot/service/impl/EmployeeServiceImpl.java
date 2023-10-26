package by.academy.springboot.service.impl;

import by.academy.springboot.model.entity.Contact;
import by.academy.springboot.model.entity.Employee;
import by.academy.springboot.model.entity.Person;
import by.academy.springboot.model.entity.WageRate;
import by.academy.springboot.model.repository.ContactRepository;
import by.academy.springboot.model.repository.EmployeeRepository;
import by.academy.springboot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ContactRepository contactRepository;


    @Override
    public List<Employee> findAllSortedByLastName() {
        List<Employee> result = employeeRepository.findAll();
        result.sort(Comparator.comparing(o -> o.getPerson().getLastName()));
        return result;
    }

    @Override
    public Contact findContactByPerson(Person person) {
        return contactRepository.findContactByPerson(person);
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee findEmployeeByPersonId(int id) {
        return employeeRepository.findEmployeeByPersonId(id);
    }

    @Override
    public WageRate findTheFirstWageRateByEmployeesId(int id) {
        List<WageRate> wageRates = employeeRepository.findById(id).get().getWageRates();

        return null;
    }

}
