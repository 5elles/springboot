package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Integer> {
}
