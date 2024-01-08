package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Entity.category;
import com.example.demo.Entity.order;
import com.example.demo.Entity.order_detail;
import com.example.demo.Entity.product;
import com.example.demo.service.blogService;
import com.example.demo.service.categoryService;
import com.example.demo.service.orderService;
import com.example.demo.service.order_detailServicce;
import com.example.demo.service.productService;
import com.example.demo.service.userService;
@Controller
public class myaccountController {
	private productService productService;
	private categoryService categoryService;
	private blogService blogService;
	private userService userService;
	private orderService orderService;
	private order_detailServicce order_detailServicce;
	

	@Autowired
	public myaccountController(productService productService, categoryService categoryService, blogService blogService,
			userService userService,orderService orderService, order_detailServicce order_detailServicce ) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.blogService = blogService;
		this.userService = userService;
		this.orderService = orderService;
		this.order_detailServicce = order_detailServicce;
	}

	@GetMapping("/history")
	public String history(Model model, HttpSession sesion) {
		 List<category> categoryList = categoryService.getAllCategory();
	        model.addAttribute("categories", categoryList);
	        String idUserString = sesion.getAttribute("idUser").toString();
	        System.out.println(idUserString);
	        int idUser = Integer.parseInt(idUserString);
		List<order> results = orderService.findOrdersByIdUserAndStatus(idUser, "1");
		List<order> oresults = orderService.findOrdersByIdUserAndStatus(idUser, "0");
		model.addAttribute("order", results);
		model.addAttribute("nostatus", oresults);

		return "shop/history";
	}
	@GetMapping("/history_detail/{id}")
	public String historyDetail(Model model, @PathVariable("id") int  orderID) {
		List<category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categories", categoryList);
		List<order_detail> results = order_detailServicce.getOrderDetailsByIdOrderUsingQuery(orderID);
		model.addAttribute("orderdetail", results);
		return "shop/history_detail";
	}
	
	
	
}
