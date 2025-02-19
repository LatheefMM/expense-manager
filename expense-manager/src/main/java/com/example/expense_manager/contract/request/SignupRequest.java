package com.example.expense_manager.contract.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SignupRequest {

    @NotBlank(message = "name cannot be empty")
    private String name;


    @NotBlank(message = "email cannot be empty")
    private String email;


    @NotBlank(message = "password cannot be empty")
    private String password;

}
