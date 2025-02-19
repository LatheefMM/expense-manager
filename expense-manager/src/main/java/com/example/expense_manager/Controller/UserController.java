package com.example.expense_manager.Controller;


import com.example.expense_manager.contract.request.LoginRequest;
import com.example.expense_manager.contract.request.SignupRequest;
import com.example.expense_manager.contract.request.response.LoginResponse;
import com.example.expense_manager.contract.request.response.SignupResponse;
import com.example.expense_manager.service.Userservice;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Entity
@RequiredArgsConstructor
@CrossOrigin(origins = "/http://localhost:3000/")
@RequestMapping("/v1/user/")

public class UserController {
    private  final Userservice userService;

    @PostMapping("/signup")
    public SignupResponse signup(@Valid @RequestBody SignupRequest request){
        return  userService.signup(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request){
        return userService.login(request);
    }
}
