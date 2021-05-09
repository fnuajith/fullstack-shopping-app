package com.company.shopping.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Entity class for product
 * 
 * @author Ajith
 *
 */
@Document(collection = "Product")
public class Product {
	
	@Id
	private String id;
	
	@Field
	private String productName;
	
	@Field
	private String productDescription;
	
	public Product() {}

	public Product(String id, String productName, String productDescription) {
		super();
		this.id = id;
		this.productName = productName;
		this.productDescription = productDescription;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Override
	public String toString() {
		return "\nProduct [id=" + id + ", productName=" + productName + ", productDescription=" + productDescription + "]";
	}
	
}
