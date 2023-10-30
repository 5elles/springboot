package by.academy.springboot.service;

import by.academy.springboot.dto.EmployeeFullDataDTO;
import by.academy.springboot.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService  {
    List<EmployeeDTO> findAll();
    EmployeeDTO findById(int id);
    EmployeeFullDataDTO getEmployeeFullData(int employeeId);

}
