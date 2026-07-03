package view.controllers;

import static view.ViewHandler.getObserver;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.enumerations.ContractType;
import model.enumerations.Status;
import model.interfaces.Employee;
import view.EmployeeProfile;
import view.ViewUtils;
import view.Vista;

public class EmployeeProfileHandler extends Vista implements EmployeeProfile {

	@FXML private TextField phoneNumber;
    @FXML private TextField surname;
    @FXML private DatePicker signinDate;
    @FXML private TextField name;
    @FXML private Button backBtn;
    @FXML private TextField salary;
    @FXML private TextField taxCode;
    @FXML private DatePicker birthdate;
    @FXML private TextField email;
    @FXML private Button saveBtn;
    @FXML private Button deleteBtn;
    @FXML private ChoiceBox<ContractType> contract;
    
    private Employee selectedEmployee;
    

    @Override
    public void setFields(Employee employee){
    	this.selectedEmployee = employee;
        this.name.setText(employee.getPerson().getFirstName());
        this.surname.setText(employee.getPerson().getLastName());
        this.birthdate.setValue(employee.getPerson().getBirthday());
        this.phoneNumber.setText(employee.getPerson().getTelephonNumber());
        this.email.setText(employee.getPerson().getEmail());
        this.taxCode.setText(employee.getPerson().getTaxCode());
        this.salary.setText(Double.toString(employee.getSalary()));
        this.signinDate.setValue(employee.getSigningContract());
        this.contract.setValue(employee.getContracType()); 
    }

    @Override
    public String getName() {
        return this.name.getText();
    }

    @Override
    public String getSurname() {
        return this.surname.getText();
    }

    @Override
    public LocalDate getBirthdate() {
        return this.birthdate.getValue();
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber.getText();
    }

    @Override
    public String getEmail() {
        return this.email.getText();
    }

    @Override
    public String getTaxCode() {
        return this.taxCode.getText();
    }

    @Override
    public Double getSalary() {
        return Double.valueOf(this.salary.getText());
    }

    @Override
	public ContractType getContractType() {
		return this.contract.getValue();
	}

	@Override
	public LocalDate getSigninDate() {
		return this.signinDate.getValue();
	}


    @FXML
    private void save() {
        Status status = getObserver().saveEmployeeData();
        if(status != Status.ALL_RIGHT){
        	ViewUtils.showAlertMessage(status.name(), status.getText());
        }else {
        	super.goBack(this.backBtn.getScene());
        }
    }

    @FXML
    private void delete() {
    	if (this.selectedEmployee == null) {
    		ViewUtils.showAlertMessage("Warning", "No employee has been selected");
    	}else {
    		getObserver().deleteEmployeeData(this.selectedEmployee);
    		super.goBack(this.backBtn.getScene());
    		//getObserver().populateEmployeesTable();
    	}
    }

    @FXML
    private void goBack() {
        super.goBack(this.backBtn.getScene());
    }
    
    @FXML
    private void initialize() {
    	this.contract.setItems(FXCollections.observableArrayList(ContractType.values()));
    	this.contract.setValue(ContractType.ANNUAL);
    }
    
}
