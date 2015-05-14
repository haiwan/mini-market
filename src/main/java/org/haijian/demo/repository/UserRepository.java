package org.haijian.demo.repository;

import org.haijian.demo.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
	User findOneByUserId(String userId);
}
