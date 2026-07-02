package controller;

import model.SuperMarket;

import view.DirectProductDepartmentViewImpl;

import view.InsertDepartmentImpl;

import view.InsertProductViewImpl;

import view.MainPanelImpl;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public class MainControllerImpl implements MainController {

	private SuperMarket modelMarket;
	private MainPanelImpl panel;

	public MainControllerImpl(SuperMarket modelMarket, MainPanelImpl panel) {

		this.modelMarket = modelMarket;
		this.panel = panel;

	}

	public void insertDepartmentView() {

		InsertDepartmentImpl productPanel = new InsertDepartmentImpl();
		DepartmentController departmentController = new DepartmentControllerImpl(this.modelMarket, panel, productPanel);

		panel.setVisible(false);
		productPanel.setEnabled(true);

		productPanel.addObserver(departmentController);

	}

	public void insertProductView() {

		InsertProductViewImpl insertProductPanel = new InsertProductViewImpl(panel);
		ProductController insertProductController = new ProductControllerImpl(this.modelMarket, panel,
				insertProductPanel);

		this.panel.setVisible(false);
		insertProductPanel.setEnabled(true);

		insertProductPanel.addObserver(insertProductController);

	}

	public void directProductDepartment() {

		DirectProductDepartmentViewImpl directView = new DirectProductDepartmentViewImpl();
		DirectProductDepartmentController directController = new DirectProductDepartmentControllerImpl(this.modelMarket,
				panel, directView);

		this.panel.setVisible(false);
		directView.setEnabled(true);

		directView.addObserver(directController);

	}

	public boolean logIn(String username, String password) {

		return this.modelMarket.logIn(username, password);

	}
	
	public void setFile(){
		
		this.modelMarket.insertDepartmentFile();
	}

	public SuperMarket getSuperMarket() {

		return this.modelMarket;

	}
	
		
}
