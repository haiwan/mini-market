package org.haijian.demo.service;

import java.util.List;

import org.haijian.demo.data.Product;
import org.haijian.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements BasicCrudService<Product>{
	
	@Autowired
	private ProductRepository repository;

	@Override
	public List<Product> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(Product product) {
		repository.save(product);
	}

	@Override
	public void delete(Product product) {
		repository.delete(product);
	}
	
}
