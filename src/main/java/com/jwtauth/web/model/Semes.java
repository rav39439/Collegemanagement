package com.jwtauth.web.model;

import java.util.List;

public class Semes {

	  public String semesterpart;
      
      public String semestercontent;
      public int semesterduration;
      public List<String> subjects;
	public String getSemesterpart() {
		return semesterpart;
	}
	public void setSemesterpart(String semesterpart) {
		this.semesterpart = semesterpart;
	}
	public String getSemestercontent() {
		return semestercontent;
	}
	public void setSemestercontent(String semestercontent) {
		this.semestercontent = semestercontent;
	}
	public int getSemesterduration() {
		return semesterduration;
	}
	public void setSemesterduration(int semesterduration) {
		this.semesterduration = semesterduration;
	}
	public List<String> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}	
	
	
}
