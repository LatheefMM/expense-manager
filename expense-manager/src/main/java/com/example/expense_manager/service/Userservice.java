package com.example.expense_manager.service;


import com.example.expense_manager.contract.request.LoginRequest;
import com.example.expense_manager.contract.request.SignupRequest;
import com.example.expense_manager.contract.request.response.LoginResponse;
import com.example.expense_manager.contract.request.response.SignupResponse;
import com.example.expense_manager.exception.InvalidUserException;
import com.example.expense_manager.model.User;
import com.example.expense_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class Userservice {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public SignupResponse signup(SignupRequest request) {
        if (UserRepository.existsByEmail(request.getEmail())) {
            throw new InvalidUserException("Signup", userid);
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .hashedpassword(passwordEncoder.encode(request.getPassword()))
                .build();
        user = userRepository.save(user);
        SignupResponse response = new SignupResponse();
        response.setUserId(user.getId());
        return response;
    }

    public LoginResponse login(LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        if (UserRepository.existsByemail(email)) {
            throw new InvalidUserException("Login", userid);
        }
        User user = UserRepository.findByEmail(request.getEmail()).orElseThrow();
        if (PasswordEncoder.matches(password, user.getHashedpassword())) {
            LoginResponse response = new LoginResponse();
            response.setUserId(user.getId());
            response.setName(user.getName());
            return response;
        }
        throw new InvalidUserException("Login", userid);
    }
}
