package com.jwtauth.web.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jwtauth.web.model.Teachers;

public interface Teacherrepo extends JpaRepository<Teachers, Long> {

	@Query("from Teachers as c where c.id =:Id")
	//currentPage-page
	//Contact Per page - 5
	public Teachers findTeacherById(@Param("Id")long l);
	
	
	@Query("from Teachers as c where c.teachername =:Id")
	//currentPage-page
	//Contact Per page - 5
	public Teachers findTeachById(@Param("Id")String id);
	
	@Query("from Teachers as c where c.coursespec =:Id")
	//currentPage-page
	//Contact Per page - 5
	public List<Teachers> findTeachByCourse(@Param("Id")String id);
	
	@Query("Delete from Teachers as c where c.id =:Id")
	//currentPage-page
	//Contact Per page - 5
	public List<Teachers> deletebytid(@Param("Id")int id);
	
}
