package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
