package com.jwtauth.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "teachers")
public class Teachers {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;
	private String coursespec;
	private String semesterspec;
	private String workexp;
	private String teacherdegree;
	
	private String teacherskills;
	
	private String teachername;
	
	

	@ManyToMany (cascade = {CascadeType.ALL},mappedBy="teachers")
	@JsonIgnore
	private List<Semesters> semesters=new ArrayList<>();
	
	
	
	
	
	
	
	public long getTid() {
		return id;
	}
	public void setTid(long tid) {
		this.id = tid;
	}
	public List<Semesters> getSemesters() {
		return semesters;
	}
	public void setSemesters(List<Semesters> semesters) {
		this.semesters = semesters;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public String getWorkexp() {
		return workexp;
	}
	public void setWorkexp(String workexp) {
		this.workexp = workexp;
	}
	public String getTeacherdegree() {
		return teacherdegree;
	}
	public void setTeacherdegree(String teacherdegree) {
		this.teacherdegree = teacherdegree;
	}
	public String getTeacherskills() {
		return teacherskills;
	}
	public void setTeacherskills(String teacherskills) {
		this.teacherskills = teacherskills;
	}
	
	public String getCoursespec() {
		return coursespec;
	}
	public void setCoursespec(String coursespec) {
		this.coursespec = coursespec;
	}
	public String getSemesterspec() {
		return semesterspec;
	}
	public void setSemesterspec(String semesterspec) {
		this.semesterspec = semesterspec;
	}
	
}
