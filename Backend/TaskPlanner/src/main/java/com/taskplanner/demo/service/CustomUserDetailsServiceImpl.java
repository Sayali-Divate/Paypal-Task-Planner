package com.taskplanner.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taskplanner.demo.entity.User;
import com.taskplanner.demo.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOptional = userRepository.findByUserEmail(username);
		
		if(userOptional.isPresent()) {
			
			User user = userOptional.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword(), authorities);
		}
		
		else throw new UsernameNotFoundException("The email id is not registered");
	}

}
