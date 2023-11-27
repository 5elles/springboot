package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {
    @Query("from Email where email = :email")
    Email find(String email);
}
