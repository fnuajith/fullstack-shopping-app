package com.company.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.shopping.entity.Product;
import com.company.shopping.kafka.Producer;
import com.company.shopping.repository.ProductRepository;
import com.company.shopping.service.ProductService;

/**
 * Business/Service class that implements methods which invoke repository methods
 * 
 * @author Ajith
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private Producer kafkaProducer;
	
	/**
	 * Method to get all products
	 */
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	/**
	 * Method to get product by product id
	 */
	@Override
	public Product getProductById(String id) {
		return productRepository.findById(id).orElse(null);
	}

	/**
	 * Method to add a product
	 */
	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	/**
	 * Method to update an existing product
	 */
	@Override
	public Product updateProduct(Product product) {
		Product updatedProduct = productRepository.save(product);
		if (updatedProduct != null) {
			kafkaProducer.sendMessage(product);
		}
		return updatedProduct;
	}

	/**
	 * Method to delete a product by product id
	 */
	@Override
	public void deleteProductById(String productId) {
		productRepository.deleteById(productId);
	}

}
