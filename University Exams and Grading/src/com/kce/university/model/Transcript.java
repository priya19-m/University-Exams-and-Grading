package com.kce.university.model;

import java.util.ArrayList;
import java.util.List;

public class Transcript {
	private Student student;
	private List<Grade> grades;
	private double cgpa;
	
	public Transcript(Student student, double cgpa) {
		this.student=student;
		this.grades=new ArrayList<>();
	}
	
	public void addGrade(Grade grade) {
		grades.add(grade);
	}
	public void setCgpa(double cgpa) {
		this.cgpa=cgpa;
	}
	public Student getStudent() {
		return student;
	}
	public List<Grade> getGrades(){
		return grades;
	}
	public double getCgpa() {
		return cgpa;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Transcript for " + student.getName() + " ");
		for (Grade g : grades) {
			sb.append(g);
		}
		sb.append("CGPA: ").append(cgpa);
		return sb.toString();
	}
 
}
