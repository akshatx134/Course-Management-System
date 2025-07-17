package com.CourseEnrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CourseEnrollment.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
	
	List<Enrollment> findByStudent_Id(Integer studentID);
	List<Enrollment> findByCourse_Id(Integer courseID);
	

}
