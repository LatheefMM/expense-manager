package com.example.expense_manager.contract.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.time.LocalDate;

@Getter
@Setter

public class TransactionRequest {

    @NotBlank(message = "name cannot be empty")
    private String name;

    private Type type;
    private double amount;
    private LocalDate date;

}
