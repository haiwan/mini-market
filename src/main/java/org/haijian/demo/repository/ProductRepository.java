package org.haijian.demo.repository;

import org.haijian.demo.data.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>{
	Product findOneByName(String name);
}
