package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.Position;
import by.academy.springboot.model.entity.WageRate;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    Position findPositionById(Integer positionId);
}
