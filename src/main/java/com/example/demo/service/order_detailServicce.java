package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.category;
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
	
	  public void addOrderdetail(order_detail order_detail, 
			  int id_order,
			  int id_product,
			  String name_product,
			  int count,
			  Float price
			  ) {
	        order_detail.setId_order(id_order);
	        order_detail.setId_product(id_product);
	       order_detail.setName_product(name_product);
	       order_detail.setCount(count);
	       order_detail.setPrice(price);
	        order_detailRepository.save(order_detail);
	    }

}
