package controller;

import view.DirectProductDepartmentViewImpl;

import view.MainPanelImpl;

import view.ModifyDepartmentViewImpl;
import view.ManagementProductViewImpl;

import java.util.ArrayList;

import javax.swing.JLabel;

import model.Department;

import model.SuperMarket;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public class DirectProductDepartmentControllerImpl implements DirectProductDepartmentController {

	private SuperMarket modelMarket;
	private MainPanelImpl panel;
	private DirectProductDepartmentViewImpl currentPanel;

	public DirectProductDepartmentControllerImpl(SuperMarket modelMarket, MainPanelImpl panel,
			DirectProductDepartmentViewImpl currentPanel) {

		this.modelMarket = modelMarket;
		this.panel = panel;
		this.currentPanel = currentPanel;
	}

	public void deleteDepartment(Department department) {

				modelMarket.getListDepartment().remove(department);
				currentPanel.setPanel(getListDepartmentView());
	}

	public void setModifyController(Department departments, JLabel nameDepartment) {

		ModifyDepartmentViewImpl departmentPanel = new ModifyDepartmentViewImpl();
		DepartmentController departmentController = new DepartmentControllerImpl(this.modelMarket, currentPanel,
				departmentPanel);

		departmentPanel.setData(departments.getName(), departments.getMaxProductDepartment(),
				departments.getCodeDepartment(), nameDepartment);

		currentPanel.setVisible(false);
		departmentPanel.setVisible(true);

		departmentPanel.addObserver(departmentController);

	}

	public void setModifyProductController(Department department) {

		ManagementProductViewImpl productPanel = new ManagementProductViewImpl();
		ProductController productController = new ProductControllerImpl(this.modelMarket,department, currentPanel, productPanel);

		productPanel.listProduct(department.getListProduct());

		currentPanel.setVisible(false);
		productPanel.setVisible(true);

		productPanel.addObserver(productController);

	}
	
	public ArrayList<Department> getListDepartmentView(){
		
		return modelMarket.getListDepartment();
	}


	public void quit() {

		this.panel.setVisible(true);
		this.currentPanel.setVisible(false);

	}

}
