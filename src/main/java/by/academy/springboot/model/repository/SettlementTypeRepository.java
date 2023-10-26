package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.SettlementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementTypeRepository extends JpaRepository<SettlementType, Integer> {
}
