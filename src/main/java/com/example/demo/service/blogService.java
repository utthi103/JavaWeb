package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.blog;
import com.example.demo.Entity.category;
import com.example.demo.Entity.product;
import com.example.demo.repository.blogRepository;
import com.example.demo.repository.categoryRepository;
@Service
public class blogService {

    private blogRepository blogRepository;

    @Autowired
    public blogService(blogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<blog> getAllBlog() {
        return blogRepository.findAll();
    }
    
	public Page<blog> findPage(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber-1, 2);
		return this.blogRepository.findAll(pageable);
	}
	
//	detail
	public blog getBlogById(int blogId) {
	    Optional<blog> blogOptional = blogRepository.findById(blogId);
	    return blogOptional.orElse(null);
	}
}