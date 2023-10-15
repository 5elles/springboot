package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.WageRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WageRateRepository extends JpaRepository<WageRate, Integer> {
}
