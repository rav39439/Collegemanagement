package com.jwtauth.web.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jwtauth.web.model.Courses;

public interface Curserepository extends JpaRepository<Courses, Long>{

	


	@Query("from Courses as c where c.cid =:Id")
	//currentPage-page
	//Contact Per page - 5
	public Courses findCourseById(@Param("Id")int id);
	
	@Query("from Courses as c where c.Coursename =:Id")
	//currentPage-page
	//Contact Per page - 5
	public Courses findCourseByname(@Param("Id")String id);

	}

