package by.academy.springboot.service.impl;

import by.academy.springboot.model.entity.Employee;
import by.academy.springboot.model.repository.EmployeeRepository;
import by.academy.springboot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

//    public Employee get(Integer id){
//        return  employeeRepository.findById(id);
//    }



}
