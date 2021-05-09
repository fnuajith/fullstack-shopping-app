package com.company.shopping.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.company.shopping.entity.Product;
import com.company.shopping.kafka.Producer;
import com.company.shopping.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Mock
	private ProductRepository productRepository;

	@Mock
	private Producer kafkaProducer;
	
	@Test
	void testGetAllProducts() {
		List<Product> productList = new ArrayList<Product>();
		Product stubbedProduct1 = new Product("1001", "Product 1", "Description 1");
		Product stubbedProduct2 = new Product("1002", "Product 2", "Description 2");
		productList.add(stubbedProduct1);
		productList.add(stubbedProduct2);
		when(productRepository.findAll()).thenReturn(productList);
		List<Product> resultProducts = productService.getAllProducts();
		assertEquals(productList, resultProducts);
	}
	
	@Test
	void testGetProductById() {
		Optional<Product> stubbedProduct = Optional.ofNullable(new Product("1001", "Product 1", "Description 1"));
		when(productRepository.findById(anyString())).thenReturn(stubbedProduct);
		Product resultProduct = productService.getProductById("1001");
		assertEquals(stubbedProduct.get(), resultProduct);
	}
	
	@Test
	void testAddProduct() {
		Product stubbedProduct = new Product("1001", "Product 1", "Description 1");
		when(productRepository.save(any())).thenReturn(stubbedProduct);
		Object resultProduct = productService.addProduct(stubbedProduct);
		assertEquals(stubbedProduct, resultProduct);
	}
	
	@Test
	void testUpdateProduct() {
		Product stubbedProduct = new Product("1001", "Product 1", "Description 1");
		doNothing().when(kafkaProducer).sendMessage(any());
		when(productRepository.save(any())).thenReturn(stubbedProduct);
		Object resultProduct = productService.updateProduct(stubbedProduct);
		assertEquals(stubbedProduct, resultProduct);
	}
	
	@Test
	void tesDeleteProductById() {
		Product stubbedProduct = new Product("1001", "Product 1", "Description 1");
		doNothing().when(productRepository).deleteById(anyString());
		productService.deleteProductById(stubbedProduct.getId());
		assertEquals("1001", stubbedProduct.getId());
	}

}
