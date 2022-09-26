package com.custom.webapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.custom.webapp.entity.Product;
import com.custom.webapp.services.ProductService;

@Controller
public class ProductController {

	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	// pagination process for page 
	@GetMapping("/page/{pageNumber}")
	public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
		Page<Product> page = productService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		List<Product> products = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("products", products);
		return "index";
	}
	
	// take pagination method that direct to first index page
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return getOnePage(model, 1);
	}
	
	// page to add product
	@GetMapping("/new-product")
	public String addProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "new_product";
	}
	
	// page to edit product
	@GetMapping("/update-product/{id}")
	public String updateProductPage(@PathVariable(value = "id") Integer id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "update_product";
	}
	
	// method to process edit data or save new data product 
	@PostMapping("/saveProduct")
	public String saveProduct(@Valid @ModelAttribute("product") Product product,
							  Errors errors,
							  Model model) {
		if (errors.hasErrors()) {
			return "new_product";
		} else {
			model.addAttribute("addProduct_message", "Product Successfully added");
			model.addAttribute("updateProduct_message", "Product Successfully added");
			productService.saveProduct(product);
			return "new_product";	
		}
	}
	
}
