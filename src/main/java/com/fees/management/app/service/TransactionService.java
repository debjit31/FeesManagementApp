package com.fees.management.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fees.management.app.entity.Transactions;
import com.fees.management.app.model.ResponseModel;
import com.fees.management.app.model.TransactionModel;
import com.fees.management.app.model.TransactionRequest;
import com.fees.management.app.repository.TransactionRepository;
import com.mongodb.MongoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public boolean saveTransaction(TransactionRequest transactionRequest){
        try{
            Transactions transaction = Transactions.builder()
                    .transactionDate(LocalDateTime.now())
                    .studentName(transactionRequest.getStudentName())
                    .month(transactionRequest.getMonth())
                    .amount(transactionRequest.getAmount())
                    .notificationTriggered("N")
                    .studentEmail(transactionRequest.getStudentEmail())
                    .build();
            transactionRepository.save(transaction);
            log.info("New Transaction Added : {}", transaction);
            return true;
        } catch (MongoException e){
            log.info("Exception occurred while saving the transaction "+e.getStackTrace());
            return false;
        }
    }

    public ResponseModel getAllTransactions(){
        List<TransactionModel> transactionsList = List.of(objectMapper.convertValue(transactionRepository.findAll(), TransactionModel[].class));
        log.info("Fetching transactions of size : {}", transactionsList.size());
        return ResponseModel.builder().status("SUCCESS").data(transactionsList).build();

    }

    public ResponseModel getTransactionById(String id){
        Optional<Transactions> transactionById = transactionRepository.findById(id);
        log.info("Fetched a transaction with id : {}", transactionById);
        return ResponseModel.builder().status("SUCCESS").data(transactionById).build();
    }

    public ResponseModel deleteTransactionById(String id){
        transactionRepository.deleteById(id);
        log.info("Deleted Transaction with Id : {}", id);
        return ResponseModel.builder().status("SUCCESS").data(null).build();
    }
}
