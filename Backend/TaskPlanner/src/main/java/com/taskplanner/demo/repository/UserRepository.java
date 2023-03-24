package com.taskplanner.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskplanner.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByUserEmail(String userEmail);

}
