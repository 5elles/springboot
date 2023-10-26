package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.OperationsRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationsRegisterRepository extends JpaRepository<OperationsRegister, Integer> {
}
