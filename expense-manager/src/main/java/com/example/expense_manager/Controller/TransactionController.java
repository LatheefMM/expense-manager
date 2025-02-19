package com.example.expense_manager.Controller;



import com.example.expense_manager.contract.request.ListTransactionRequest;
import com.example.expense_manager.contract.request.TransactionRequest;
import com.example.expense_manager.contract.request.response.TransactionResponse;
import com.example.expense_manager.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor

@CrossOrigin(origins = "/http:localhost:3000/")
@RequestMapping("/v1/transcation")

public class TransactionController {
    private final TransactionService transactionservice;

    @PostMapping
    public TransactionResponse createtransaction(
            @RequestBody TransactionRequest transaction, @RequestParam Long userid){
        return  transactionservice.createtransaction(transaction,userid);
    }

    @DeleteMapping("/{id}")
    public  String deletetransactionbyid(@PathVariable Long id,@RequestParam Long Userid){
        return  transactionservice.deletetranscationbyid(Userid,id);
    }

    @PostMapping("/list")
    public String listTransaction(@RequestParam Long Userid,@RequestBody ListTransactionRequest request){
        return transactionservice.listTranscation(Userid, request);
    }

    @PostMapping("/date")
    public String transactiondate(@RequestBody Long id,
                                  LocalDate datefrom,
                                  LocalDate dateto,
                                  @RequestBody dateTransactionRequest request
                                  ){
        return  transactionservice.transactiondate(id,datefrom,dateto,request);
    }
}
