package com.jwtauth.web.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jwtauth.web.model.User;



public interface UserRepository extends JpaRepository<User, Long> {
	
	
	
	@Query("from User as c where c.username =:Id")

	public User findByUsername(@Param("Id")String username); 
	
	
	@Query("from User as c where c.id =:Id")

	public User findById(@Param("Id")int id);


	
	@Query("from User as c where c.mycourse =:Id")
	public List<User> findByCourse(@Param("Id")String coursename); 


}
