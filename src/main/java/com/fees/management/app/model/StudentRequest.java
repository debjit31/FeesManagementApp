package com.fees.management.app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String contactNumber;
}
