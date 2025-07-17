package com.CourseEnrollment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CourseEnrollment.dao.CourseDao;
import com.CourseEnrollment.dto.ResponseStructure;
import com.CourseEnrollment.entity.Course;
import com.CourseEnrollment.entity.Instructor;
import com.CourseEnrollment.repository.CourseRepository;
import com.CourseEnrollment.repository.InstructorRepository;



@Service
public class CourseService {

	@Autowired
	private CourseDao courseDao; 
	
	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	public ResponseEntity<ResponseStructure<Course>> saveCourse(Course course){
		Instructor instructor = course.getInstructor();
		if(instructor != null && instructor.getId() != null) {
			Optional<Instructor> optionalInstructor = instructorRepository.findById(instructor.getId());
			if(optionalInstructor.isPresent()) {
				course.setInstructor(optionalInstructor.get());;
			} else {
				ResponseStructure<Course> structure = new ResponseStructure<Course>();
				structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
				structure.setMessage("Instructor ID not found");
				structure.setData(null);
				return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
			}
		} else {
			course.setInstructor(null);
		}
		
		Course savedCourse = courseDao.saveCourse(course);
		
		ResponseStructure<Course> structure = new ResponseStructure<Course>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Success");
		structure.setData(savedCourse);
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<ResponseStructure<List<Course>>> getAllCourse(){
	    List<Course> getCourse=courseDao.getAllCourse();
		if(!getCourse.isEmpty()) {
			ResponseStructure<List<Course>> structure=new ResponseStructure<List<Course>>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("success");
			structure.setData(getCourse);
			
			return new ResponseEntity<ResponseStructure<List<Course>>>(structure,HttpStatus.OK);
			
		}else {
			ResponseStructure<List<Course>> structure=new ResponseStructure<List<Course>>();
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Fail");
			
			return new ResponseEntity<ResponseStructure<List<Course>>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<Course>>getCourseById(Integer id) {
		Optional<Course> opt=courseDao.getCourseById(id);
		ResponseStructure<Course> structure=new ResponseStructure<Course>();
		if(opt.isPresent()) {
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("success");
		structure.setData(opt.get());
		return new ResponseEntity<ResponseStructure<Course>>(structure,HttpStatus.OK);
	}else{
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Fail");
		return new ResponseEntity<ResponseStructure<Course>>(structure,HttpStatus.NOT_FOUND);
		

}
	}
	
	public ResponseEntity<ResponseStructure<Course>> updateCourse(Course course) {
		Course updateCourse=courseDao.updateCourse(course);
		ResponseStructure<Course> structure=new ResponseStructure<Course>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Success");
		structure.setData(updateCourse);
		return new ResponseEntity<ResponseStructure<Course>>(structure,HttpStatus.OK);
		
}
	
	public ResponseEntity<ResponseStructure<String>> deleteCourse(Integer id) {
	    Optional<Course> opt = courseDao.deleteCourse(id); 

	    ResponseStructure<String> structure = new ResponseStructure<>();

	    if (opt.isPresent()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Deleted");
	        structure.setData("Course record deleted");
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Failure");
	        structure.setData("Course ID not found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}

	
	
	public ResponseEntity<ResponseStructure<List<Course>>> getCoursesByInstructorId(Integer instructorId) {

        List<Course> courses = courseRepository.findByInstructor_Id(instructorId);

        ResponseStructure<List<Course>> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Courses fetched successfully for instructor ID: " + instructorId);
        structure.setData(courses);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Page<Course>>> getCourseByPaginationAndSorting(int pageNumber, int pageSize, String field) {

        Page<Course> page = courseDao.getCourseByPaginationAndSorting(pageNumber, pageSize, field);

        ResponseStructure<Page<Course>> structure = new ResponseStructure<>();
        if(!page.isEmpty()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Fetched course successfully");
	        structure.setData(page);
	        
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	        }else {
	        	structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        	structure.setMessage("failed");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
}
	
	
	
}
