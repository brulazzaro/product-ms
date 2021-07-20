package com.productms.springboot.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.productms.springboot.model.Product;
import com.productms.springboot.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping
	public List<Product> findProducts() {
		return productRepository.findAll();
	}
	
	@GetMapping("/search")
	public List<Product> search(@RequestParam(name = "q", required = false) final String q, 
			@RequestParam(name = "min_price", required = false) final Double minPrice, 
			@RequestParam(name = "max_price", required = false) final Double maxPrice) {
		
		if (q != null || minPrice != null || maxPrice != null) {
			if ((minPrice != null || maxPrice != null) && q == null) {
				if (maxPrice == null) {
					return productRepository.searchProductByMinimumPrice(minPrice);
				} else if (minPrice == null) {
					return productRepository.searchProductByMaximumPrice(maxPrice);
				}
				return productRepository.searchProductByMinAndMaxPrice(minPrice, maxPrice);
			} else if ((q != null || minPrice != null) && maxPrice == null) {
				if (minPrice == null) {
					return productRepository.searchProduct(q);
				}
				return productRepository.searchProductByMinimumPrice(q, minPrice);
			} else if ((q != null || maxPrice != null) && minPrice == null) {
				return productRepository.searchProductByMaximumPrice(q, maxPrice);
			}
			return productRepository.searchProduct(q, minPrice, maxPrice);
		}
		
		return productRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Product> createProduct(@RequestBody final Product product) {
		productRepository.save(product);
		
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/{id}")
	public Product findById(@PathVariable final Long id) {
		return productRepository.getById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> attById(@PathVariable final Long id, @RequestBody final Product product) {
		Product respProduct = productRepository.getById(id);
		
		if (respProduct == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(product, respProduct, "id");
		respProduct = productRepository.save(respProduct);
		
		return ResponseEntity.ok(respProduct);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable final Long id) {
		final Product product = productRepository.getById(id);
		
		if (product.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		productRepository.delete(product);
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
}
