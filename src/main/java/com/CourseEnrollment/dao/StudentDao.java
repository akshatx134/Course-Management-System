package com.CourseEnrollment.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.CourseEnrollment.entity.Course;
import com.CourseEnrollment.entity.Student;
import com.CourseEnrollment.repository.CourseRepository;
import com.CourseEnrollment.repository.EnrollmentRepository;
import com.CourseEnrollment.repository.StudentRepository;



@Repository
public class StudentDao {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}
	 public List<Student> getAllStudent() {
	    	return studentRepository.findAll();
	  }
	 
	 public Optional<Student> getStudentById(Integer id) {
	    	return studentRepository.findById(id);
	    }
	 
	 public Student updateStudent(Student student) {
	    	return studentRepository.save(student);
	    }
	 
	 public Optional<Student> deleteStudent(Integer id) {
		    Optional<Student> student = studentRepository.findById(id);
		    student.ifPresent(s -> studentRepository.delete(s));
		    return student;
		}

	 
	 public List<Course> getCourseByStudentId(Integer studentId) {
		    return enrollmentRepository.findByStudent_Id(studentId).stream()
		        .map(enrollment -> enrollment.getCourse())
		        .collect(Collectors.toList()); 
		}
	 
	 public Page<Student> getStudentByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending());
	        return studentRepository.findAll(pageRequest);
	    }
	
	
	
	
}
