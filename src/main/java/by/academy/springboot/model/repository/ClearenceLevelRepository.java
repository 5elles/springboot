package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.ClearanceLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClearenceLevelRepository extends JpaRepository<ClearanceLevel, Integer> {
}
