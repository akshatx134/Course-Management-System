package com.CourseEnrollment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CourseEnrollment.dao.StudentDao;
import com.CourseEnrollment.dto.ResponseStructure;
import com.CourseEnrollment.entity.Course;
import com.CourseEnrollment.entity.Enrollment;
import com.CourseEnrollment.entity.Student;
import com.CourseEnrollment.repository.CourseRepository;
import com.CourseEnrollment.repository.EnrollmentRepository;
import com.CourseEnrollment.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public ResponseEntity<ResponseStructure<Student>> addStudent(Student student){
		Student savedStudent = studentDao.addStudent(student);
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Success");
		structure.setData(savedStudent);
		
		return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> getAllStudent(){
		List<Student> getStudents = studentDao.getAllStudent();
		if(!getStudents.isEmpty()) {
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Success");
		structure.setData(getStudents);
		
		return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.OK);
	} else {
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Fail");
		return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.NOT_FOUND);
		}
	}
	
	
	public ResponseEntity<ResponseStructure<Student>> getStudentById(Integer id){
		Optional<Student> opt = studentDao.getStudentById(id);
		
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if(opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Fail");
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> updateStudent(Student student){
		Student updateStudent = studentDao.updateStudent(student);
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Success");
		structure.setData(updateStudent);
		return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteStudent(Integer id){
		Optional<Student> opt = studentDao.deleteStudent(id);
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		
		if(opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData("Record Updated");
			return new ResponseEntity<>(structure, HttpStatus.OK);
			
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Failure");
			structure.setData("ID not found");
			return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	public ResponseEntity<ResponseStructure<List<Course>>> getCourseByStudentId(Integer studentId){
	    List<Enrollment> enrollments = enrollmentRepository.findByStudent_Id(studentId);

	    List<Course> courses = enrollments.stream()
	                                      .map(Enrollment::getCourse)
	                                      .toList();

	    ResponseStructure<List<Course>> structure = new ResponseStructure<>();
	    structure.setStatusCode(HttpStatus.OK.value());
	    structure.setMessage("Courses fetched successfully for student ID: " + studentId);
	    structure.setData(courses);

	    return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	
	 	public ResponseEntity<ResponseStructure<Page<Student>>> getStudentByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	       
	        Page<Student> page=studentDao.getStudentByPaginationAndSorting(pageNumber, pageSize, field);
	        ResponseStructure<Page<Student>> structure = new ResponseStructure<>();
	        if(!page.isEmpty()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Fetched students successfully");
	        structure.setData(page);
	        
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	        }else {
	        	structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        	structure.setMessage("failed");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	        }
	 }
	
	
	
	
	
	
}
