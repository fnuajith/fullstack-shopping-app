package com.company.shopping.service;

import java.util.List;

import com.company.shopping.entity.Product;

/**
 * Business/Service interface that has product related methods
 * 
 * @author Ajith
 *
 */
public interface ProductService {
	
	public List<Product> getAllProducts();
	
	public Product getProductById(String id);
	
	public Product addProduct(Product product);
	
	public Product updateProduct(Product product);
	
	public void deleteProductById(String productId);
	
}
