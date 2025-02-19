package com.example.expense_manager.contract.request.response;

import com.example.expense_manager.constant.Color;
import lombok.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TransactionResponse {
    private Long id;
    private  String name;
    private double amount;
    private Type type;
    private Color color;
    private LocalDate date;
    private Long user;
}
