package com.jwtauth.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "semesters")
public class Semesters {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = false)

	private int seid;
	
private String Semesterpart;


	@Column( length = 6000 )

   private String semestercontent;
	
	
	@Column(unique = false)

	@OneToMany(mappedBy = "semesters",cascade = CascadeType.ALL,orphanRemoval = true)
	 

    private List<Subjects> subjects=new ArrayList<>();
	
	
	private String semesterduration;
	
	
	
	@ManyToOne(cascade=CascadeType.ALL)	
	@JsonIgnore
	private Courses courses;
	
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
	  name ="Teachers_Semesters", 
				
       joinColumns = @JoinColumn(name ="semester_id"), 
	  inverseJoinColumns = @JoinColumn(name ="teacher_id"))
    private List<Teachers> teachers=new ArrayList<>();

	public int getSeid() {
		return seid;
	}

	public void setSeid(int seid) {
		this.seid = seid;
	}
       
	public String getSemestercontent() {
		return semestercontent;
	}

	public void setSemestercontent(String semestercontent) {
		this.semestercontent = semestercontent;
	}

	public String getSemesterduration() {
		return semesterduration;
	}

	public void setSemesterduration(String semesterduration) {
		this.semesterduration = semesterduration;
	}

	
	
	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	public List<Teachers> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teachers> teachers) {
		this.teachers = teachers;
	}

	public String getSemesterpart() {
		return Semesterpart;
	}

	public void setSemesterpart(String semesterpart) {
		this.Semesterpart = semesterpart;
	}

	public List<Subjects> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subjects> subjects) {
		this.subjects = subjects;
	}

}
