package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {
    @Query("from PhoneNumber where phoneNumber = :phoneNumber")
    PhoneNumber find (String phoneNumber);
}
