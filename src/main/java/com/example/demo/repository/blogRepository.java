package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.blog;

public interface blogRepository extends JpaRepository<blog, Integer>{
	
}
