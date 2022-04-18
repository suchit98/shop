package com.example.shop.model;

import java.util.List;

public class ResponseBean {

	private int statusCode;
	private String message;
	private String description;

	private Product productBean;
	private List<Product> productBeanList;
	private Category categoryBean;
	private List<Category> categoryBeanList;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Product getProductBean() {
		return productBean;
	}

	public void setProductBean(Product productBean) {
		this.productBean = productBean;
	}

	public List<Product> getProductBeanList() {
		return productBeanList;
	}

	public void setProductBeanList(List<Product> productBeanList) {
		this.productBeanList = productBeanList;
	}

	public Category getCategoryBean() {
		return categoryBean;
	}

	public void setCategoryBean(Category categoryBean) {
		this.categoryBean = categoryBean;
	}

	public List<Category> getCategoryBeanList() {
		return categoryBeanList;
	}

	public void setCategoryBeanList(List<Category> categoryBeanList) {
		this.categoryBeanList = categoryBeanList;
	}

}
