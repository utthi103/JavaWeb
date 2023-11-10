package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.category;
import com.example.demo.Entity.order;
import com.example.demo.repository.categoryRepository;

@Service
public class categoryService {
    private categoryRepository categoryRepository;

    @Autowired
    public categoryService(categoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<category> getAllCategory() {
        return categoryRepository.findAll();
    }
	public Page<category> findPage(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber-1, 4);
		return this.categoryRepository.findAll(pageable);
	}
    
    public void deleteCate(int idCate) {
		categoryRepository.deleteById(idCate);;
	}
}