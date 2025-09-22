package com.kce.university.service;

import com.kce.university.model.Course;
import com.kce.university.model.Student;
import com.kce.university.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UniversityManagementService {
	private List<Course> courses=new ArrayList<>();
	private List<Student> students = new ArrayList<>();
	
	public void addCourse(Course course) {
		courses.add(course);
	}
	public void addStudent(Student student) {
		students.add(student);
	}
	
	public Course getCourseByCode(String code) throws EntityNotFoundException{
		return courses.stream()
				.filter(c->c.getCourseCode().equalsIgnoreCase(code))
				.findFirst()
				.orElseThrow(() -> new EntityNotFoundException("Course not found!"));
	}
	
	public Student getStudentById(int id) throws EntityNotFoundException{
		return students.stream()
				.filter(s->s.getStudentId() == id)
				.findFirst()
				.orElseThrow(() -> new EntityNotFoundException("Student not found!"));
	}
	
	public List<Course> getAllCourses(){
		return courses;
	}
	public List<Student> getAllStudents(){
		return students;
	}
}
