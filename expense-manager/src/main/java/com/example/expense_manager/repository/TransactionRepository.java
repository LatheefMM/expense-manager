package com.example.expense_manager.repository;

import com.example.expense_manager.model.Transaction;
import com.example.expense_manager.model.User;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Page<Transaction>findByUser(User user, Pageable pageable);
    Long countbyuser(User user);
    List<Transaction>findByuserandDateBetween(
            User user,
            LocalDate datefrom,
            LocalDate dateto,
            Pageable pageable);

    Long CountByUserAndDateBetween(
            User user,LocalDate datefrom,LocalDate dateto);
}
