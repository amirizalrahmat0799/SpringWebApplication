package com.custom.webapp.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.custom.webapp.entity.Product;

public interface ProductService {
	
	
	List<Product> getAllProducts();
	void saveProduct(Product product);
	Product getProductById(Integer id);
	Page<Product> findPage(int pageNumber);
	
	// rest controller method
	List<Product> getProduct(Integer productId);
	void addNewProduct(Product product);
	void updateProduct(Integer productId,
			 			String code,
			 			String name,
			 			String category,
			 			String brand,
			 			String type,
			 			String description);
	void deleteProductById(Integer productId);
}
