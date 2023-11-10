package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.order;
import com.example.demo.Entity.order_detail;
import com.example.demo.Entity.product;
import com.example.demo.repository.orderRepository;
import com.example.demo.repository.order_detailRepository;

@Service
public class order_detailServicce {
	private order_detailRepository order_detailRepository;

	@Autowired
	public order_detailServicce(order_detailRepository order_detailRepository) {
		this.order_detailRepository = order_detailRepository;
	}

	public List<order_detail> getAllOrder() {
		return order_detailRepository.findAll();
	}

	public List<order_detail> getorderDetail(int orderId) {
		List<order_detail> orderOptional = order_detailRepository.findByIdOrder(orderId);
		return orderOptional;
	}

}
