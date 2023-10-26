package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.WageRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WageRateRepository extends JpaRepository<WageRate, Integer> {
}
