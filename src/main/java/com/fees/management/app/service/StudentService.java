package com.fees.management.app.service;

import com.fees.management.app.entity.Student;
import com.fees.management.app.model.StudentRequest;
import com.fees.management.app.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public boolean saveStudent(StudentRequest studentRequest) {
        try{
            Student student = Student.builder()
                    .firstName(studentRequest.getFirstName())
                    .lastName(studentRequest.getLastName())
                    .emailAddress(studentRequest.getEmailAddress())
                    .contactNumber(studentRequest.getContactNumber())
                    .studentId(generateUniqueStudentId())
                    .build();

            studentRepository.save(student);
            return true;
        }catch (Exception e){
            log.info("Error Occured while saving record in DB");
            e.printStackTrace();
            return false;
        }
    }

    private String generateUniqueStudentId() {
        long count = studentRepository.count();
        return "BDU"+String.valueOf(count+1);
    }
}
