package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
