package controller;

import model.Department;
import model.DepartmentImpl;
import model.SuperMarket;

import view.DirectProductDepartmentViewImpl;

import view.InsertDepartmentImpl;

import view.MainPanelImpl;

import view.ModifyDepartmentViewImpl;

import javax.swing.JLabel;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */


public class DepartmentControllerImpl implements DepartmentController {
	
	private SuperMarket modelMarket;
	private MainPanelImpl precPanel;
	private InsertDepartmentImpl nextPanel;
	private DirectProductDepartmentViewImpl directPanel;
	private ModifyDepartmentViewImpl modifyView;

	public DepartmentControllerImpl(SuperMarket modelMarket, MainPanelImpl precPanel, InsertDepartmentImpl nextPanel) {

		this.modelMarket = modelMarket;
		this.precPanel = precPanel;
		this.nextPanel = nextPanel;

	}

	public DepartmentControllerImpl(SuperMarket modelMarket, DirectProductDepartmentViewImpl directPanel,
			ModifyDepartmentViewImpl modify) {

		this.modelMarket = modelMarket;
		this.directPanel = directPanel;
		this.modifyView = modify;

	}

	public void insertDepartement(String name, int max, int codeRepart) {

		
		Department newDepartment = new DepartmentImpl(name, max, codeRepart);

		modelMarket.addDepartment(newDepartment);


	}

	public String modifyDepartment(String name, int max, int code, JLabel displayNameDepartment) {

		String check = null;

		if (!String.valueOf(max).matches("[+]?\\d*\\.?\\d+")) {

			check = Utility.Utility.ERRORQUANTITY;
			directPanel.setPanel(this.modelMarket.getListDepartment());
			return check;

		} else {

			for (int i = 0; i < modelMarket.getListDepartment().size(); i++) {
				
				if(modelMarket.getListDepartment().get(i).quantityTotal() > max){
					
					
					 check = Utility.Utility.ERRORMODIFYQUANTIY;
					 directPanel.setPanel(this.modelMarket.getListDepartment());
					
					return check;
				}

				if (modelMarket.getListDepartment().get(i).getCodeDepartment() == code) {

					if (checkName(name) == true) {

						check = Utility.Utility.CHECKQUANTITY;

						modelMarket.getListDepartment().get(i).setMaxPriductDepartment(max);
						directPanel.setPanel(this.modelMarket.getListDepartment());
							
						
						return check;

					} else {

						modelMarket.getListDepartment().get(i).setName(name);
						modelMarket.getListDepartment().get(i).setMaxPriductDepartment(max);

						 directPanel.setPanel(this.modelMarket.getListDepartment());
						

						check = Utility.Utility.SUCCESSMODIFY;

					}
				}

			}
		}


		return check;


	}

	public boolean checkName(String name) {

		for (int i = 0; i < modelMarket.getListDepartment().size(); i++) {

			if (modelMarket.getListDepartment().get(i).getName().equals(name)) {

				return true;
			}

		}

		return false;
	}

	public boolean checkCode(int code) {

		for (int i = 0; i < modelMarket.getListDepartment().size(); i++) {

			if (modelMarket.getListDepartment().get(i).getCodeDepartment() == code) {

				return true;

			}

		}

		return false;

	}
	

	public SuperMarket getMarket() {

		return this.modelMarket;
	}

	public void quit() {

		
		this.precPanel.setVisible(true);
		this.nextPanel.setVisible(false);

	}

	public void quitModify() {

		this.directPanel.setVisible(true);
		this.modifyView.setVisible(false);

	}
	

}
