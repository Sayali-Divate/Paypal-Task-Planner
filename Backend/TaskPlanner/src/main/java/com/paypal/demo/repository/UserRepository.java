package com.paypal.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paypal.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
