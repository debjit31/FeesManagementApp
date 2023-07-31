package com.fees.management.app.controller;

import com.fees.management.app.model.ResponseModel;
import com.fees.management.app.model.StudentRequest;
import com.fees.management.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/new")
    public ResponseEntity<ResponseModel> addStudent(@RequestBody StudentRequest studentRequest){
        boolean status = studentService.saveStudent(studentRequest);
        if(status){
            return new ResponseEntity<>(ResponseModel.builder().status("SUCCESS").build(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(ResponseModel.builder().status("FAILURE").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<ResponseModel> allStudents(){
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }
}
