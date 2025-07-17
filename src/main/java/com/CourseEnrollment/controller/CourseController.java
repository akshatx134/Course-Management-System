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
import com.CourseEnrollment.entity.Course;
import com.CourseEnrollment.service.CourseService;



@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Course>> saveCourse(@RequestBody Course course){
		return courseService.saveCourse(course);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Course>>> getAllCourse(){
		return courseService.getAllCourse();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Course>> getCourseById(@PathVariable Integer id){
		return courseService.getCourseById(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Course>> updateCourse(@RequestBody Course course){
		return courseService.updateCourse(course);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteCourse(@PathVariable Integer id){
		return courseService.deleteCourse(id);
	}
	
	@GetMapping("/instructoe/{instructorId}")
	public ResponseEntity<ResponseStructure<List<Course>>> getCourseByInstructorId(@PathVariable Integer instructorId){
		return courseService.getCoursesByInstructorId(instructorId);
	}
	
	@GetMapping("/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Course>>> getCourseByPaginationAndSorting(
			@PathVariable int pageNumber,
			@PathVariable int pageSize,
			@PathVariable String field){
		return courseService.getCourseByPaginationAndSorting(pageNumber, pageSize, field);
	}
	
	
	
}
