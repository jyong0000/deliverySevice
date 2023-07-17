package com.deliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliveryservice.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
