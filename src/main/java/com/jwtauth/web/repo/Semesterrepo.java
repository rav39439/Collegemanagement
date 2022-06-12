package com.jwtauth.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jwtauth.web.model.Semesters;

public interface Semesterrepo extends JpaRepository<Semesters, Long> {

	
	@Query("from Semesters as c where c.Semesterpart =:part")
	//currentPage-page
	//Contact Per page - 5
	public Semesters findSemesterById(@Param("part")String id);
	
	
	
}
