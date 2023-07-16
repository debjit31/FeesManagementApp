package com.fees.management.app.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "students")
@Data
@Builder
public class Student {
    @Id
    private String studentId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String contactNumber;
}
