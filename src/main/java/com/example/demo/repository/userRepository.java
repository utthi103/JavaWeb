package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.user;

public interface userRepository extends JpaRepository<user, Integer> {
	
	 user findByAccountUserAndPassUser(String accountUser, String passUser);
}
