package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.Employee;
import by.academy.springboot.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByPerson(Person person);
    @Query("from Employee order by person.lastName")
    List<Employee> findAllEmployees();
}
