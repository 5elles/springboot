package by.academy.springboot.service.impl;

import by.academy.springboot.dto.EmployeeDTO;
import by.academy.springboot.dto.EmployeeFullDataDTO;
import by.academy.springboot.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;

import by.academy.springboot.exception.IncorrectParameterException;
import by.academy.springboot.model.entity.*;
import by.academy.springboot.model.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    @InjectMocks
    EmployeeServiceImpl employeeService;
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    ContactRepository contactRepository;
    private Employee employee;
    private Person person;
    private Contact contact;

    @BeforeEach
    public void setup() {
        person = Person.builder()
                .id(1)
                .build();
        employee = Employee.builder()
                .id(1)
                .person(person)
                .build();
        contact = Contact.builder()
                .id(1)
                .person(person)
                .build();
    }

    @DisplayName("test for findAll method")
    @Test
    void givenEmployeeList_whenFindAll_thenReturnEmployeeList() {
        Employee employee1 = Employee.builder()
                .id(2)
                .build();
        given(employeeRepository.findAllEmployees()).willReturn(List.of(employee, employee1));
        assertThat(employeeService.findAll())
                .isNotNull()
                .hasSize(2);
    }

    @DisplayName("test for findById method")
    @Test
    void givenEmployeeId_whenFindById_thenReturnEmployeeDto() {
        given(employeeRepository.findById(employee.getId()))
                .willReturn(Optional.of(employee));
        EmployeeDTO byId = employeeService.findById(employee.getId());
        assertThat(byId).isEqualTo(EmployeeMapper.INSTANCE.toDTO(employee));
    }

    @DisplayName("test for findById method which throws IncorrectParameterException")
    @Test
    void givenEmployeeId_whenFindById_thenThrowsIncorrectParameterException() {
        given(employeeRepository.findById(5))
                .willThrow(new IncorrectParameterException("exception"));
        org.junit.jupiter.api.Assertions.assertThrows(IncorrectParameterException.class,
                () -> employeeService.findById(5));
    }

    @DisplayName("test for findEmployeeFullData method")
    @Test
    void givenEmployeeId_whenFindEmployeeFullData_thenReturnEmployeeFullDataDto() {
        given(employeeRepository.findById(employee.getId()))
                .willReturn(Optional.of(employee));
        given(contactRepository.findByPerson(person)).willReturn(contact);
        EmployeeFullDataDTO employeeFullData = employeeService.findEmployeeFullData(employee.getId());
        assertThat(employeeFullData)
                .isNotNull()
                .isInstanceOf(EmployeeFullDataDTO.class);
    }

    @DisplayName("test for findEmployeeFullData method which throws IncorrectParameterException")
    @Test
    void givenEmployeeId_whenFindEmployeeFullData_thenThrowIncorrectParameterException() {
        given(employeeRepository.findById(5))
                .willThrow(new IncorrectParameterException("exception"));
        org.junit.jupiter.api.Assertions.assertThrows(IncorrectParameterException.class,
                () -> employeeService.findEmployeeFullData(5));
    }
}