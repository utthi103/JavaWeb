package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.blogService;
import com.example.demo.service.categoryService;
import com.example.demo.service.userService;
import com.example.demo.service.productService;

@Controller
public class cartController {
	private productService productService;
	private categoryService categoryService;
	private blogService blogService;
	private userService userService;

	@Autowired
	public cartController(productService productService, categoryService categoryService, blogService blogService,
			userService userService) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.blogService = blogService;
		this.userService = userService;
	}
	
	@GetMapping("/show_cart")
	public String showcart( HttpSession session, Model model) {
  	    List<Map<String, Object>> cart = (List<Map<String, Object>>) session.getAttribute("cart");
  	  double total = calculateTotal(cart);
	    System.err.println(total);
	    session.setAttribute("total", String.format("%.1f", total));
	    if(cart!=null) {
	    	 int numberOfItemsInCart = cart.size();
	 	    session.setAttribute("numberOfItemsInCart", numberOfItemsInCart);
	    }
	   

	    return "shop/cart";
	}
	

	
	@PostMapping(value = "/add_to_cart")
	public String addToCart( @RequestBody Map<String, Object> data,HttpSession session, Model model) {
	    Object idUser = (Object) session.getAttribute("idUser");
	    String redirect = null;
	    // Kiểm tra xem người dùng đã đăng nhập hay chưa
	    if (idUser == null) {
	    	System.err.println("zds");
	        // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
	    	redirect = "shop/contact";
	    }else {
	    	redirect = "shop/cart";
	    	   String sessionId = session.getId();
	   	    List<Map<String, Object>> cart = (List<Map<String, Object>>) session.getAttribute("cart");
	   	    System.err.println("cart" + cart);
	   	    if (cart == null) {
	   	        cart = new ArrayList<>();
	   	    }

	   	    boolean exists = cart.stream().anyMatch(item -> item.get("id_product").equals(data.get("id_product")));

	   	    if (!exists) {
	   	        int qty = Integer.parseInt(data.get("qty").toString());
//	   	     int qty = (int)(data.get("qty"));
	   	        int count = Integer.parseInt(data.get("count").toString());
//	   	        int count = (int)(data.get("count"));

	   	        if (qty <= count) {
	   	            data.put("session_id", sessionId);
	   	            cart.add(data);
	   	            session.setAttribute("cart", cart);
	   	        }
	   	    }

	   	    System.err.println("cart" + cart);
	   	   
	    }

	    return redirect;
	}




	public double calculateTotal(List<Map<String, Object>> cartList) {
		if (cartList==null) {
			return 0;
		}else {
			return cartList.stream().mapToDouble(cart -> Double.parseDouble(cart.get("qty").toString())
					* Double.parseDouble(cart.get("price_product").toString())).sum();
		}

		
	}

	@GetMapping("/deleteCart/{id}")
	public String deleteCart(@PathVariable("id") String cartID, HttpSession session) {
	    List<Map<String, Object>> cart = (List<Map<String, Object>>) session.getAttribute("cart");
	    System.err.println(cartID);
	    System.err.println("Before deletion: " + cart);

	    if (cart != null) {
	        Iterator<Map<String, Object>> iterator = cart.iterator();
	        while (iterator.hasNext()) {
	            Map<String, Object> item = iterator.next();
	            if (item.get("session_id").equals(cartID)) {
	                iterator.remove();
	                break;
	            }
	        }

	        session.setAttribute("cart", cart);
	        System.err.println("After deletion: " + cart);
	    }

	    return "redirect:/show_cart";
	}

}
