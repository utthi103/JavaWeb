package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.user;
import com.example.demo.Entity.order;
import com.example.demo.Entity.order_detail;
import com.example.demo.service.blogService;
import com.example.demo.service.categoryService;
import com.example.demo.service.orderService;
import com.example.demo.service.order_detailServicce;
import com.example.demo.service.productService;
import com.example.demo.service.userService;

@Controller
public class paymentController {
	private productService productService;
	private categoryService categoryService;
	private blogService blogService;
	private userService userService;
	private orderService orderService;
	private order_detailServicce order_detailServicce;
	

	@Autowired
	public paymentController(productService productService, categoryService categoryService, blogService blogService,
			userService userService, orderService orderService,order_detailServicce order_detailServicce) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.blogService = blogService;
		this.userService = userService;
		this.orderService = orderService;
		this.order_detailServicce = order_detailServicce;
	}
	
	@GetMapping("/show_checkout")
	public String show(Model model, HttpSession session) {
			int idUser =(int) session.getAttribute("idUser");
			user user = userService.getUserById(idUser);
			model.addAttribute("user", user);
		return "shop/checkout";
	}
	
	@PostMapping(value = "/place_order")
	public String order(Model model, HttpSession session,
//			 @PathVariable("cateID") int categoryId,
	            @RequestParam("first_name") String first_name,
	            @RequestParam("last_name") String last_name,
	            @RequestParam("address") String address,
	            @RequestParam("phone") String phone, 
	            @RequestParam("email") String email, 
	            @RequestParam("note") String note,
	            @RequestParam("pay") String pay
			) {
int  idUser = (int) session.getAttribute("idUser");
		String totalValue = (String)session.getAttribute("total");
		Float total = Float.parseFloat(totalValue);
			System.out.println(total);
			String status = "0"; 
		 order order = new order();
	  orderService.addOrder(order, idUser, first_name, last_name, phone, address, pay, total, status, note);
	  int id_order = order.getId_order();
	  System.out.println(id_order);
	  
	  List<Map<String, Object>> cart = (List<Map<String, Object>>) session.getAttribute("cart");

	  if (cart != null) {
	      for (Map<String, Object> item : cart) {
	          // Retrieve values from the item map
	          int idProduct = (int) item.get("id_product");
	          String nameProduct = (String) item.get("name_product");
	          
	          // Convert qty and price_product to appropriate types
	          int qty = Integer.parseInt((String) item.get("qty"));
	          float priceProduct = Float.parseFloat(item.get("price_product").toString());
	          
	          // Calculate the price
	          float price = qty * priceProduct;
	          
	          // Create and save order_detail
	          order_detail orderDetail = new order_detail();
	          order_detailServicce.addOrderdetail(orderDetail, id_order, idProduct, nameProduct, qty, price);
	      }
	      
	      for (Map<String, Object> item : cart) {
	    	  int idProduct = (int) item.get("id_product");
	    	  int qty =Integer.parseInt((String) item.get("qty"));
	    	  productService.updateCount(idProduct, qty);
	      }
	      
	      session.setAttribute("cart", null);
	      session.setAttribute("numberOfItemsInCart", 0);
	  }

	  return "redirect:/show_cart";
	}
}
