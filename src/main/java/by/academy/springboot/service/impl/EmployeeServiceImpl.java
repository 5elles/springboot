package by.academy.springboot.service.impl;

import by.academy.springboot.dto.EmployeeFullDataDTO;
import by.academy.springboot.dto.EmployeeDTO;
import by.academy.springboot.mapper.EmployeeFullDataMapper;
import by.academy.springboot.mapper.EmployeeMapper;
import by.academy.springboot.model.entity.Employee;
import by.academy.springboot.model.repository.ContactRepository;
import by.academy.springboot.model.repository.EmployeeRepository;
import by.academy.springboot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ContactRepository contactRepository;


    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> list = employeeRepository.findAll();
        list.sort(Comparator.comparing(o -> o.getPerson().getLastName()));
        return list.stream()
                .map(EmployeeMapper.INSTANCE::toDTO)
                .toList();
    }

    @Override
    public EmployeeDTO findById(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null){
            return null;
        } else {
            return EmployeeMapper.INSTANCE.toDTO(employee);
        }
    }

    @Override
    public EmployeeFullDataDTO getEmployeeWithContacts(int employeeId)
            throws IllegalArgumentException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElse(null);
        if (employee == null) {
            return null;
        } else {
        return EmployeeFullDataMapper.INSTANCE.toDTO(
                employee,
                contactRepository.findByPerson(employee.getPerson()));}
    }
}
