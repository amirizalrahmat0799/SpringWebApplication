package com.custom.webapp.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.custom.webapp.entity.Product;
import com.custom.webapp.repos.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public void saveProduct(Product product) {
		this.productRepository.save(product);
	}
	
	@Override
	public Product getProductById(Integer id) {
		Optional<Product> optional = productRepository.findById(id);
		Product product = null;
		if (optional.isPresent()) {
			product = optional.get();
		} else {
			throw new RuntimeException("Product by id " + id + " not found");
		}
		return product;
	}

	@Override
	public Page<Product> findPage(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1,10);
		return productRepository.findAll(pageable);
	}
	
	// rest controller method

	@Override
	public List<Product> getProduct(Integer productId) {
		Optional<Product> byId = productRepository.findById(productId);
		if (!byId.isPresent()) {
			throw new IllegalStateException("Product by Id " + productId + " not found");
		}
		return productRepository.getProductById(productId);
	}

	@Override
	public void addNewProduct(Product product) {
		Optional<Product> productByCode = productRepository.findProductByCode(product.getCode());
		if (productByCode.isPresent()) {
			throw new IllegalStateException("Product code exist, no duplicate");
		}
		productRepository.save(product);
	}

	@Override
	@Transactional
	public void updateProduct(Integer productId,
							 String code,
							 String name,
							 String category,
							 String brand,
							 String type,
							 String description) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new IllegalStateException("Product by id " + productId + " not Found"));
		
		if (product != null &&
				name.length() > 0 &&
				!Objects.equals(product.getName(), name)) 
		{
			product.setName(name);
		}
		
		if (code != null &&
				code.length() > 0 &&
				!Objects.equals(product.getCode(), code)) 
		{
			Optional<Product> productByCode = productRepository.findProductByCode(code);
			if (productByCode.isPresent()) {
				throw new IllegalStateException("Code Exist, Can't Duplicate");
			}
			product.setCode(code);
		}	
	}

	@Override
	public void deleteProductById(Integer productId) {
		boolean existById = productRepository.existsById(productId);
		if (!existById) {
			throw new IllegalStateException("Product by id " + productId + " not exist");
		}
		productRepository.deleteById(productId);
	}
	
	

	
}
