package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Entity.blog;
import com.example.demo.Entity.category;
import com.example.demo.Entity.product;
import com.example.demo.service.blogService;
import com.example.demo.service.categoryService;
import com.example.demo.service.productService;

@Controller
public class blogController {
	private productService productService;
    private categoryService categoryService;
    private blogService blogService;
    
    @Autowired
    public blogController(productService productService, categoryService categoryService, blogService blogService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.blogService = blogService;
    }
    
    @GetMapping("/blog")
    public String myshop(Model model) {
  
      return myblogpaginate(model, 1);
  }
    
    @GetMapping("/blog/page/{pageNumber}")
    public String myblogpaginate(Model model, @PathVariable("pageNumber") int currentPage) {
    	Page<blog> page  = blogService.findPage(currentPage);
    			int totalPage = page.getTotalPages();
    	long totalItem = page.getTotalElements();
        List<blog> blogList = page.getContent();
        List<category> categoryList = categoryService.getAllCategory();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("blogList", blogList);
        model.addAttribute("categories", categoryList);

        return "shop/blog";
    }
    
    @GetMapping("/blog/detail/{id}")
    public String detailProduct(Model model,@PathVariable("id") int blogId) {
        blog blogwithID = blogService.getBlogById(blogId);
        List<blog> blogList = blogService.getAllBlog();
        List<category> categoryList = categoryService.getAllCategory();
        model.addAttribute("blogById", blogwithID);
        model.addAttribute("blogList", blogList);
        model.addAttribute("categories", categoryList);
		return "shop/blogDetail";
    	
    }
    
    
}
