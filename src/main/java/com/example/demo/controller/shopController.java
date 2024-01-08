package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.blog;
import com.example.demo.Entity.category;
import com.example.demo.Entity.product;
import com.example.demo.Entity.user;
import com.example.demo.service.blogService;
import com.example.demo.service.categoryService;
import com.example.demo.service.productService;
import com.example.demo.service.userService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

@Controller
public class shopController {
	private productService productService;
	private categoryService categoryService;
	private blogService blogService;
	private userService userService;

	@Autowired
	public shopController(productService productService, categoryService categoryService, blogService blogService,
			userService userService) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.blogService = blogService;
		this.userService = userService;
	}

	@GetMapping("/user")
	public String login() {
		return "layout/login";
	}

	@PostMapping("/user/login")
    public String check(@RequestParam("account_user") String username,
            @RequestParam("pass_user") String pass, Model model, HttpSession session) throws NoSuchAlgorithmException {
     
        MessageDigest md;
		
			md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
	        byte[] digest = md.digest();
	        String myHash = DatatypeConverter
	                .printHexBinary(digest).toUpperCase();
	        user user = userService.isValidUser(username, myHash);

	        if (user!=null) {
	            // Đăng nhập thành công, thực hiện các thao tác cần thiết
	        	 String name = user.getAccountUser();
	        	 session.setAttribute("username", user.getAccountUser());
	        	 session.setAttribute("idUser", user.getId_user());
	            return "redirect:/";
	        } else {
	            // Xử lý lỗi đăng nhập không hợp lệ
	            model.addAttribute("error", "Invalid username or password");
	            return "redirect:/user";
	        }
	}
		@GetMapping("/user/logout")
		public String logout(HttpSession session) {
			 session.setAttribute("username", null);
			 session.setAttribute("idUser", null);
			 session.setAttribute("total", null);
			 session.setAttribute("numberOfItemsInCart", null);
			 session.setAttribute("cart", null);
			return "redirect:/";
		}
		        
      
    

	@GetMapping("/")
	public String home(Model model) {
		List<product> productList = productService.getAllProducts();
		List<category> categoryList = categoryService.getAllCategory();
		List<product> getHighlightedPdList = productService.getHighlightedProducts();
		List<product> newProduct = productService.newProduct();
		List<blog> blogList = blogService.getAllBlog();

		model.addAttribute("products", productList);
		model.addAttribute("categories", categoryList);
		model.addAttribute("getHighlightedPdList", getHighlightedPdList);
		model.addAttribute("newProductList", newProduct);
		model.addAttribute("blogList", blogList);
		return "shop/shop";
	}

	@GetMapping("/myshop")
	public String myshop(Model model) {

		return myshoppaginate(model, 1);
	}

	@GetMapping("/myshop/page/{pageNumber}")
	public String myshoppaginate(Model model, @PathVariable("pageNumber") int currentPage) {
		Page<product> page = productService.findPage(currentPage);
		int totalPage = page.getTotalPages();
		long totalItem = page.getTotalElements();
		List<product> productList = page.getContent();
		List<category> categoryList = categoryService.getAllCategory();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalItem", totalItem);
		model.addAttribute("products", productList);
		model.addAttribute("categories", categoryList);
		return "shop/myshop";
	}

	@GetMapping("/myshop/detail/{id}")
	public String detailProduct(Model model, @PathVariable("id") int productID) {
		product prwithID = productService.getProductById(productID);
		List<product> productList = productService.getAllProducts();
		List<category> categoryList = categoryService.getAllCategory();
		model.addAttribute("productbyId", prwithID);
		model.addAttribute("products", productList);
		model.addAttribute("categories", categoryList);
		return "shop/shopDetail";

	}

//    contact
	@GetMapping("/contact")
	public String contact() {
		return "shop/contact";
	}
	
	@PostMapping("/myshop/search_name")
	public String searchName(Model model,@RequestParam("name_product") String name_product) {
		List<product> results = productService.searchProducts(name_product);
        model.addAttribute("nameProduct", results);
        List<category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categories", categoryList);
        for (product product : results) {
            System.out.println(product.getName_product());
        }
    	return "shop/myshop";
	}
	

	@GetMapping("/myshop/search_category/{cateName}")
	public String searchCate(Model model,
			@PathVariable("cateName") String cateName) {
		List<product> results = productService.searchNameCate(cateName);
        model.addAttribute("nameCate", results);
        List<category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categories", categoryList);
//        for (product product : results) {
//            System.out.println(product.getName_product());
//        }
    	return "shop/myshop";
	}
	
	@PostMapping("/myshop/search_price")
	public String searchPrice(Model model
			,@RequestParam("min_price") String min_price,
			@RequestParam("max_price") String max_price
			) {
		   // Tách giá trị sau ký tự '$'
	    String[] minArray = min_price.split("\\$");
	    String[] maxArray = max_price.split("\\$");

	    // Lấy giá trị sau ký tự '$'
	    String minPrice = minArray[1];
	    String maxPrice = maxArray[1];

	    // Chuyển đổi giá trị sang kiểu dữ liệu phù hợp (nếu cần)
	    Float minPriceFloat = Float.parseFloat(minPrice);
	    Float maxPriceFloat = Float.parseFloat(maxPrice);

		List<product> results = productService.searchPrice(minPriceFloat,maxPriceFloat );
        model.addAttribute("nameProduct", results);
        List<category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categories", categoryList);
        for (product product : results) {
            System.out.println(product.getName_product());
        }
		System.out.println(maxPrice);
    	return "shop/myshop";
	}
}