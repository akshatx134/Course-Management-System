package com.CourseEnrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CourseEnrollment.dto.ResponseStructure;
import com.CourseEnrollment.entity.Enrollment;
import com.CourseEnrollment.service.EnrollmentService;



@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Enrollment>> enrollStudent(@RequestBody Enrollment enrollment){
		return enrollmentService.enrollStudent(enrollment);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Enrollment>>> getAllEnrollment(){
		return enrollmentService.getAllEnrollment();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Enrollment>> getEnrollmentById(@PathVariable Integer id){
		return enrollmentService.getEnrollmentById(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Enrollment>> updateEnrollment(@RequestBody Enrollment enrollment){
		return enrollmentService.updateEnrollment(enrollment);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteEnrollment(@PathVariable Integer id){
		return enrollmentService.deleteEnrollment(id);
	}
	
	@GetMapping("/student/{studentId}")
	public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentsByStudentId(@PathVariable Integer studentId){
		return enrollmentService.getEnrollmentsByStudentId(studentId);
	}
	
	@GetMapping("course/{courseId}")
	public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentByCourseId(@PathVariable Integer courseId){
		return enrollmentService.getEnrollmentsByCourseId(courseId);
	}
	
	@GetMapping("/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Enrollment>>> getEnrollmentByPaginationAndSorting(
			@PathVariable int pageNumber,
			@PathVariable int pageSize,
			@PathVariable String field){
		
		return enrollmentService.getEnrollmentsByPaginationAndSorting(pageNumber, pageSize, field);
	}
	
	
	
	
}
