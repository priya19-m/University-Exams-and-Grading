package com.kce.university.service;

import com.kce.university.model.*;
import com.kce.university.exception.ResultProcessingException;
import java.util.*;

public class ResultService {
	public Grade calculateGrade(Student student, Course course, List<MarkEntry> entries) throws ResultProcessingException{
		double totalWeightedMarks = 0;
		double totalWeightage = 0;
		
		for(MarkEntry entry : entries) {
			if(entry.getStudent().equals(student) && entry.getCourse().equals(course)) {
				double percentage = (entry.getMarksObtained() / entry.getAssessment().getMaxMarks())*100;
				totalWeightedMarks += (percentage*entry.getAssessment().getWeightage());
				totalWeightage += entry.getAssessment().getWeightage();
			}
		}
		if(totalWeightage == 0) {
			throw new ResultProcessingException("No marks available for student in course.");
		}
		
		double finalPercentage = totalWeightedMarks / totalWeightage;
		String gradeLetter;
		
		if(finalPercentage >= 90) gradeLetter ="A+";
		else if(finalPercentage >= 80) gradeLetter="A";
		else if(finalPercentage >= 70) gradeLetter="B";
		else if(finalPercentage >= 60) gradeLetter="C";
		else if(finalPercentage >= 50) gradeLetter="D";
		else gradeLetter="F";
		
		return new Grade(course, gradeLetter, finalPercentage);
	}
	
	public Transcript generateTranscript(Student student, List<Course> courses, List<MarkEntry> allEntries) throws ResultProcessingException{
		Transcript transcript = new Transcript(student, 0);
		double totalPoints = 0;
		int totalCourses = 0;
		
		for(Course course : courses) {
			Grade grade = calculateGrade(student, course, allEntries);
			transcript.addGrade(grade);
			
			double gradePoint = getGradePoint(grade.getLetterGrade());
			totalPoints += gradePoint;
			totalCourses++;
		}
		
		if(totalCourses > 0) {
			transcript.setCgpa(totalPoints/totalCourses);
		}else {
			throw new ResultProcessingException("No courses available for transcript.");
		}
		return transcript;
	}
	
	private double getGradePoint(String letterGrade) {
		switch(letterGrade) {
		case "A+":return 10;
		case "A":return 9;
		case "B":return 8;
		case "C":return 7;
		case "D":return 6;
		default:return 0;
		}
	}

}
