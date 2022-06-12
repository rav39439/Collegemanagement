package com.jwtauth.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtauth.web.model.Messages;

public interface Messagerepo extends JpaRepository<Messages, Long> {

}
