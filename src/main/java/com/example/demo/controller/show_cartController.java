package com.example.demo.controller;

import org.springframework.http.MediaType;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.blogService;
import com.example.demo.service.categoryService;
import com.example.demo.service.userService;
import com.example.demo.service.productService;

@Controller 
public class show_cartController {
		private productService productService;
		private categoryService categoryService;
		private blogService blogService;
		private userService userService;

		@Autowired
		public show_cartController(productService productService, categoryService categoryService, blogService blogService,
				userService userService) {
			this.productService = productService;
			this.categoryService = categoryService;
			this.blogService = blogService;
			this.userService = userService;
		}
		
		@GetMapping("/show_cart")
		public String showcart() {
//			System.out.println(data); 
			return "shop/cart";
		}
}
