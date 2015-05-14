package org.haijian.demo.data;

import javax.validation.constraints.NotNull;

public class Purchase extends BaseData{
	@NotNull
	private User user;
	@NotNull
	private Product product;
	private int amount;
	
	public Purchase(){
		
	}
	
	public Purchase(User user, Product product, int amount){
		this.user = user;
		this.product = product;
		this.amount = amount;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice(){
		return getProduct().getPrice()*getAmount();
	}
	
}
