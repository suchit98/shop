package com.example.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.exception.CategoryException;
import com.example.shop.model.Category;
import com.example.shop.repository.CategoryRepository;

@RestController
@RequestMapping(value = "/api")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;

	@PostMapping("/categories")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		Category categoryAdded = categoryRepository.save(category);
		if (categoryAdded == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(categoryAdded, HttpStatus.CREATED);
	}

	@GetMapping("/categories/{di}")
	public ResponseEntity<Category> getCategoryById(@PathVariable(value = "di") Integer id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryException("Not found Category with id = " + id));
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@PutMapping("/categories/{di}")
	public ResponseEntity<Category> updateCategory(@PathVariable("di") Integer id,
			@RequestBody Category categoryRequest) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryException("CategoryId " + id + "not found"));
		category.setCategoryName(categoryRequest.getCategoryName());
		return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
	}

	@DeleteMapping("/categories/{di}")
	public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("di") Integer id) {
		categoryRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategory() {

		List<Category> category = categoryRepository.findAll();
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	@GetMapping("/categoryPage/{pageNo}")
	public List<Category> getAllCatgoryLimit(@PathVariable("pageNo") int pageNo)
    {
        Pageable paging = PageRequest.of(pageNo, 5);
 
        Page<Category> pagedResult = categoryRepository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Category>();
        }
    }

}
