package com.fees.management.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TransactionRequest {
    private String studentName;
    private String month;
    private String amount;
}
