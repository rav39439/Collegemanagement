package com.jwtauth.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usersem")
public class Usersem {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "semid", unique = true, length = 128)

	private int semid;
	private int noofexam;
	private int credit;
	
	private String feepaid;
	private String mysemname;
	
@ManyToOne(cascade=CascadeType.ALL)
	
	@JsonIgnore
	private User persons;	
	
	


	@OneToMany(mappedBy = "usersem",cascade = CascadeType.ALL,orphanRemoval = true)
	 

    private List<Usersemres1> result=new ArrayList<>();


	public int getSemid() {
		return semid;
	}


	public void setSemid(int semid) {
		this.semid = semid;
	}


	public int getNoofexam() {
		return noofexam;
	}


	public void setNoofexam(int noofexam) {
		this.noofexam = noofexam;
	}


	public int getCredit() {
		return credit;
	}


	public void setCredit(int credit) {
		this.credit = credit;
	}


	


	public User getPersons() {
		return persons;
	}


	public void setPersons(User persons) {
		this.persons = persons;
	}


	public List<Usersemres1> getResult() {
		return result;
	}


	public void setResult(List<Usersemres1> result) {
		this.result = result;
	}
	public String getMysemname() {
		return mysemname;
	}


	public void setMysemname(String mysemname) {
		this.mysemname = mysemname;
	}


	public String getFeepaid() {
		return feepaid;
	}


	public void setFeepaid(String feepaid) {
		this.feepaid = feepaid;
	}
	

}
