package com.example.expense_manager.repository;

import com.example.expense_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    static boolean existsByEmail(String email) {
        return false;
    }

    static Optional<User> findByEmail(String email) {
        return null;
    }

    static boolean existsByemail(String email) {
        return false;
    }
}
