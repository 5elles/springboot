package by.academy.springboot.service;

import by.academy.springboot.dto.EmployeeFullDataDTO;
import by.academy.springboot.dto.EmployeeDTO;
import by.academy.springboot.dto.PositionDTO;
import by.academy.springboot.dto.WageRateFullDataDTO;

import java.util.List;

public interface EmployeeService  {
    List<EmployeeDTO> findAll();
    EmployeeDTO findById(int id);
    EmployeeFullDataDTO findEmployeeFullData(int employeeId);
    List<PositionDTO> findAllPositions();
    void createNewWageRate(WageRateFullDataDTO dto, Integer personId);
    Integer findEmployeeIdByPersonId(Integer pid);
    Integer findPersonIdByEmployeeId(Integer employeeId);
    void closeWageRate(Integer wageRateId);
    List<PositionDTO> getAllPositions();
    List<EmployeeDTO> findAlLActualEmployeesByPositionId(Integer positionId);
    String findPositionNameById(Integer positionId);
}
