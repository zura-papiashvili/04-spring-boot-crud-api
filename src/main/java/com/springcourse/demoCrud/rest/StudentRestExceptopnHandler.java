package com.springcourse.demoCrud.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptopnHandler {
    // add exception handling code here

    @ExceptionHandler
    public ResponseEntity<StudentErrorException> handleException(StudentNotFoundException exc) {

        // create a StudentErrorException

        StudentErrorException error = new StudentErrorException();

        error.setStatus(404);
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, org.springframework.http.HttpStatus.NOT_FOUND);
    }

    // add another exception handler to catch any exception (catch all)

    @ExceptionHandler
    public ResponseEntity<StudentErrorException> handleException(Exception exc) {

        // create a StudentErrorException

        StudentErrorException error = new StudentErrorException();

        error.setStatus(400);
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, org.springframework.http.HttpStatus.BAD_REQUEST);

    }

}
