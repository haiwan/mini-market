package org.haijian.demo.service;

import java.util.List;

public interface BasicCrudService<T> {
	List<T> findAll();
	
	void save(T object);
	
	void delete(T object);
}
