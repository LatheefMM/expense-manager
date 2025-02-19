package com.example.expense_manager.contract.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ListTransactionRequest {

    private int PageNumber;
    private int PageSize;
}
