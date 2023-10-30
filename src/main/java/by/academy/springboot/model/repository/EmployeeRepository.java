package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.Employee;
import by.academy.springboot.model.entity.Person;
import by.academy.springboot.model.entity.WageRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
