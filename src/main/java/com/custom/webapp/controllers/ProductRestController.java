package com.custom.webapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.custom.webapp.entity.Product;
import com.custom.webapp.services.ProductService;

@RestController
@RequestMapping(path = "api/products")
public class ProductRestController {

	private final ProductService productService;
	
	@Autowired
	public ProductRestController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public List<Product> getProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping(path = "{productId}")
	public List<Product> getProduct(@PathVariable("productId") Integer productId) {
		return productService.getProduct(productId);
	}
	
	@PostMapping
	public void registerNewProduct(@RequestBody Product product) {
		productService.addNewProduct(product);
	}
	
	@PutMapping(path = "{productId}")
	public void updateProduct(@PathVariable("productId") Integer ProductId,
							 @RequestParam(required = false) String code,
							 @RequestParam(required = false) String name,
							 @RequestParam(required = false) String category,
							 @RequestParam(required = false) String brand,
							 @RequestParam(required = false) String type,
							 @RequestParam(required = false) String description) 
	{
		productService.updateProduct(ProductId, code, name, category, brand, type, description);
	}
	
	@DeleteMapping(path = "{productId}")
	public void deleteProduct(@PathVariable("productId") Integer productId) {
		productService.deleteProductById(productId);
	}
	
}
