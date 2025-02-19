package com.example.expense_manager.service;

import com.example.expense_manager.contract.request.ListTransactionRequest;
import com.example.expense_manager.contract.request.TransactionRequest;
import com.example.expense_manager.contract.request.response.TransactionListResponse;
import com.example.expense_manager.contract.request.response.TransactionResponse;
import com.example.expense_manager.exception.EntityNotFoundException;
import com.example.expense_manager.exception.InvalidUserException;
import com.example.expense_manager.model.Category;
import com.example.expense_manager.model.Transaction;
import com.example.expense_manager.model.User;
import com.example.expense_manager.repository.CategoryRepository;
import com.example.expense_manager.repository.TransactionRepository;
import com.example.expense_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class TransactionService {
    private TransactionRepository transactionRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    public TransactionResponse createtransaction(TransactionRequest request, Long userId){
        Category category = categoryRepository.findByType(request.getType())
                .orElseThrow(()->new EntityNotFoundException("Category"));

        User user = UserRepository.findById(userId).
                orElseThrow(()->new EntityNotFoundException("User",userId));

        Transaction transaction = Transaction.builder()
                .name(request.getName())
                .amount(request.getamount())
                .category(category)
                .date(request.getDate())
                .user(user)
                .build();

       transaction = transactionRepository.save(transaction);

       TransactionResponse response = TransactionResponse.builder()
               .id(transaction.getId())
               .name(transaction.getName())
               .type(transaction.getCategory().getType())
               .amount(transaction.getAmount())
               .color(transaction.getCategory().getColor())
               .date(transaction.getDate())
               .user(transaction.getUser().getId())
               .build();

       return  response;
    }

    public String deletetransactionbyid(Long Userid,Long id){

        User user = userRepository.findById(Userid).
                orElseThrow(()->new EntityNotFoundException("user",Userid));

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("transaction",id));



        if (!transaction.getUser().equals(user)){
            throw new EntityNotFoundException("transaction");
        }
        transactionRepository.delete(transaction);
        return "Transaction"+transaction.getName()+"has been deleted";



    }

    public TransactionListResponse listTransactions(Long userid,ListTransactionRequest request){
        Pageable page =
                PageRequest.of(
                        request.getPageNumber(),
                        request.getPageSize(),
                        Sort.by(Sort.Direction.ASC,"Date"));

        User user = userRepository.findById(userid).
                orElseThrow(()->new EntityNotFoundException("User",userid));

        Page <Transaction> transactionPage = transactionRepository.findByUser(user,page);

        List<TransactionResponse> transactions =
        transactionRepository.findByUser(user,page)
                .stream().map(transaction->TransactionResponse.builder()
                        .id(transaction.getId())
                        .name(transaction.getName())
                        .type(transaction.getCategory().getType())
                        .amount(transaction.getamount())
                        .color(transaction.getCategory().getColor())
                        .date(transaction.getDate())
                        .user(transaction.getUser().getId())
                        .build()).collect(Collectors.toList());


        Long Totaltransactions = transactionRepository.countbyuser(user);

        return  TransactionListResponse.builder()
                .transactions(transactions)
                .totalTransactions(transactionPage.getTotalElements())
                .currentPage(transactionPage.getNumber())
                .totalPages(transactionPage.getTotalPages())
                .build();


    }


    public TransactionListResponse byDatelistTransaction(
            Long userid, LocalDate datefrom, LocalDate dateto, ListTransactionRequest request){
        Pageable Page =
                PageRequest.of(
                        request.getPageNumber(),
                        request.getPageSize(),
                        Sort.by(Sort.Direction.ASC,"Date"));

        User user =
                userRepository.findById(userid)
                        .orElseThrow(()->new InvalidUserException("User",userid));

        List<Transaction>transactionList =
                transactionRepository.findByuserandDateBetween(user,datefrom,dateto,Page);
        int start = (int) Page.getOffset();
        if (start>transactionList.size()){
            start = transactionList.size();
        }
        int end = Math.min((start+Page.getPageSize()),transactionList.size());
        List<Transaction>transactions = transactionList.subList(start,end);
        List<TransactionResponse> transactionResponses =
                transactionRepository
                        .findByuserandDateBetween(user,datefrom,dateto,Page)
                        .stream()
                        .map(
                                transaction->TransactionResponse.builder()
                                        .id(transaction.getId())
                                        .name(transaction.getName())
                                        .type(transaction.getCategory().getType())
                                        .amount(transaction.getAmount())
                                        .color(transaction.getCategory().getColor())
                                        .date(transaction.getDate())
                                        .user(transaction.getUser().getId())
                                        .build())
                        .collect(Collectors.toList());

        Long TotalTransactions =
                transactionRepository.CountByUserAndDateBetween(user,datefrom,dateto);



        return TransactionListResponse.builder()
                .transactions(transactionResponses)
                .totaltransactions(TotalTransactions)
                .currentPage(page.getPageNumber())
                .totalpages((int)Math.ceil((double) TotalTransactions/page.getPageSize()))
                .build();



    }


}
