package org.haijian.demo.service;

import java.util.List;

import org.haijian.demo.data.User;
import org.haijian.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements BasicCrudService<User>{
	@Autowired
	private UserRepository repository;

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(User user) {
		repository.save(user);
	}

	@Override
	public void delete(User user) {
		repository.delete(user);
	}
	
}
