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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.exception.ProductException;
import com.example.shop.model.Product;
import com.example.shop.repository.ProductRepository;

@RestController
@RequestMapping(value = "/api")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/products/")
	public ResponseEntity<List<Product>> getAllProducts() {
		
		
		List<Product> productList = productRepository.findAll();
		if (productList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@GetMapping("/products/{di}")
	public ResponseEntity<Product> getProductById(@PathVariable("di") long id) {
		Product product = productRepository.findById((int) id)
				.orElseThrow(() -> new ProductException("Not found Product with id = " + id));
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product productAdded = productRepository.save(product);
		if (productAdded == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(productAdded, HttpStatus.CREATED);
	}

	@PutMapping("/products/{di}")
	public ResponseEntity<Product> updateProduct(@PathVariable("di") long id, @RequestBody Product product) {
		Product productFound = productRepository.findById((int) id)
				.orElseThrow(() -> new ProductException("Not found Product with id = " + id));
		productFound.setProductCount(product.getProductCount());
		productFound.setProductdesc(product.getProductdesc());
		productFound.setProductName(product.getProductName());
		productFound.setProductPrice(product.getProductPrice());
		productFound.setProductType(product.getProductType());

		return new ResponseEntity<>(productRepository.save(productFound), HttpStatus.OK);
	}

	@DeleteMapping("/products/{di}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("di") long id) {
		productRepository.deleteById((int) id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/productsPage/{pageNo}")
	public List<Product> getAllProductLimit( @PathVariable("pageNo") int pageNo)
    {
		System.out.println(pageNo);
        Pageable paging = PageRequest.of(pageNo , 5);
 
        Page<Product> pagedResult = productRepository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Product>();
        }
    }
	
	
}

