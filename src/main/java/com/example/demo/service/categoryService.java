package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.category;
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
}