package com.example.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Category findByCategoryId(int categoryId);

}
