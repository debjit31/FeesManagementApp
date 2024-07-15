package com.fees.management.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fees.management.app.entity.Transactions;
import com.fees.management.app.model.TransactionModel;
import com.fees.management.app.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ScheduledNotificationService {

    @Autowired
    EmailService emailService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    TransactionRepository transactionRepository;
//
//    @Value("${payment.mailsubject}")
//    private String mailSubject;

    private static final String MONGO_ID = "_id";
    private static final String FEES_MANAGEMENT_SYSTEM_TRANSACTIONS="transactions";

    @Scheduled(cron = "0 0/5 * * * ?")
    public void sendNotifications(){
        log.info("Notification Service Invoked......");
        List<TransactionModel> pendingRecords = getPendingTransactions();
        log.info("No. of pending records = " + pendingRecords.size());
        pendingRecords.stream().forEach(record -> {
            log.info(record.toString());
            String mailBody = String.format("Hello %s,%nPayment of Rs.%s received for the month of %s",record.getStudentName(),record.getAmount(),record.getMonth());
            String mailSubject = String.format("Payment Received for the Month of %s",record.getMonth());
            emailService.sendEmail(record.getStudentEmail(), mailBody, mailSubject);
            updateTransaction(record);
        });
    }

    private void updateTransaction(TransactionModel record) {
        Query query = new Query().addCriteria(new Criteria(MONGO_ID).is(new ObjectId(record.getTransactionId())));
        Update update = new Update();
        update.set("notificationTriggered", "Y");
        update.set("modifiedDate", new Date());
        mongoOperations.updateFirst(query,update,FEES_MANAGEMENT_SYSTEM_TRANSACTIONS).getModifiedCount();
        log.info("Transaction Updated Successfully !!!! ");
    }

    public List<TransactionModel> getPendingTransactions(){
        Query query = new Query();
        Criteria criteria = Criteria.where("notificationTriggered").is("N");
        query.addCriteria(criteria);
        return List.of(objectMapper.convertValue(mongoTemplate.find(query, Transactions.class), TransactionModel[].class));
    }
}
