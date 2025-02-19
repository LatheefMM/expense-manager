package com.example.expense_manager.contract.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginRequest {

    @NotBlank(message = "email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;


}
