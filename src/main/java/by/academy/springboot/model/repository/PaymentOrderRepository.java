package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Integer> {
    List<PaymentOrder> findDistinctByFromAccount_AccountNumberOrToAccount_AccountNumberOrderByTimeStamp(String accountNumber1, String accountNumber2);
    List<PaymentOrder> findPaymentOrdersByFromAccount_AccountNumberOrderByTimeStamp(String accountNumber);
    List<PaymentOrder> findPaymentOrdersByToAccount_AccountNumberOrderByTimeStamp(String accountNumber);
    @Query("from PaymentOrder order by timeStamp desc ")
    List<PaymentOrder> findAllOrders();
}
