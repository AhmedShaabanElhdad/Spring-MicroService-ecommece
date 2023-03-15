package com.ahmedshaban.productservice.repository;

import com.ahmedshaban.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepo extends MongoRepository<Product,String> {
}
