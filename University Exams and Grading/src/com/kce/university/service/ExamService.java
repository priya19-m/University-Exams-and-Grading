package com.kce.university.service;

import com.kce.university.model.*;
import com.kce.university.exception.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

public class ExamService {
	private List<Assessment> assessments = new ArrayList<>();
	private List<ExamSchedule> schedules = new ArrayList<>();
	private List<MarkEntry> markEntries = new ArrayList<>();
	
	public void createAssessment(Assessment assessment) throws InvalidDataException{
		if(assessment.getWeightage() < 0 || assessment.getWeightage() > 100 ) {
			throw new InvalidDataException("Invalid weightage!");
		}
		assessments.add(assessment);
	}
	
	public void scheduleExam(ExamSchedule schedule) {
		schedules.add(schedule);
	}
	
	public void recordMarks(MarkEntry entry) throws InvalidDataException{
		if(entry.getMarksObtained() < 0 || entry.getMarksObtained() > entry.getAssessment().getMaxMarks()) {
			throw new InvalidDataException("Marks out of range!");
		}
		markEntries.add(entry);
	}
	
	public List<Assessment> getAssessments(){
		return assessments;
	}
	public List<ExamSchedule> getExamSchedules(){
		return schedules;
	}
	public List<MarkEntry> getMarkEntries(){
		return markEntries;
	}
}
