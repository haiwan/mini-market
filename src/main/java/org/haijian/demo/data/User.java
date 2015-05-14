package org.haijian.demo.data;


public class User extends BaseData{
	private String name;
	private String userId;

	public User(){
		
	}
	
	public User(String userId, String name){
		this.name = name;
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
