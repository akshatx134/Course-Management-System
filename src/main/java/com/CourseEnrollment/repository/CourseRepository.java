package com.CourseEnrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CourseEnrollment.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	List<Course> findByInstructor_Id(Integer instructorId);
}
