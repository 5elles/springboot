package by.academy.springboot.service;

import by.academy.springboot.model.entity.Contact;
import by.academy.springboot.model.entity.Employee;
import by.academy.springboot.model.entity.Person;
import by.academy.springboot.model.entity.WageRate;

import java.util.List;
import java.util.Locale;

public interface EmployeeService  {
    List<Employee> findAllSortedByLastName();
    Contact findContactByPerson(Person person);

    Employee findById(int id);
    Employee findEmployeeByPersonId(int id);

    WageRate findTheFirstWageRateByEmployeesId(int id);
}
