package com.jwtauth.web.Services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwtauth.web.model.CustomUserDetails;
import com.jwtauth.web.model.User;
import com.jwtauth.web.repo.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 final User user = this.userRepository.findByUsername(username);

	        if (user == null) {
	            throw new UsernameNotFoundException("User not found !!");
	        } else {
	            return new CustomUserDetails(user);
	        }
	}

}
