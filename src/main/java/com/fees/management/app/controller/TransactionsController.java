package com.fees.management.app.controller;

import com.fees.management.app.model.ResponseModel;
import com.fees.management.app.model.TransactionRequest;
import com.fees.management.app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/new")
    public ResponseEntity<ResponseModel> addTransaction(@RequestBody TransactionRequest transactionRequest){
        boolean status = transactionService.saveTransaction(transactionRequest);
        if(status){
            return new ResponseEntity<>(ResponseModel.builder().status("SUCCESS").build(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(ResponseModel.builder().status("FAILURE").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllTransactions")
    public ResponseModel getAllTransactions(){
        return transactionService.getAllTransactions();
    }
}
