package com.CourseEnrollment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.CourseEnrollment.entity.Enrollment;
import com.CourseEnrollment.repository.CourseRepository;
import com.CourseEnrollment.repository.EnrollmentRepository;
import com.CourseEnrollment.repository.StudentRepository;



@Repository
public class EnrollmentDao {

	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	public Enrollment enrollStudent(Enrollment enrollment) {
		return enrollmentRepository.save(enrollment);
	}
	
	public List<Enrollment> getAllEnrollment(){
		return enrollmentRepository.findAll();
	}
	
	 public Optional<Enrollment> getEnrollmentById(Integer id) {
	    	return enrollmentRepository.findById(id);
	    }
	
	 public Enrollment updateEnrollment(Enrollment enrollment) {
	    	return enrollmentRepository.save(enrollment);
	    }
	 
	 public Optional<Enrollment> deleteEnrollment(Integer id) {
		    Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
		    enrollment.ifPresent(e -> enrollmentRepository.delete(e));
		    return enrollment;
		}

	 
	 public List<Enrollment> getEnrollmentsByStudentId(Integer studentId){
		 return enrollmentRepository.findByStudent_Id(studentId);
	 }
	 public List<Enrollment> getEnrollmentByCourseId(Integer courseId){
		 return enrollmentRepository.findByCourse_Id(courseId);
	 }
	 
	 public Page<Enrollment> getEnrollmentByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	  PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending());
	        return enrollmentRepository.findAll(pageRequest);
	 }
	
	
}
