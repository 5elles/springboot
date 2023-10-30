package by.academy.springboot.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_order")
public class PaymentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "date_and_time")
    private LocalDateTime timeStamp;
    @Column(name = "amount")
    private Double amount;
    @OneToOne
    @JoinColumn(name = "from_account_id")
    private BankAccount fromAccount;
    @OneToOne
    @JoinColumn(name = "to_account_id")
    private BankAccount toAccount;
}
