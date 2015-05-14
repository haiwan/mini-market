package org.haijian.demo.ui;

import org.haijian.demo.data.Product;
import org.haijian.demo.service.ProductService;

import com.vaadin.spring.annotation.SpringView;

@SpringView(name = "product")
public class ProductView extends BasicCrudView<Product, ProductService> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3808386205611936011L;

	protected void prepareColumns() {
		getGrid().addColumn("name", String.class);
		getGrid().addColumn("price", Double.class);
		getGrid().addColumn("quantity", Integer.class);
	}

	@Override
	protected Class<Product> getDataType() {
		return Product.class;
	}

}
