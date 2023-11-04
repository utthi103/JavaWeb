package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.productModel;
import com.example.demo.repository.productRepository;

import java.util.List;

@Service
public class productService {
    private productRepository productRepository;

    @Autowired
    public productService(productRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<productModel> getAllProducts() {
        return productRepository.getAllProducts();
    }
//    paginate	
//    Page<productModel> getAll(Integer page) {
//    	Pageable pageable = PageRequest.of(page-1, 3);
//		return productRepository.findAll;
//	}
}