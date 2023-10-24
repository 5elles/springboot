package by.academy.springboot.service;

import by.academy.springboot.model.entity.Employee;

import java.util.List;

public interface EmployeeService  {
    List<Employee> getAll();
    Employee getById(Integer id);
}
