package com.fees.management.app.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionModel {
    private String transactionId;
    private LocalDateTime transactionDate;
    private String studentName;
    private String month;
    private String notificationTriggered;
    private String amount;
}
