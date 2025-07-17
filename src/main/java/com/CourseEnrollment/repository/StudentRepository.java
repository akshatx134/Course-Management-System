package com.CourseEnrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.CourseEnrollment.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	@Query("SELECT DISTINCT e.student FROM Enrollment e WHERE e.course.instructor.id = :instructorId")
	List<Student> findStudentsByInstructorId(@Param("instructorId") Integer instructorId);

}
