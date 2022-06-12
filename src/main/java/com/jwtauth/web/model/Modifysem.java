package com.jwtauth.web.model;

import java.util.ArrayList;
import java.util.List;

public class Modifysem {
	
	
	 String tname;
	 
	 String coursename;

	List<String> sem=new ArrayList<>();
	String semester;
	int Scode;
	List<String> teachers=new ArrayList<>();

	
	
	
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public List<String> getSem() {
		return sem;
	}
	public void setSem(List<String> sem) {
		this.sem = sem;
	}
	public int getScode() {
		return Scode;
	}
	public void setScode(int scode) {
		Scode = scode;
	}
	public List<String> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<String> teachers) {
		this.teachers = teachers;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	

}
