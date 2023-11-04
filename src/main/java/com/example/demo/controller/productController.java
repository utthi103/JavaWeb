package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.productModel;
import com.example.demo.service.productService;
import java.util.List;

@Controller
public class productController {
    private productService productService;

    @Autowired
    public productController(productService productService) {
        this.productService = productService;
    }

    @GetMapping("/myshop")
    public String myshop(Model model) {
        List<productModel> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        return "shop/myshop";
    }
}