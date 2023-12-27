package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.product;

@Repository
public interface productRepository extends JpaRepository<product, Integer > {
	
	List<product> findByOutstandProduct(String outstandProduct);
	 @Query("SELECT p FROM product p WHERE p.id_product = :id_product")
	    product findById_product(@Param("id_product") int id_product);
    @Query("SELECT p FROM product p ORDER BY p.dateProduct DESC")
    List<product> findTop4ByOrderByDateProductDesc();
    
}