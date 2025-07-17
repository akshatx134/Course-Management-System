package com.CourseEnrollment.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CourseEnrollment.dao.EnrollmentDao;
import com.CourseEnrollment.dto.ResponseStructure;
import com.CourseEnrollment.entity.Course;
import com.CourseEnrollment.entity.Enrollment;
import com.CourseEnrollment.entity.Student;
import com.CourseEnrollment.repository.CourseRepository;
import com.CourseEnrollment.repository.EnrollmentRepository;
import com.CourseEnrollment.repository.StudentRepository;


@Service
public class EnrollmentService {

	@Autowired
	private EnrollmentDao enrollmentDao;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	public ResponseEntity<ResponseStructure<Enrollment>> enrollStudent(Enrollment enrollment){
		Optional<Student> studentOptional = studentRepository.findById(enrollment.getStudent().getId());
		if (!studentOptional.isPresent()) {
			ResponseStructure<Enrollment> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			structure.setMessage("Student not found");
			structure.setData(null);
			return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
		}
		enrollment.setStudent(studentOptional.get());
		
		Optional<Course> courseOptional = courseRepository.findById(enrollment.getCourse().getId());
		if (!courseOptional.isPresent()) {
			ResponseStructure<Enrollment> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			structure.setMessage("Course not found");
			structure.setData(null);
			return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
		}
		enrollment.setCourse(courseOptional.get());
		
		Enrollment savedEnrollment = enrollmentDao.enrollStudent(enrollment);
		
		ResponseStructure<Enrollment> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Enrollment created succesfully");
		structure.setData(savedEnrollment);
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
			
		
	}
	
	
	public ResponseEntity<ResponseStructure<List<Enrollment>>> getAllEnrollment(){
		
		List<Enrollment> getEnrollment = enrollmentDao.getAllEnrollment();
		if(!getEnrollment.isEmpty()) {
			ResponseStructure<List<Enrollment>> structure = new ResponseStructure<List<Enrollment>>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(getEnrollment);
		
			return new ResponseEntity<ResponseStructure<List<Enrollment>>>(structure, HttpStatus.OK);
		
		} else {
			ResponseStructure<List<Enrollment>> structure = new ResponseStructure<List<Enrollment>>();
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Fail");
			
			return new ResponseEntity<ResponseStructure<List<Enrollment>>>(structure, HttpStatus.NOT_FOUND);
		}
					
	}
	
	
	public ResponseEntity<ResponseStructure<Enrollment>> getEnrollmentById(Integer id){
		
		Optional<Enrollment> opt = enrollmentDao.getEnrollmentById(id);
		ResponseStructure<Enrollment> structure = new ResponseStructure<Enrollment>();
		if(opt.isPresent()) {
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Success");
		structure.setData(opt.get());
		return new ResponseEntity<ResponseStructure<Enrollment>>(structure, HttpStatus.OK);
	} else {
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("fail");
		return new ResponseEntity<ResponseStructure<Enrollment>>(structure, HttpStatus.NOT_FOUND);
	}
		
  }
	
	public ResponseEntity<ResponseStructure<Enrollment>> updateEnrollment(Enrollment enrollment){
		Enrollment updateEnrollment  = enrollmentDao.updateEnrollment(enrollment);
		ResponseStructure<Enrollment> structure = new ResponseStructure<Enrollment>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Success");
		structure.setData(updateEnrollment);
		
		return new ResponseEntity<ResponseStructure<Enrollment>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteEnrollment(Integer id){
		Optional<Enrollment> opt = enrollmentDao.deleteEnrollment(id);
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		
		if(opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Deleted");
			structure.setData("Record Deleted");
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Failure");
			structure.setData("ID not found");
			return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
		}
			
	}
	
	
	public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentsByStudentId(Integer studentId){
		
		List<Enrollment> enrollments = enrollmentRepository.findByStudent_Id(studentId);
		
		ResponseStructure<List<Enrollment>> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Enrollments fetched Successfully");
		response.setData(enrollments);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	
	
	
	public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentsByCourseId(Integer courseId) {
		
		List<Enrollment> enrollments = enrollmentRepository.findByCourse_Id(courseId);
		
		ResponseStructure<List<Enrollment>> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Enrollments fetched Successfully");
		response.setData(enrollments);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Page<Enrollment>>> getEnrollmentsByPaginationAndSorting(int pageNumber, int pageSize, String field) {
		
		Page<Enrollment> page = enrollmentDao.getEnrollmentByPaginationAndSorting(pageNumber, pageSize, field);
		
		ResponseStructure<Page<Enrollment>> structure = new ResponseStructure<>();
		
		if(!page.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Fetched enrollment successfully");
			structure.setData(page);
			
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("failed");
			return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
		}
		
		
	}
	
}
