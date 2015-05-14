package org.haijian.demo.ui;

import org.haijian.demo.data.User;
import org.haijian.demo.service.UserService;

import com.vaadin.spring.annotation.SpringView;

@SpringView(name = "user")
public class UserView extends BasicCrudView<User, UserService>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9100485009909037979L;
	
	@Override
	protected void prepareColumns() {
		getGrid().addColumn("userId", String.class);
		getGrid().addColumn("name", String.class);
	}

	@Override
	protected Class<User> getDataType() {
		return User.class;
	}

}
