package com.example.demo.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class category implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_category;
	public String nameCategory;
	private String note;
	public category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_category() {
		return id_category;
	}
	public void setId_category(int id_category) {
		this.id_category = id_category;
	}
	public String getName_category() {
		return nameCategory;
	}
	public void setName_category(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
