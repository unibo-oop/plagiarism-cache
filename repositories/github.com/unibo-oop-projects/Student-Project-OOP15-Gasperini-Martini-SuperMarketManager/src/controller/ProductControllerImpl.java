package controller;

import view.DirectProductDepartmentViewImpl;

import view.InsertProductViewImpl;

import view.MainPanelImpl;

import view.ModifyProduct;
import view.ModifyProductImpl;
import view.ManagementProductViewImpl;
import model.Department;

import model.Product;
import model.SuperMarket;
import model.ProductImpl;
/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public class ProductControllerImpl implements ProductController {

	private SuperMarket modelMarket;
	private Department department; 
	private MainPanelImpl panel;
	private InsertProductViewImpl nextPanel;
	private DirectProductDepartmentViewImpl directPanel;
	private ManagementProductViewImpl modifyView;

	public ProductControllerImpl(SuperMarket modelMarket, MainPanelImpl precPanel, InsertProductViewImpl nextPanel) {

		this.modelMarket = modelMarket;
		this.panel = precPanel;
		this.nextPanel = nextPanel;

	}

	public ProductControllerImpl(SuperMarket modelMarket, Department department,
			DirectProductDepartmentViewImpl precPanel, ManagementProductViewImpl nextPanel) {

		this.modelMarket = modelMarket;
		this.directPanel = precPanel;
		this.modifyView = nextPanel;
		this.department = department;
	}

	public SuperMarket getMarket() {

		return this.modelMarket;

	}

	public String insertProduct(Department department, String name, int code, int index, int price, int quantity) {

		if (checkQuantity(department, quantity) == false) {

			return Utility.Utility.ERRORCAPACITY;

		} else {

			if (checkCode(code) == true) {

				return Utility.Utility.ERRORCODE;

			}

			Product newProdcut = new ProductImpl(name, code, price, quantity);

			modelMarket.getListDepartment().get(index).insertProduct(newProdcut);
			modelMarket.insertDepartmentFile();
			
			return Utility.Utility.SUCCESSINSERT;

		}
	}

	public void modifyController(Product product, ManagementProductViewImpl view) {

		ModifyProduct productPanel = new ModifyProductImpl(view);
		ProductController productController = new ProductControllerImpl(this.modelMarket, this.department,
				this.directPanel, this.modifyView);

		this.modifyView.setVisible(false);

		productPanel.setData(product.getCodeProduct(), product.getName(), product.getPrice(), product.getQuantity());

		productPanel.addObserver(productController);

	}

	public String changeProduct(int code, String name, int price, int quantity) {

		String check;
		

		for (int i = 0; i < modelMarket.getListDepartment().size(); i++) {

			for (int j = 0; j < modelMarket.getListDepartment().get(i).getListProduct().size(); j++) {

				if (modelMarket.getListDepartment().get(i).getListProduct().get(j).getCodeProduct() == code) {

					if (quantity == 0) {

						modelMarket.getListDepartment().get(i)
								.deleteProduct(modelMarket.getListDepartment().get(i).getListProduct().get(j));

						check = Utility.Utility.CHECKDELETEPRODUCT;

						modelMarket.insertDepartmentFile();

						return check;
					}

					check = Utility.Utility.CHECKQUANTITY;
					modelMarket.getListDepartment().get(i).getListProduct().get(j).setPrice(price);

					modelMarket.insertDepartmentFile();

					if (checkQuantity(modelMarket.getListDepartment().get(i), quantity) == false) {

						check = Utility.Utility.ERRORCAPACITY;
						return check;

					} else {

						modelMarket.getListDepartment().get(i).getListProduct().get(j).setQuantity(quantity);

						modelMarket.insertDepartmentFile();

					}

					if (checkName(name) == true) {

						check = Utility.Utility.CHECKPRODUCT;

						return check;

					} else {

						modelMarket.getListDepartment().get(i).getListProduct().get(j).setName(name);

						check = Utility.Utility.SUCCESSMODIFY;
					}

				}

			}

		}

		check = Utility.Utility.SUCCESSMODIFY;

		
		return check;

	}

	public void deleteProdcut(Product product) {

		if (product.getQuantity() == 0) {

			department.deleteProduct(product);
			modifyView.setPanel(department.getListProduct());

		} else {

			product.setQuantity(product.getQuantity() - 1);
			modifyView.setPanel(department.getListProduct());

		}

	}

	public boolean checkQuantity(Department department, int quantity) {

		int count = 0;

		System.out.println(department.getListProduct().size());

		for (int i = 0; i < department.getListProduct().size(); i++) {

			count = count + department.getListProduct().get(i).getQuantity();

		}

		count = department.getMaxProductDepartment() - count;

		if (quantity > count) {

			return false;

		} else {

			return true;
		}

	}

	public boolean checkName(String name) {

		for (int i = 0; i < modelMarket.getListDepartment().size(); i++) {

			for (int j = 0; j < modelMarket.getListDepartment().get(i).getListProduct().size(); j++) {

				if (modelMarket.getListDepartment().get(i).getListProduct().get(j).getName().equals(name)) {

					return true;
				}

			}

		}

		return false;
	}

	public boolean checkCode(int code) {

		for (int i = 0; i < modelMarket.getListDepartment().size(); i++) {

			for (int j = 0; j < modelMarket.getListDepartment().get(i).getListProduct().size(); j++) {

				if (modelMarket.getListDepartment().get(i).getListProduct().get(j).getCodeProduct() == code) {

					return true;
				}

			}

		}

		return false;

	}
	public void quit() {

		this.panel.setVisible(true);
		this.nextPanel.setVisible(false);

	}

	public void quitModify() {

		this.directPanel.setVisible(true);
		this.modifyView.setVisible(false);

	}

	public void quitModifyProduct(ModifyProductImpl view) {

		modifyView.setVisible(true);
		modifyView.setPanel(department.getListProduct());
		view.setVisible(false);
	}

}
