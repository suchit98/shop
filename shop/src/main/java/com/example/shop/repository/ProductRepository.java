package com.example.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByProductId(int productId);

}
