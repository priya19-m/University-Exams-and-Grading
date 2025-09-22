package com.kce.university.model;

public class Grade {
	private Course course;
	private String letterGrade;
	private double percentage;
	
	public Grade(Course course, String letterGrade, double percentage) {
		this.course=course;
		this.letterGrade=letterGrade;
		this.percentage=percentage;
	}
	
	public Course getCourse() {
		return course;
	}
	public String getLetterGrade() {
		return letterGrade;
	}
	public double getPercentage() {
		return percentage;
	}
	
	@Override
	public String toString() {
		return course + " -> " + letterGrade + " (" + percentage + "%)";
	}
}
