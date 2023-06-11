package com.fees.management.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "transactions")
@Data
@Builder
@AllArgsConstructor
public class Transactions {
    @Id
    private String transactionId;
    private LocalDateTime transactionDate;
    private String studentName;
    private String month;
    private String notificationTriggered;
    private String amount;
}
