package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.StreetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetTypeRepository extends JpaRepository<StreetType, Integer> {
}
