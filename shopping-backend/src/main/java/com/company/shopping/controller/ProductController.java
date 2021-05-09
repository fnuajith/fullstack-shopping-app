package com.company.shopping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.shopping.entity.Product;
import com.company.shopping.service.ProductService;

import io.swagger.annotations.ApiOperation;

/**
 * REST controller that exposes product APIs
 * 
 * @author Ajith
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductService productService;

	/**
	 * Retrieve all the products
	 * 
	 * @return List<Product>
	 */
	@ApiOperation(value = "Get All Products", notes = "Fetch all the products")
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		try {
			List<Product> products = productService.getAllProducts();
			return CollectionUtils.isEmpty(products) ? new ResponseEntity<>(products, null, HttpStatus.NO_CONTENT)
					: new ResponseEntity<>(products, null, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception occurred when trying to get all products - {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.headers(HeaderUtil.createErrorHeader("Error occurred when trying to get all products")).body(null);
		}
	}

	/**
	 * Retrieve product by product id
	 * 
	 * @param productId
	 * @return Product
	 */
	@ApiOperation(value = "Get Product By Id", notes = "Find product by product ID")
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") String productId) {
		try {
			Product product = productService.getProductById(productId);
			return product != null ? new ResponseEntity<>(product, null, HttpStatus.OK)
					: new ResponseEntity<>(product, null, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error("Exception occurred when trying to get product by id - {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.headers(HeaderUtil.createErrorHeader("Error occurred when trying to get product by id"))
					.body(null);
		}
	}

	/**
	 * Create a new product
	 * 
	 * @param product
	 * @return Product
	 */
	@ApiOperation(value = "Create Product", notes = "Create a new product")
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		try {
			Product savedProduct = productService.addProduct(product);
			return new ResponseEntity<>(savedProduct, null, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Exception occurred when trying to get create product - {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.headers(HeaderUtil.createErrorHeader("Error occurred occurred when trying to get create product"))
					.body(null);
		}
	}

	/**
	 * Find and update an existing product
	 * 
	 * @param product
	 * @return Product
	 */
	@ApiOperation(value = "Update Product", notes = "Update an existing product")
	@PutMapping("/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable("productId") String productId, @RequestBody Product product) {
		try {
			Product currentProduct = productService.getProductById(productId);
			if (currentProduct != null) {
				Product savedProduct = productService.updateProduct(product);
				return new ResponseEntity<>(savedProduct, null, HttpStatus.OK);
			} else {
				logger.error("Unable to find product {}", product);
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.headers(HeaderUtil.createErrorHeader("Unable to find product"))
						.body(null);
			}
		} catch (Exception e) {
			logger.error("Exception occurred when trying to update product - {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.headers(HeaderUtil.createErrorHeader("Error occurred occurred when trying to get update product"))
					.body(null);
		}
	}

	/**
	 * Delete product by product id
	 * 
	 * @param productId
	 * @return
	 */
	@ApiOperation(value = "Delete Product", notes = "Delete product by product ID")
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") String productId) {
		try {
			productService.deleteProductById(productId);
			return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error("Exception occurred when trying to delete product - {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.headers(HeaderUtil.createErrorHeader("Error occurred occurred when trying to get delete product"))
					.body(null);
		}
	}

}
