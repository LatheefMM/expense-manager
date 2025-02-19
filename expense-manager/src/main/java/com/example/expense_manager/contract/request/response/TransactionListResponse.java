package com.example.expense_manager.contract.request.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class TransactionListResponse {
    private List<TransactionResponse>tranactions;
    private  Long totaltransactions;
    private int currentPage;
    private int totalPages;


}
