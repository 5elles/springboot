package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    BankAccount findBankAccountByAccountNumber(String accountNumber);
    List<BankAccount> findBankAccountsByCustomerId(int customerId);
}
