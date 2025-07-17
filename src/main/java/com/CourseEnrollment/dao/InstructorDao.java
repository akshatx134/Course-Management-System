package com.CourseEnrollment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.CourseEnrollment.entity.Instructor;
import com.CourseEnrollment.entity.Student;
import com.CourseEnrollment.repository.InstructorRepository;
import com.CourseEnrollment.repository.StudentRepository;



@Repository
public class InstructorDao {

	
	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public Instructor saveInstructor(Instructor instructor) {
		return instructorRepository.save(instructor);
	}
	
	public List<Instructor> getAllInstructor(){
		return instructorRepository.findAll();
	}
	
	public Optional<Instructor> getInstructorById(Integer id){
		return instructorRepository.findById(id);
	}
	
	public Instructor updateInstructor(Instructor instructor) {
		return instructorRepository.save(instructor);
	}
	
	public List<Student> getStudentByInstructorId(Integer instructorId){
		 return studentRepository.findStudentsByInstructorId(instructorId);
	 }
	 
	 public Optional<Instructor> deleteInstructor(Integer id) {
		    Optional<Instructor> instructor = instructorRepository.findById(id);
		    instructor.ifPresent(i -> instructorRepository.delete(i));
		    return instructor;
		}


	 
	 public Page<Instructor> getInstructorByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending());
	        return instructorRepository.findAll(pageRequest);
	    }
	
	
}
