package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.OrderDetail;
import com.example.demo.Entity.order;
import com.example.demo.Entity.order_detail;

public interface order_detailRepository extends JpaRepository<order_detail, Long> {

	List<order_detail> findByIdOrder(int orderId);
	
	  @Query("SELECT od FROM order_detail od WHERE od.idOrder = :idOrder")
	    List<order_detail> findOrderDetailsByIdOrder(@Param("idOrder") int idOrder);

}
