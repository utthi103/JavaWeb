package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.order;
import com.example.demo.Entity.product;

public interface orderRepository extends JpaRepository<order, Integer> {
}
