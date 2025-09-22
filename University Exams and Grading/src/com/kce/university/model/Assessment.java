package com.kce.university.model;

public class Assessment {
	private String name;
	private double weightage;
	private double maxMarks;
	
	public Assessment(String name, double weightage, double maxmarks) {
		this.name=name;
		this.weightage=weightage;
		this.maxMarks=maxmarks;
	}
	
	public String getName() {
		return name;
	}
	
	public double getWeightage() {
		return weightage;
	}
	
	public double getMaxMarks() {
		return maxMarks;
	}
	
	@Override
	public String toString() {
		return name + "(Weight: " + weightage + "%, Max: " + maxMarks + ")";
	}

}
