package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.order;
import com.example.demo.Entity.product;

public interface orderRepository extends JpaRepository<order, Integer> {
//	void deleteById(int idOrder);
//	 Optional<order> findById_order(int orderId);
	@Query("SELECT o FROM order o WHERE o.id_user = :idUser AND o.status = :status")
    List<order> findOrdersByIdUserAndStatus(@Param("idUser") int idUser, @Param("status") String status);
}
