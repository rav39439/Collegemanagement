package com.jwtauth.web.model;

import java.util.List;

public class Mycourse {
	
	private String Coursename;
	private String Courseduration;
	private String Coursetructure;
	private int Coursefees;

	 private List<Semes> sem;

	public String getCoursename() {
		return Coursename;
	}

	public void setCoursename(String coursename) {
		Coursename = coursename;
	}

	public String getCourseduration() {
		return Courseduration;
	}

	public void setCourseduration(String courseduration) {
		Courseduration = courseduration;
	}

	public String getCoursetructure() {
		return Coursetructure;
	}

	public void setCoursetructure(String coursetructure) {
		Coursetructure = coursetructure;
	}

	public int getCoursefees() {
		return Coursefees;
	}

	public void setCoursefees(int coursefees) {
		Coursefees = coursefees;
	}

	public List<Semes> getSem() {
		return sem;
	}

	public void setSem(List<Semes> sem) {
		this.sem = sem;
	}

	
	
	
}
