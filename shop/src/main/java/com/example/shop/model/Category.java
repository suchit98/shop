package com.example.shop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "CATEGORY_BEAN_TB")
public class Category implements Serializable {

	private static final long serialVersionUID = 1588876621277899436L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoryId;

	@Column
	private String categoryName;

	@ManyToOne
	@JoinColumn(name = "productId", nullable = false)
	private Product product;

	@JsonBackReference
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
