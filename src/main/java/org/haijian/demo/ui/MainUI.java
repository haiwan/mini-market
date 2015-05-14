package org.haijian.demo.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("mini-market")
@Widgetset("AppWidgetset")
public class MainUI extends UI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3598748334142085488L;

	@Autowired
	private ViewProvider provider;
	
	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		MenuBar menuBar = new MenuBar();
		Panel viewContent = new Panel();
		viewContent.setSizeFull();
		content.addComponent(menuBar);
		content.addComponent(viewContent);
		content.setExpandRatio(viewContent, 1f);
		setContent(content);
		
		Navigator navigator = new Navigator(this, viewContent);
		navigator.addProvider(provider);
		navigator.navigateTo("purchase");

		menuBar.addItem("Purchase", e->navigator.navigateTo("purchase"));
		menuBar.addItem("User", e->navigator.navigateTo("user"));
		menuBar.addItem("Product", e->navigator.navigateTo("product"));
		menuBar.addItem("Report", e->navigator.navigateTo("report"));
	}
	
}
