package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.order;
import com.example.demo.Entity.product;
import com.example.demo.repository.orderRepository;

@Service
public class orderService {
	private orderRepository orderRepository;
	@Autowired
	public orderService(orderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	

	public List<order> getAllOrder(){
		return orderRepository.findAll();
	}
	
	public Page<order> findPage(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber-1, 6);
		return this.orderRepository.findAll(pageable);
	}
	
//delete order
	public void deleteOrder(int idOrder) {
		orderRepository.deleteById(idOrder);;
	}
	
	public void updateOrderStatus(int orderId) {
        order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order id: " + orderId));

        order.setStatus("1");
        orderRepository.save(order);
    }	
}
