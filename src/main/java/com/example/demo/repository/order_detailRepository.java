package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.order_detail;

public interface order_detailRepository extends JpaRepository<order_detail, Integer> {

	List<order_detail> findByIdOrder(int orderId);

}
