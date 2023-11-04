package com.example.demo.Entity;

import java.util.Date;

public class product {	
	private int id_product;
	public product() {
		super();
	}
	private String name_product;
	private String name_category;
	private Float price_product;
	private int count_product;
	private String image1;
	private String image2;
	private String image3;
	private Long decription_product;
	private Long decription;
	private int sale_product;
	private String outstand_product;
	private Date date_product;
	private Float price_sale;
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
	public String getName_category() {
		return name_category;
	}
	public void setName_category(String name_category) {
		this.name_category = name_category;
	}
	public Float getPrice_product() {
		return price_product;
	}
	public void setPrice_product(Float price_product) {
		this.price_product = price_product;
	}
	public int getCount_product() {
		return count_product;
	}
	public void setCount_product(int count_product) {
		this.count_product = count_product;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public Long getDecription_product() {
		return decription_product;
	}
	public void setDecription_product(Long decription_product) {
		this.decription_product = decription_product;
	}
	public Long getDecription() {
		return decription;
	}
	public void setDecription(Long decription) {
		this.decription = decription;
	}
	public int getSale_product() {
		return sale_product;
	}
	public void setSale_product(int sale_product) {
		this.sale_product = sale_product;
	}
	public String getOutstand_product() {
		return outstand_product;
	}
	public void setOutstand_product(String outstand_product) {
		this.outstand_product = outstand_product;
	}
	public Date getDate_product() {
		return date_product;
	}
	public void setDate_product(Date date_product) {
		this.date_product = date_product;
	}
	public Float getPrice_sale() {
		return price_sale;
	}
	public void setPrice_sale(Float price_sale) {
		this.price_sale = price_sale;
	} 
}
