package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Entity.order;
import com.example.demo.Entity.order_detail;
import com.example.demo.Entity.user;
import com.example.demo.Entity.category;

import com.example.demo.service.categoryService;
import com.example.demo.service.orderService;
import com.example.demo.service.order_detailServicce;
import com.example.demo.service.userService;

@Controller
public class adminController {
	private orderService orderService;
	private order_detailServicce order_detailService;
	private userService userService;
	private categoryService categoryService;

	@Autowired
	public adminController(orderService orderService, order_detailServicce order_detailServicce,userService userService,
			categoryService categoryService) {
		this.orderService = orderService;
		this.order_detailService = order_detailServicce;
		this.userService = userService;
		this.categoryService = categoryService;
	}

//		order
	@GetMapping("/admin/order")
	public String order(Model model) {

		return orderpaginate(model, 1);
	}

	@GetMapping("/admin/order/page/{pageNumber}")
	public String orderpaginate(Model model, @PathVariable("pageNumber") int currentPage) {
		Page<order> page = orderService.findPage(currentPage);
		int totalPage = page.getTotalPages();
		long totalItem = page.getTotalElements();
		List<order> orderList = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalItem", totalItem);
		model.addAttribute("orders", orderList);

		return "admin/order/order";
	}

	@GetMapping("/admin/order/detail/{id}")
	public String detailOrder(Model model, @PathVariable("id") int orderID) {
		List<order_detail> orderwithID = order_detailService.getorderDetail(orderID);
		model.addAttribute("orderwithID", orderwithID);
		return "admin/order/order_detail";
	}
//	delete order
	
	@GetMapping("/admin/order/delete/{id}")
	public String deleteOrder(@PathVariable("id") int orderID,RedirectAttributes redirectAttributes) {
		 orderService.deleteOrder(orderID);
		 redirectAttributes.addFlashAttribute("message", "Order deleted successfully");
		 return "redirect:/admin/order";
	}
	
	@GetMapping("/admin/order/accpect/{id}")
	public String accpectOrder(@PathVariable("id") int orderID) {
		orderService.updateOrderStatus(orderID);
		return "redirect:/admin/order";
	}
	
//	manage user
	
	@GetMapping("/admin/user")
	public String user(Model model) {
		List<user> user = userService.getAllBlog();
		model.addAttribute("user", user);
		return "admin/user";
	}
	

	@GetMapping("/admin/user/delete/{id}")
	public String deleteUser(@PathVariable("id") int userId,RedirectAttributes redirectAttributes) {
		 userService.deleteUser(userId);
		 redirectAttributes.addFlashAttribute("message", "Order deleted successfully");
		 return "redirect:/admin/user";
	}
	
	
//	manage category
	/*
	 * @GetMapping("/admin/category") public String category(Model model) {
	 * List<category> category = categoryService.getAllCategory();
	 * model.addAttribute("category", category); return "admin/category/category"; }
	 */
	
	@GetMapping("/admin/category")
	public String category(Model model) {

		return catepaginate(model, 1);
	}

	@GetMapping("/admin/category/page/{pageNumber}")
	public String catepaginate(Model model, @PathVariable("pageNumber") int currentPage) {
		Page<category> page = categoryService.findPage(currentPage);
		int totalPage = page.getTotalPages();
		long totalItem = page.getTotalElements();
//		List<category> cateList = page.getContent();
		List<category> category = page.getContent();
		 model.addAttribute("category", category);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalItem", totalItem);
//		model.addAttribute("cateList", cateList);

		return "admin/category/category";
	}

	
	  @GetMapping("/admin/category/delete/{id}") public String
	  deleteCate(@PathVariable("id") int cateID,RedirectAttributes
	  redirectAttributes) { categoryService.deleteCate(cateID);
	  redirectAttributes.addFlashAttribute("message",
	  "Order deleted successfully"); return "redirect:/admin/category"; }
	 
}
