package com.kce.university.model;

public class MarkEntry {
	private Student student;
	private Course course;
	private Assessment assessment;
	private double marksObtained;
	
	public MarkEntry(Student student, Course course, Assessment assessment, double marksObtained) {
		this.student=student;
		this.course=course;
		this.assessment=assessment;
		this.marksObtained=marksObtained;
	}
	
	public Student getStudent() {
		return student;
	}
	public Course getCourse() {
		return course;
	}
	public Assessment getAssessment() {
		return assessment;
	}
	public double getMarksObtained() {
		return marksObtained;
	}
	
	@Override
	public String toString() {
		return student.getName() + " - " + course.getCourseCode() + " - " + assessment.getName() + " : " + marksObtained;
	}

}
