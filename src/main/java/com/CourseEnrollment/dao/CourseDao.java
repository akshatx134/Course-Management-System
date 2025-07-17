package com.CourseEnrollment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.CourseEnrollment.entity.Course;
import com.CourseEnrollment.repository.CourseRepository;
import com.CourseEnrollment.repository.InstructorRepository;


@Repository
public class CourseDao {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private InstructorRepository instructorRepository;
	
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}
	
	public List<Course> getAllCourse(){
		return courseRepository.findAll();
	}
	
	public Optional<Course> getCourseById(Integer id){
		return courseRepository.findById(id);
	}
	
	public Course updateCourse(Course course) {
		return courseRepository.save(course);
	}
	
	public Optional<Course> deleteCourse(Integer id){
		Optional<Course> course = courseRepository.findById(id);
		course.ifPresent(c -> courseRepository.delete(c));
		return course;
	}
	
	public List<Course> getCourseByInstructorId(Integer instructorId){
		return courseRepository.findByInstructor_Id(instructorId);
	}
	
	public Page<Course> getCourseByPaginationAndSorting(int pageNumber, int pageSize, String field){
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending());
		return courseRepository.findAll(pageRequest);
	}




}
