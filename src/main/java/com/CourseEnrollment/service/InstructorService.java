package com.CourseEnrollment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CourseEnrollment.dao.InstructorDao;
import com.CourseEnrollment.dto.ResponseStructure;
import com.CourseEnrollment.entity.Instructor;
import com.CourseEnrollment.entity.Student;
import com.CourseEnrollment.repository.CourseRepository;
import com.CourseEnrollment.repository.EnrollmentRepository;
import com.CourseEnrollment.repository.InstructorRepository;
import com.CourseEnrollment.repository.StudentRepository;

@Service
public class InstructorService {

	
	@Autowired
	private InstructorDao instructorDao;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private InstructorRepository  instructorRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	public ResponseEntity<ResponseStructure<Instructor>> addInstructor(Instructor instructor){
		Instructor savedInstructor = instructorDao.saveInstructor(instructor);
		
		ResponseStructure<Instructor> structure = new ResponseStructure<Instructor>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Success");
		structure.setData(instructor);
		return new ResponseEntity<ResponseStructure<Instructor>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Instructor>>> getAllInstructor(){
		List<Instructor> getEmployee = instructorDao.getAllInstructor();
		
		if(!getEmployee.isEmpty()) {
			ResponseStructure<List<Instructor>> structure = new ResponseStructure<List<Instructor>>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(getEmployee);
			
			return new ResponseEntity<ResponseStructure<List<Instructor>>>(structure,  HttpStatus.CREATED);
		} else {
			ResponseStructure<List<Instructor>> structure = new ResponseStructure<List<Instructor>>();
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Fail");
			
			return new ResponseEntity<ResponseStructure<List<Instructor>>>(structure, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<Instructor>> getInstructorById(Integer id){
		Optional<Instructor> opt = instructorDao.getInstructorById(id);
		
		ResponseStructure<Instructor> structure = new ResponseStructure<Instructor>();
		if(opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Instructor>>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Fail");
			return new ResponseEntity<ResponseStructure<Instructor>>(structure, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<Instructor>> updateInstructor(Instructor instructor){
		Instructor updateInstructor = instructorDao.updateInstructor(instructor);
		ResponseStructure<Instructor> structure = new ResponseStructure<Instructor>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Success");
		structure.setData(updateInstructor);
		
		return new ResponseEntity<ResponseStructure<Instructor>>(structure, HttpStatus.OK);
		
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteInstructor(Integer id){
		Optional<Instructor> opt = instructorDao.deleteInstructor(id);
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		
		if(opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Deleted");
			structure.setData("Instructor Data Deleted");
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Failure");
			structure.setData("Instrutor ID not found");
			return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> getStudentsByInstructorId(Integer instructorId){
		List<Student> student = studentRepository.findStudentsByInstructorId(instructorId);
		
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Success");
		structure.setData(student);
		
		return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.OK);
	}
	
	 public ResponseEntity<ResponseStructure<Page<Instructor>>> getInstructorByPaginationAndSorting(int pageNumber, int pageSize, String field) {

	        Page<Instructor> page = instructorDao.getInstructorByPaginationAndSorting(pageNumber, pageSize, field);

	        ResponseStructure<Page<Instructor>> structure = new ResponseStructure<>();
	        
	        if(!page.isEmpty()) {
		        structure.setStatusCode(HttpStatus.OK.value());
		        structure.setMessage("Fetched instructors successfully");
		        structure.setData(page);
		        
		        return new ResponseEntity<>(structure, HttpStatus.OK);
		        }else {
		        	structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		        	structure.setMessage("failed");
		        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
		    }
	}
	
}
