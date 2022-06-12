package com.jwtauth.web.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "subjects")
public class Subjects {
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String subject;
	
	
	
@ManyToOne(cascade=CascadeType.ALL)
	
	@JsonIgnore
	private Semesters semesters;	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Semesters getSemesters() {
		return semesters;
	}
	public void setSemesters(Semesters semesters) {
		this.semesters = semesters;
	}

	
	
}
