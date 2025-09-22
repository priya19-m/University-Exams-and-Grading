package com.kce.university.model;

public class ExamSchedule {
	private Course course;
	private String date;
	
	public ExamSchedule(Course course, String date) {
		this.course=course;
		this.date=date;
	}
	
	public Course getCourse() {
		return course;
	}
	public String getDate() {
		return date;
	}
	@Override
	public String toString() {
		return course + "scheduled on" + date;
	}

}
