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
import com.CourseEnrollment.entity.Instructor;
import com.CourseEnrollment.entity.Student;
import com.CourseEnrollment.service.InstructorService;



@RestController
@RequestMapping("/instructors")
public class InstructorController {

	
	@Autowired
	private InstructorService instructorService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Instructor>> addInstructor(@RequestBody Instructor instructor){
	return instructorService.addInstructor(instructor);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Instructor>>> getAllInstructor(){
		return instructorService.getAllInstructor();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Instructor>> getInstructorById(@PathVariable Integer id){
		return instructorService.getInstructorById(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Instructor>> updateInstructor(@RequestBody Instructor instructor){
		return instructorService.updateInstructor(instructor);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteInstructor(@PathVariable Integer id){
		return instructorService.deleteInstructor(id);
	}
	
	@GetMapping("/students/{instructorId}")
	public ResponseEntity<ResponseStructure<List<Student>>> getStudentByInstructorId(@PathVariable Integer instructorId){
		return instructorService.getStudentsByInstructorId(instructorId);
	}
	
	@GetMapping("/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Instructor>>> getInstructorsByPaginationAndSorting(
        @PathVariable int pageNumber,
        @PathVariable int pageSize,
        @PathVariable String field) {

		return instructorService.getInstructorByPaginationAndSorting(pageNumber, pageSize, field);
    }
	
	
}
