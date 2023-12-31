package by.academy.springboot.service.impl;

import by.academy.springboot.dto.EmployeeFullDataDTO;
import by.academy.springboot.dto.EmployeeDTO;
import by.academy.springboot.dto.PositionDTO;
import by.academy.springboot.dto.WageRateFullDataDTO;
import by.academy.springboot.exception.ForbiddenActionException;
import by.academy.springboot.exception.IncorrectParameterException;
import by.academy.springboot.mapper.*;
import by.academy.springboot.model.entity.*;
import by.academy.springboot.model.entity.enums.Role;
import by.academy.springboot.model.repository.*;
import by.academy.springboot.service.EmployeeService;
import by.academy.springboot.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ContactRepository contactRepository;
    private final PositionRepository positionRepository;
    private final PersonRepository personRepository;
    private final WageRateRepository wageRateRepository;
    private final RegistrationService registrationService;
    private final UserRepository userRepository;


    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> list = employeeRepository.findAllEmployees();
        return EmployeeListMapper.INSTANCE.toDTO(list);
    }

    @Override
    public EmployeeDTO findById(int id) throws IncorrectParameterException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IncorrectParameterException("wrong employee id " + id));
            return EmployeeMapper.INSTANCE.toDTO(employee);
    }

    @Override
    public EmployeeFullDataDTO findEmployeeFullData(int employeeId)
            throws IncorrectParameterException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IncorrectParameterException("there is no such employee, id " + employeeId));
            return EmployeeFullDataMapper.INSTANCE.toDTO(
                    employee,
                    contactRepository.findByPerson(employee.getPerson()));
    }

    @Override
    public List<PositionDTO> findAllPositions() {
        List<Position> positions = positionRepository.findAll().stream()
                .sorted(Comparator.comparing(Position::getPositionName))
                .toList();
        return PositionListMapper.INSTANCE.toDTO(positions);
    }


    @Override
    @Transactional
    public void createNewWageRate(WageRateFullDataDTO dto, Integer personId)
            throws IncorrectParameterException {
        Person person = personRepository.findById(personId)
                .orElseThrow(()->new IncorrectParameterException("no such person, id " + personId));
        Position position = positionRepository.findPositionById(dto.getPositionId());
        if (position != null) {
            Employee employee = employeeRepository.findByPerson(person);
            if (employee == null) {
                employeeRepository.saveAndFlush(
                        Employee.builder()
                        .person(person)
                        .build());
                employee = employeeRepository.findByPerson(person);
            }
            dto.setEmployeeId(employee.getId());
            wageRateRepository.save(WageRateFullDataMapper.INSTANCE.toModel(dto));
        } else {
            throw new IncorrectParameterException("position can not be null");
        }
        if (person.getUser() == null){
            registrationService.createUser(
                    PersonMapper.INSTANCE.toDTO(person));
        }
        User user = userRepository.getUserByPersonId(personId);
        registrationService.addRole(
                UserMapper.INSTANCE.toDTO(user),
                Role.valueOf(positionRepository.findPositionById(dto.getPositionId()).getRole()));
    }

    /**
     * @return employee id or -1
     */
    @Override
    public Integer findEmployeeIdByPersonId(Integer pid) {
        Person person = personRepository.findById(pid).orElse(null);
        if (person != null) {
            return employeeRepository.findByPerson(person).getId();
        }
        return -1;
    }

    /**
     * @return person id or -1
     */
    @Override
    public Integer findPersonIdByEmployeeId(Integer employeeId){
        Person person = personRepository.findByEmployeeId(employeeId);
        if (person != null){
            return person.getId();
        }
        return -1;
    }

    @Override
    public void closeWageRate(Integer wageRateId) throws ForbiddenActionException{
        WageRate wageRate = wageRateRepository.findById(wageRateId).orElse(null);
        if (wageRate != null){
            wageRate.setFinishDate(LocalDate.now());
            wageRateRepository.save(wageRate);
            registrationService.deleteRole(
                    UserMapper.INSTANCE.toDTO(wageRate.getEmployee().getPerson().getUser()),
                    Role.valueOf(wageRate.getPosition().getRole()));
        } else throw new ForbiddenActionException("wage rate must be not null");
    }
    @Override
    public List<PositionDTO> getAllPositions(){
        List<Position> positions = positionRepository.findAll();
        if (!positions.isEmpty()){
            positions.sort(Comparator.comparing(Position::getPositionName));
        }
        return PositionListMapper.INSTANCE.toDTO(positions);
    }

    @Override
    public List<EmployeeDTO> findAlLActualEmployeesByPositionId(Integer positionId) {
        List<WageRate> wageRates = wageRateRepository.findActualWageRatesPositionId(positionId);
        List<Employee> employees = employeeRepository.findAllById(new HashSet<>(wageRates.stream()
                .map(wageRate -> wageRate
                        .getEmployee()
                        .getId())
                .toList()));
        List<EmployeeDTO> dtos = EmployeeListMapper.INSTANCE.toDTO(employees);
        dtos.sort(Comparator.comparing(EmployeeDTO::getLastName));
        return dtos;
    }

    @Override
    public String findPositionNameById(Integer positionId) {
        Position position = positionRepository.findPositionById(positionId);
        if (position != null){
            return position.getPositionName();
        }
        return null;
    }
}
