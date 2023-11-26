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
	public int idCategory;
	public String nameCategory;
	public String note;
	public category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getidCategory() {
		return idCategory;
	}
	public void setidCategory(int idCategory) {
		this.idCategory = idCategory;
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
	
	  public void updateCategory(String newName, String newNote) {
	        this.nameCategory = newName;
	        this.note = newNote;
	    }
}
