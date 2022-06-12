package com.jwtauth.web.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usersemresult1")
public class Usersemres1 {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rid", unique = true, length = 128)

	private int rid;
	private String sub;
	private int marks;
	private String coursename;
	private String semname;
	


@ManyToOne(cascade=CascadeType.ALL)
	
	@JsonIgnore
	private Usersem usersem;
	
	
	
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public Usersem getUsersem() {
		return usersem;
	}
	public void setUsersem(Usersem usersem) {
		this.usersem = usersem;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	
	public String getSemname() {
		return semname;
	}
	public void setSemname(String semname) {
		this.semname = semname;
	}
}
