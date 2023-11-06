package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Entity.order;
import com.example.demo.Entity.order_detail;
import com.example.demo.service.orderService;
import com.example.demo.service.order_detailServicce;

@Controller
public class adminController {
		private orderService orderService;
		private order_detailServicce order_detailService;
		@Autowired
		public adminController(orderService orderService) {
			this.orderService = orderService;
			this.order_detailService= order_detailService;
		}
		
//		order
	@GetMapping("/admin/order")
	public String order(Model model) {
     
		return orderpaginate(model, 1); 
	}
	
	 
    @GetMapping("/admin/order/page/{pageNumber}")
    public String orderpaginate(Model model, @PathVariable("pageNumber") int currentPage) {
    	Page<order> page  = orderService.findPage(currentPage);
    			int totalPage = page.getTotalPages();
    	long totalItem = page.getTotalElements();
      	 List<order> orderList = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("orders", orderList);


        return "admin/order/order";
    }
    
	/*
	 * @GetMapping("/admin/order/detail/{id}") public String detailOrder(Model
	 * model,@PathVariable("id") int orderID) { List<order_detail> orderwithID =
	 * order_detailService.getorderDetail(orderID);
	 * model.addAttribute("orderwithID", orderwithID); return
	 * "admin/order/order_detail";
	 * 
	 * }
	 */
    
}
