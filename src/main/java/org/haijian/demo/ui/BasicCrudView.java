package org.haijian.demo.ui;

import javax.annotation.PostConstruct;

import org.haijian.demo.service.BasicCrudService;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.ButtonRenderer;

public abstract class BasicCrudView<T, S extends BasicCrudService<T>> extends
		CssLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6948967463136343114L;

	private Grid grid;

	@Autowired
	private S service;

	protected abstract void prepareColumns();

	@Override
	public void enter(ViewChangeEvent event) {
		BeanItemContainer<T> ds = new BeanItemContainer<T>(getDataType(),
				service.findAll());
		GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(ds);

		gpc.addGeneratedProperty("delete",
				new PropertyValueGenerator<String>() {

					/**
				 * 
				 */
					private static final long serialVersionUID = -6937958091767265709L;

					@Override
					public String getValue(Item item, Object itemId,
							Object propertyId) {
						return "Delete"; // The caption
					}

					@Override
					public Class<String> getType() {
						return String.class;
					}
				});
		grid.setContainerDataSource(gpc);
		grid.getColumn("delete").setRenderer(new ButtonRenderer(e -> {
			grid.getContainerDataSource().removeItem(e.getItemId());
			service.delete((T) e.getItemId());
		}));
		grid.getColumn("delete").setEditable(false);

		getGrid().getEditorFieldGroup().addCommitHandler(new CommitHandler() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 5517548514603785981L;

			@Override
			public void preCommit(CommitEvent commitEvent)
					throws CommitException {

			}

			@Override
			public void postCommit(CommitEvent commitEvent)
					throws CommitException {
				service.save((T) getGrid().getEditedItemId());
			}
		});
	}

	@PostConstruct
	public void initialize() {
		setSizeFull();

		addComponent(new Button("Add", e -> addNewItem()));

		grid = new Grid();
		prepareColumns();
		grid.addColumn("delete", Button.class);
		grid.setEditorEnabled(true);

		addComponent(grid);
	}

	private void addNewItem() {
		grid.getContainerDataSource().addItem(createNewItem());
	}

	private T createNewItem() {
		try {
			return getDataType().newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	protected Grid getGrid() {
		return grid;
	}

	protected abstract Class<T> getDataType();
}
