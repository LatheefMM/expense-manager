package com.example.expense_manager.contract.request.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginResponse {
    private Long userId;
    private String name;
}
