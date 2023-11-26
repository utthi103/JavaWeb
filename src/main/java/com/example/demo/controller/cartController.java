package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping(value = "/add_to_cart")
    public String addToCart(@RequestBody Map<String, Object> data, HttpSession session, Model model) {
        String sessionId = session.getId();
        List<Map<String, Object>> cart = (List<Map<String, Object>>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean exists = cart.stream()
                .anyMatch(item -> item.get("id_product").equals(data.get("id_product")));

        if (!exists) {
            int qty = Integer.parseInt(data.get("qty").toString());
            int count = Integer.parseInt(data.get("count").toString());

            if (qty <= count) {
                data.put("session_id", sessionId);
                cart.add(data);
                session.setAttribute("cart", cart);
            }
        }

        model.addAttribute("cart", cart);
          System.out.println("Cart: " + cart);
        return "shop/cart"; // Tên của view (HTML) bạn muốn hiển thị
    }
}
