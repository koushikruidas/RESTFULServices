package com.RESTService.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RESTService.entity.Student;
import com.RESTService.exceptionHandle.StudentErrorResponse;
import com.RESTService.exceptionHandle.StudentNotFoundException;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	List<Student> theStudents;
	
	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<Student>();
		theStudents.add(new Student("Koushik","Ruidas"));
		theStudents.add(new Student("Koustav","Ruidas"));
		theStudents.add(new Student("Kasturi","Ruidas"));
		theStudents.add(new Student("Nandalal","Ruidas"));
	}
	
	@GetMapping("/student-api")
	public List<Student> getStudents(){
		return theStudents;
	}
	
	@GetMapping("/student-api/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		if(studentId > theStudents.size() || studentId < 0)
			throw new StudentNotFoundException("Student id not found: " +studentId);
		return theStudents.get(studentId);
	}
}
