package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.category;

public interface categoryRepository extends JpaRepository<category, Integer> {
	 category findByIdCategory(int idCate);
	 @Query("SELECT c FROM category c WHERE c.nameCategory = :nameCategory")
	    category findByNameCategory(@Param("nameCategory") String nameCategory);
//	void add(category category);
}
