package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.category;
import com.example.demo.Entity.product;
import com.example.demo.repository.productRepository;

import java.util.List;
import java.util.Optional;

@Service
public class productService {
    private productRepository productRepository;

    @Autowired
    public productService(productRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<product> getAllProducts() {
        return productRepository.findAll();
    }


	public Page<product> findPage(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber-1, 6);
		return this.productRepository.findAll(pageable);
	}
//	detail 
	public product getProductById(int productId) {
	    Optional<product> productOptional = productRepository.findById(productId);
	    return productOptional.orElse(null);
	}
	
	public List<product> getHighlightedProducts() {
        return productRepository.findByOutstandProduct("có");
    }
	public List<product> newProduct() {
        return productRepository.findTop4ByOrderByDateProductDesc();
    }
	
	 public void updateCount(int id_product, int qty) {
		 product product = productRepository.findById_product(id_product);
	        if (product != null) {
	            product.setCount_product(product.getCount_product() - qty);
	            productRepository.save(product);
	        }
	    }
}