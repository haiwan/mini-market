package org.haijian.demo.repository;

import org.haijian.demo.data.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRepository extends MongoRepository<Purchase, String>{

}
