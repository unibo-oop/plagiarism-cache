package controller;

import java.io.Serializable;

import model.EmployeeImpl;
import model.Model;
import view.AddEmployeePanelImpl;
import view.BookshopPanelImpl;
import view.LoginPanel;
import view.MainView;
import view.observer.LoginObserver;

/**
 * @author erik_
 *
 */
@SuppressWarnings("serial")
public class LoginController implements LoginObserver, Serializable {

	private Model model;
	public MainView mainView;
	private LoginPanel view;

	public LoginController(MainView mainView, Model model) {
		this.mainView = mainView;
		this.model = model;
	}

	public void setView(LoginPanel lp) {
		this.view = lp;
		this.view.attachObserver(this);

	}

	@Override
	public void loginEmployee(String username, char[] password) {
		EmployeeImpl e;
		if (model.employees().logged(username, password).equals(true)) {
			this.mainView.getNorthPanel().changeLogStatus(true);
			this.mainView.changeLogStatus(true);
			BookshopPanelImpl bsp = new BookshopPanelImpl();
			BookshopController bsc = new BookshopController(this.mainView, model);
			bsc.setView(bsp);
			this.mainView.replaceMainPanel(bsp);
			e = (EmployeeImpl) model.employees().searchEmployee(username);
			this.mainView.getNorthPanel().displayLoggedEmployee(e.getName(), e.getSurname());
		} else {
			view.displayMessage("Credenziali errate");
		}
	}

	@Override
	public void registerEmployeeClicked() {
		AddEmployeePanelImpl ae = new AddEmployeePanelImpl();
		InsertEmployeeController ie = new InsertEmployeeController(this.mainView, model);
		ie.setView(ae);
		this.mainView.replaceMainPanel(ae);
	}

}