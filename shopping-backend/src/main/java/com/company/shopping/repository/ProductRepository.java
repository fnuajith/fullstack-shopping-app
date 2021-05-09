package com.company.shopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.company.shopping.entity.Product;

/**
 * Product repository that extends MongoRepository. Exposes methods to query the database.
 * 
 * @author Ajith
 *
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
