package com.example.demo.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class order_detail {
	
	  @Id 
//	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	private int id_product;
	  
	private int idOrder;
	
	private String name_product;
	private int count;
	private Float price;
	private Date date;
	public order_detail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_order() {
		return idOrder;
	}
	public void setId_order(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getId_product() {
		return id_product;
	}
	public void setId_product(int id_product) {
		this.id_product = id_product;
	}
	public String getName_product() {
		return name_product;
	}
	public void setName_product(String name_product) {
		this.name_product = name_product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
