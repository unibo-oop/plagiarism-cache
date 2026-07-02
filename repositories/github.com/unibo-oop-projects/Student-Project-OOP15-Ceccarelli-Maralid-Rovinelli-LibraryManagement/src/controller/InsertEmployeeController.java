package controller;

import java.util.Date;

import view.AddEmployeePanel;
import view.LoginPanelImpl;
import view.MainView;
import view.observer.AddEmployeeObserver;
import model.Model;
import model.EmployeeImpl;
import model.EmployeeModel;

/**
 * @author erik_
 *
 */
public class InsertEmployeeController implements AddEmployeeObserver {

	private Model model;
	private AddEmployeePanel view;
	private MainView mainView;
	private EmployeeModel employee;

	public InsertEmployeeController(MainView mainView, Model model) {
		this.model = model;
		this.mainView = mainView;
	}

	public void setView(AddEmployeePanel ae) {
		this.view = ae;
		this.view.attachObserver(this);
	}

	@Override
	public void saveEmployee(String name, String surname, String address, String username, char[] password,
			String email, int telephoneNumber, String taxCode, Date birthDate, Date hireDate) {
		if (model.employees().searchEmployee(username).getUsername() == null) {
			employee = new EmployeeImpl(taxCode, name, surname, email, birthDate, hireDate, address, username,
					password);
			model.employees().addNewEmployee(employee);
			LoginPanelImpl li = new LoginPanelImpl();
			LoginController lp = new LoginController(this.mainView, model);
			lp.setView(li);
			this.mainView.replaceMainPanel(li);
			this.view.displayMessage("Dipendente registrato con successo");
		} else {
			this.view.displayMessage("Username già utilizzato");
		}

	}
}
