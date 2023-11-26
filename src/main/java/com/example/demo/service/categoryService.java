package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.category;
import com.example.demo.Entity.order;
import com.example.demo.Entity.product;
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
    public category getCateById(int cateId) {
	    Optional<category> productOptional = categoryRepository.findById(cateId);
	    return productOptional.orElse(null);
	}

    public void updateCategory(int categoryId, String newName, String newNote) {
        Optional<category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            category category = optionalCategory.get();
            category.setName_category(newName);
            category.setNote(newNote);
            categoryRepository.save(category);
        } else {
            // Xử lý khi không tìm thấy danh mục
        }
    }
    
    public void addCategory(category category, String newName, String newNote) {
        category.setName_category(newName);
        category.setNote(newNote);
        categoryRepository.save(category);
    }
   
}