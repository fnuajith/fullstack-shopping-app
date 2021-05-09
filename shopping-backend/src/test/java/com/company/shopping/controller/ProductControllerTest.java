package com.company.shopping.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.company.shopping.entity.Product;
import com.company.shopping.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
	
	@InjectMocks
	private ProductController productController;
	
	@Mock
	private ProductService productService;
	

	@Test
	void testGetAllProducts() {
		List<Product> productList = new ArrayList<Product>();
		Product stubbedProduct1 = new Product("1001", "Product 1", "Description 1");
		Product stubbedProduct2 = new Product("1002", "Product 2", "Description 2");
		productList.add(stubbedProduct1);
		productList.add(stubbedProduct2);
		when(productService.getAllProducts()).thenReturn(productList);
		ResponseEntity<List<Product>> resultProducts = productController.getAllProducts();
		assertEquals(productList, resultProducts.getBody());
	}
	
	@Test
	void testGetProductById() {
		Product stubbedProduct = new Product("1001", "Product 1", "Description 1");
		when(productService.getProductById(anyString())).thenReturn(stubbedProduct);
		ResponseEntity<Product> resultProduct = productController.getProductById("1001");
		assertEquals(stubbedProduct, resultProduct.getBody());
	}
	
	@Test
	void testCreateProduct() {
		Product stubbedProduct = new Product("1001", "Product 1", "Description 1");
		when(productService.addProduct(any())).thenReturn(stubbedProduct);
		ResponseEntity<Product> resultProduct = productController.createProduct(stubbedProduct);
		assertEquals(stubbedProduct, resultProduct.getBody());
	}
	
	@Test
	void testUpdateProduct() {
		Product stubbedProduct = new Product("1001", "Product 1", "Description 1");
		when(productService.getProductById(anyString())).thenReturn(stubbedProduct);
		when(productService.updateProduct(any())).thenReturn(stubbedProduct);
		ResponseEntity<Product> resultProduct = productController.updateProduct(stubbedProduct.getId(), stubbedProduct);
		assertEquals(stubbedProduct, resultProduct.getBody());
	}
	
	@Test
	void testDeleteProductById() {
		Product stubbedProduct = new Product("1001", "Product 1", "Description 1");
		doNothing().when(productService).deleteProductById(anyString());
		ResponseEntity<?> result = productController.deleteProductById(stubbedProduct.getId());
		assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
	}

}
