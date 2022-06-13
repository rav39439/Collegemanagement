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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;




@Entity
@Table(name = "courses")
public class Courses {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
		@Column(unique = false)

	private int cid;
	private String Coursename;
	private String Courseduration;
	
	  @Column( length = 6000 )
private String Coursetructure;
	private int Coursefees;
	
	@Column(unique = false)
	@OneToMany(mappedBy = "courses",cascade = {CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
	 private List<Semesters> semesters=new ArrayList<>();
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
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
	public List<Semesters> getSemesters() {
		return semesters;
	}
	public void setSemesters(List<Semesters> semesters) {
		this.semesters = semesters;
	}
	@Override
	public String toString() {
		return "Courses [cid=" + cid + ", Coursename=" + Coursename + ", Courseduration=" + Courseduration
				+ ", Coursetructure=" + Coursetructure + ", Coursefees=" + Coursefees + ", semesters=" + semesters
				+ "]";
	}
	
	
}
