package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.Date;

@Entity
public class product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id_product;

    private String name_product;
    
    @ManyToOne
    @JoinColumn(name = "name_category", referencedColumnName = "nameCategory")
    private category categoryEntity;
    
    private Float price_product;
    private int count_product;
    private String image1;
    private String image2;
    private String image3;
    private String decription_product;
    private String decription;
    private int sale_product;
    private String outstandProduct;
    private Date dateProduct;

	public category getCategoryEntity() {
	    return categoryEntity;
	}

	public void setCategoryEntity(category categoryEntity) {
	    this.categoryEntity = categoryEntity;
	}


	private Float price_sale;

    public product() {
        super();
    }

    public int getid_product() {
        return id_product;
    }

    public void setid_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
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

    public String getDecription_product() {
        return decription_product;
    }

    public void setDecription_product(String decription_product) {
        this.decription_product = decription_product;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public int getSale_product() {
        return sale_product;
    }

    public void setSale_product(int sale_product) {
        this.sale_product = sale_product;
    }

    public String getOutstandProduct() {
        return outstandProduct;
    }

    public void setOutstandProduct(String outstandProduct) {
        this.outstandProduct = outstandProduct;
    }

    public Date getDateProduct() {
        return dateProduct;
    }

    public void setDateProduct(Date dateProduct) {
        this.dateProduct = dateProduct;
    }

    public Float getPrice_sale() {
        return price_sale;
    }

    public void setPrice_sale(Float price_sale) {
        this.price_sale = price_sale;
    }
}