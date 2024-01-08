package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Entity.order;
import com.example.demo.Entity.order_detail;
import com.example.demo.Entity.product;
import com.example.demo.Entity.user;
import com.example.demo.Entity.category;

import com.example.demo.service.categoryService;
import com.example.demo.service.orderService;
import com.example.demo.service.order_detailServicce;
import com.example.demo.service.productService;
import com.example.demo.service.userService;

@Controller
public class adminController {
	private orderService orderService;
	private order_detailServicce order_detailService;
	private userService userService;
	private categoryService categoryService;
	private productService productService;

	@Autowired
	public adminController(orderService orderService, order_detailServicce order_detailServicce,
			userService userService, categoryService categoryService, productService productService) {
		this.orderService = orderService;
		this.order_detailService = order_detailServicce;
		this.userService = userService;
		this.categoryService = categoryService;
		this.productService = productService;
	}

//	product
	@GetMapping("/admin/product")
	public String product(Model model) {

		return productpaginate(model, 1);
	}

	@GetMapping("/admin/product/page/{pageNumber}")
	public String productpaginate(Model model, @PathVariable("pageNumber") int currentPage) {
		Page<product> page = productService.findPage(currentPage);
		int totalPage = page.getTotalPages();
		long totalItem = page.getTotalElements();
		List<product> productList = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalItem", totalItem);
		model.addAttribute("products", productList);

		return "admin/product/product";
	}

	@GetMapping("/admin/product/show/{id}")
	public String formeditProduct(Model model, @PathVariable("id") int productID) {
		List<category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories);

		product productwithID = productService.getProductById(productID);
		model.addAttribute("productwithID", productwithID);

		String nameCate = "";
		for (category category : categories) {
			if (category.getidCategory() == productwithID.getCategoryEntity().idCategory) {
				nameCate = category.nameCategory;
				break;
			}
		}
		model.addAttribute("nameCate", nameCate);

		return "admin/product/edit";
	}

	@PostMapping("/admin/product/edit/{productID}")
	public String updateProduct(@PathVariable("productID") int productID,
			@RequestParam("name_product") String name_product, @RequestParam("name_category") String name_category,

			@RequestParam("price_product") Float price_product,

			@RequestParam("count_product") int count_product, @RequestParam("image1") MultipartFile image1new,
			@RequestParam("image2") MultipartFile image2new, @RequestParam("image3") MultipartFile image3new,
			@RequestParam("decription_product") String decription_product,
			@RequestParam("decription") String decription, @RequestParam("sale_product") int sale_product,
			@RequestParam("outstand_product") String outstand_product, Float price_sale, Model model) {
		product productwithID = productService.getProductById(productID);
		Date date = productwithID.getDateProduct();
		price_sale = price_product - (price_product * sale_product / 100);
		String image1 = handleImage(image1new, productwithID.getImage1());
		String image2 = handleImage(image2new, productwithID.getImage2());

		String image3 = handleImage(image3new, productwithID.getImage3());
		productService.updateProduct(productID, name_product, name_category, price_product, count_product, image1,
				image2, image3, decription_product, decription, sale_product, outstand_product, date, price_sale);

		return "redirect:/admin/product";
	}

	@Value("${upload.path}")
	private String uploadPath;
	
	private String handleImage(MultipartFile newImage, String oldImage) {
		if (newImage != null && !newImage.isEmpty()) {
			try {
				// Lưu tệp mới vào thư mục uploadPath
				String fileName = newImage.getOriginalFilename();
				String uploadDir = uploadPath; // sử dụng đường dẫn đầy đủ

				// Lưu tệp mới vào thư mục
				Path filePath = Paths.get(uploadDir, fileName);
				Files.copy(newImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Upload Path: " + uploadPath);
				System.out.println("File Name: " + fileName);
				System.out.println("File Path: " + filePath);
				return fileName;
			} catch (IOException e) {
				e.printStackTrace(); // Thêm xử lý ngoại lệ phù hợp cho ứng dụng của bạn
			}
		}

		// Nếu không có tệp mới, giữ nguyên tên tệp cũ
		return oldImage;
	}

	@GetMapping("/admin/product/formAdd")
	public String forAddProduct(Model model) {
		List<category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories);

		return "admin/product/add";
	}

	@PostMapping("/admin/product/add")
	public String addProduct(@RequestParam("name_product") String name_product,
			@RequestParam("name_category") String name_category, @RequestParam("price_product") Float price_product,

			@RequestParam("count_product") int count_product, @RequestParam("image1") MultipartFile image1new,
			@RequestParam("image2") MultipartFile image2new, @RequestParam("image3") MultipartFile image3new,
			@RequestParam("decription_product") String decription_product,
			@RequestParam("decription") String decription, @RequestParam("sale_product") int sale_product,
			@RequestParam("outstand_product") String outstand_product, Float price_sale) {
		product product = new product();
		price_sale = price_product - (price_product * sale_product / 100);
		Date currentTime = new Date();
		String oldImg = "";
		String image1 = handleImage(image1new, oldImg);
		// Xử lý ảnh 2
		String image2 = handleImage(image2new, oldImg);
		// Xử lý ảnh 3
		String image3 = handleImage(image3new, oldImg);
		productService.addproduct(product, name_product, name_category, price_product, count_product, image1, image2,
				image3, decription_product, decription, sale_product, outstand_product, currentTime, price_sale);
		return "redirect:/admin/product";
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

	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") int productID, RedirectAttributes redirectAttributes) {
		productService.deleteProduct(productID);
		redirectAttributes.addFlashAttribute("message", "Product deleted successfully");
		return "redirect:/admin/product";
	}

//	delete order

	@GetMapping("/admin/order/delete/{id}")
	public String deleteOrder(@PathVariable("id") int orderID, RedirectAttributes redirectAttributes) {
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
	public String deleteUser(@PathVariable("id") int userId, RedirectAttributes redirectAttributes) {
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

	@GetMapping("/admin/category/delete/{id}")
	public String deleteCate(@PathVariable("id") int cateID, RedirectAttributes redirectAttributes) {
		categoryService.deleteCate(cateID);
		redirectAttributes.addFlashAttribute("message", "Order deleted successfully");
		return "redirect:/admin/category";
	}

//	  edit category
	@GetMapping("/admin/category/formedit/{id}")
	public String formeditCate(Model model, @PathVariable("id") int cateId) {
		category catewithID = categoryService.getCateById(cateId);
		model.addAttribute("catewithID", catewithID);
		return "admin/category/edit";
	}

	@PostMapping("/admin/category/edit/{cateID}")
	public String updateCategory(@PathVariable("cateID") int categoryId, @RequestParam("name_category") String newName,
			@RequestParam("note_category") String newNote) {
		categoryService.updateCategory(categoryId, newName, newNote);

		return "redirect:/admin/category";
	}

	@GetMapping("/admin/category/formAdd")
	public String forAddCate() {
		return "admin/category/add";
	}

	@PostMapping("/admin/category/add")
	public String addCate(@RequestParam("name_category") String newName,
			@RequestParam("note_category") String newNote) {
		category category = new category();
//		    category.setName_category(newName);
//		    category.setNote(newNote);
		categoryService.addCategory(category, newName, newNote);

		return "redirect:/admin/category";
	}
}
