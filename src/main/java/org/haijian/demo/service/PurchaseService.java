package org.haijian.demo.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.haijian.demo.data.Product;
import org.haijian.demo.data.Purchase;
import org.haijian.demo.data.User;
import org.haijian.demo.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService implements BasicCrudService<Purchase>{
	@Autowired
	private PurchaseRepository repository;

	public Map<User, Double> getAllPurchasesGroupByUser() {
		return findAll().stream().collect(Collectors.groupingBy(Purchase::getUser, Collectors.summingDouble(Purchase::getPrice)));
	}
	
	public Map<Product, Double> getAllPurchasesGroupByProduct() {
		return findAll().stream().collect(Collectors.groupingBy(Purchase::getProduct, Collectors.summingDouble(Purchase::getPrice)));
	}

	@Override
	public List<Purchase> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(Purchase purchase) {
		repository.save(purchase);
	}

	@Override
	public void delete(Purchase purchase) {
		repository.delete(purchase);
	}

}
