package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.category;

public interface categoryRepository extends JpaRepository<category, Integer> {
	 category findByIdCategory(int idCate);

//	void add(category category);
}
