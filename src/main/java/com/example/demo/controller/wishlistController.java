package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.service.blogService;
import com.example.demo.service.categoryService;
import com.example.demo.service.productService;
import com.example.demo.service.userService;

@Controller
public class wishlistController {
	private productService productService;
	@Autowired
	public wishlistController(productService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/show_wishlist")
	public String showWish(HttpSession session, Model model) {
		List<Map<String, Object>> wishlist = (List<Map<String, Object>>) session.getAttribute("wishlist");

		if (wishlist != null) {
			int numberOfItemsInWish = wishlist.size();
			session.setAttribute("numberOfItemsInWish",numberOfItemsInWish);
		}

		return "shop/wishlist";
	}
	

	@PostMapping(value = "/add_to_wishlist")
	public String addToWish(@RequestBody Map<String, Object> data, HttpSession session, Model model) {
		Object idUser = (Object) session.getAttribute("idUser");
		String redirect = null;
		// Kiểm tra xem người dùng đã đăng nhập hay chưa
		if (idUser == null) {
			System.err.println("zds");
			// Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
			redirect = "shop/contact";
		} else {
			redirect = "shop/wishlist";
			String sessionId = UUID.randomUUID().toString().substring(0, 5);

			List<Map<String, Object>> wishlist = (List<Map<String, Object>>) session.getAttribute("wishlist");
			System.err.println("wishlist" + wishlist);
			if (wishlist == null) {
				wishlist= new ArrayList<>();
			}

			boolean exists =wishlist.stream().anyMatch(item -> item.get("id_product").equals(data.get("id_product")));

			if (!exists) {

					data.put("session_id", sessionId);
					wishlist.add(data);
					session.setAttribute("wishlist", wishlist);
				
			}

			System.err.println("wishlist" + wishlist);

		}

		return redirect;
	}

}
