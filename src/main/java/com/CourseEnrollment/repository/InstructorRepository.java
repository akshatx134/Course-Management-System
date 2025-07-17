package com.CourseEnrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CourseEnrollment.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

}
