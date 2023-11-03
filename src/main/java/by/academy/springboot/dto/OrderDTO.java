package by.academy.springboot.dto;

import lombok.Data;

@Data
public class OrderDTO {
    double amount;
    int fromAccountId;
    String toAccountNumber;

}
