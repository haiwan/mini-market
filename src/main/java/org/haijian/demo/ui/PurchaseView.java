package org.haijian.demo.ui;

import org.apache.catalina.User;
import org.haijian.demo.data.Product;
import org.haijian.demo.data.Purchase;
import org.haijian.demo.service.ProductService;
import org.haijian.demo.service.PurchaseService;
import org.haijian.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.ComboBox;

@SpringView(name = "purchase")
public class PurchaseView extends BasicCrudView<Purchase, PurchaseService>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2452348971814925538L;

	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	
	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		
		ComboBox userComb = new ComboBox();
		userComb.addItems(userService.findAll());
		getGrid().getColumn("user").setEditorField(userComb);
		getGrid().getColumn("user").getEditorField().setRequired(true);
		
		ComboBox productComb = new ComboBox();
		productComb.addItems(productService.findAll());
		getGrid().getColumn("product").setEditorField(productComb);
		getGrid().getColumn("product").getEditorField().setRequired(true);
	}

	@Override
	protected void prepareColumns() {
		getGrid().addColumn("user", User.class);
		getGrid().addColumn("product", Product.class);
		getGrid().addColumn("amount", Integer.class);
	}

	@Override
	protected Class<Purchase> getDataType() {
		return Purchase.class;
	}
	
}
