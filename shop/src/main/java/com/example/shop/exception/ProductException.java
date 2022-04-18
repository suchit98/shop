package com.example.shop.exception;

public class ProductException extends RuntimeException {

	private static final long serialVersionUID = -7900677380899033365L;
	
	public ProductException() {
		super();
	}
	public ProductException(String message) {
		super(message);
	}

}