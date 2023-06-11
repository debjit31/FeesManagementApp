package com.fees.management.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fees.management.app.entity.Transactions;
import com.fees.management.app.model.TransactionModel;
import com.fees.management.app.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

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
    TransactionRepository transactionRepository;

    @Scheduled(cron = "0 0/2 * * * ?")
    public void sendNotifications(){
        log.info("Notification Service Invoked......");
        /// find all transactions for which notification not yet triggered
        List<TransactionModel> pendingRecords = getPendingTransactions();
        log.info("No. of pending records = " + pendingRecords.size());
        pendingRecords.stream().forEach(record -> {
            /// fetch the email id for that particular student from user collection
            // call the generic Email sender method for each record
            /// after sucessfull email triggered, update that collection and set notificationTriggered to Y
            log.info(record.getStudentName());
            log.info(record.getMonth());
            log.info(record.getNotificationTriggered());
        });
    }

    public List<TransactionModel> getPendingTransactions(){
        Query query = new Query();
        Criteria criteria = Criteria.where("notificationTriggered").is("N");
        query.addCriteria(criteria);
        return List.of(objectMapper.convertValue(mongoTemplate.find(query, Transactions.class), TransactionModel[].class));
    }
}
