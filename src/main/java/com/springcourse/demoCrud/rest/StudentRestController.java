package com.springcourse.demoCrud.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.demoCrud.entity.Student;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    @PostConstruct
    public void loadData() {

        theStudents = new ArrayList<>();

        theStudents.add(new Student("John", "Doe"));

        theStudents.add(new Student("Mary", "Public"));

        theStudents.add(new Student("Samantha", "Johnson"));
    }

    // define endpoint for "/students" - return list of students

    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudents;
    }

    // define endpoint for "/students/{studentId}" - return student at index

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // check the studentId against list size

        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return theStudents.get(studentId);
    }

    // add an exception handler using @ExceptionHandler

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

    // define endpoint for "/students" - add student

    // define endpoint for "/students" - update student

    // define endpoint for "/students/{studentId}" - delete student
}
