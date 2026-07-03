package view.controllers;

import static view.ViewHandler.getObserver;
import static view.ViewUtils.setVista;

import java.time.LocalDate;
import java.util.List;

import controller.charts.BalanceChartData;
import controller.charts.Chart;
import controller.charts.UsersChartData;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import model.interfaces.Employee;
import view.Administration;
import view.Page;
import view.Vista;

public class AdministrationHandler extends Vista implements Administration {

	@FXML private TableView<Employee> employeesTable;
	@FXML private TableColumn<Employee, String> phoneCol;
	@FXML private TableColumn<Employee, String> surnameCol;
	@FXML private TableColumn<Employee, String> salaryCol;
	@FXML private TableColumn<Employee, String> signinCol;
	@FXML private TableColumn<Employee, String> contractTypeCol;
	@FXML private TableColumn<Employee, String> birthdateCol;
	@FXML private TableColumn<Employee, String> nameCol;
	@FXML private TableColumn<Employee, String> taxCodeCol;
	@FXML private TableColumn<Employee, String> emailCol;
	@FXML private ChoiceBox<Integer> employeesPeriod;
	@FXML private ChoiceBox<Integer> balancePeriod;
	@FXML private LineChart<String, Double> balanceGraph;
	@FXML private BarChart<String, Integer> employeesGraph;
	@FXML private Button backBtn;
	@FXML private Button addEmployeeBtn;


    @FXML private void initialize() {
    	Chart balanceStatistc = new BalanceChartData();
    	balancePeriod.getItems().addAll(FXCollections.observableArrayList(balanceStatistc.getAvailablePeriods()));
    	balancePeriod.setValue(balanceStatistc.getAvailablePeriods().stream().findFirst().get());
    	balancePeriod.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue , newValue) -> {
    		balanceGraph.getData().clear();
    		balanceGraph.getData().add(balanceStatistc.getSeries(newValue));
    	});
    	balanceGraph.getData().add(balanceStatistc.getSeries(LocalDate.now().getYear()));
    	balanceGraph.setTitle(balanceStatistc.getTitle());

    	Chart userStatistc = new UsersChartData();
    	employeesPeriod.getItems().addAll(FXCollections.observableArrayList(userStatistc.getAvailablePeriods()));
    	employeesPeriod.setValue(userStatistc.getAvailablePeriods().stream().findFirst().get());
    	employeesPeriod.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue , newValue) -> {
    		employeesGraph.getData().clear();
    		employeesGraph.getData().add(userStatistc.getSeries(newValue));
    	});
    	employeesGraph.getData().add(userStatistc.getSeries(LocalDate.now().getYear()));
    	employeesGraph.setTitle(userStatistc.getTitle());
    	
    	this.nameCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getPerson().getFirstName()));
		this.surnameCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getPerson().getLastName()));
		this.birthdateCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getPerson().getBirthday().toString()));
		this.taxCodeCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getPerson().getTaxCode()));
		this.phoneCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getPerson().getTelephonNumber()));
		this.emailCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getPerson().getEmail()));
		this.signinCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSigningContract().toString()));
		this.contractTypeCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getContracType().toString()));
		this.salaryCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getSalary())));
		getObserver().populateEmployeesTable();
    }

    @FXML
    void copyRow(MouseEvent event) {
    	if (event.getClickCount() == 2) {
			Employee employee = employeesTable.getSelectionModel().getSelectedItem();
			if (employee != null) {
				setVista(this.employeesTable.getScene(), Page.EMPLOYEE_PROFILE, true);
				getObserver().modifyEmployeeData(employee);
			}
		}
	}

    @FXML
    private void goBack() {
    	super.goBack(this.backBtn.getScene());
    }
    
    @FXML
    private void addEmployee() {
    	setVista(this.addEmployeeBtn.getScene(), Page.EMPLOYEE_PROFILE, true);
    }
    
    @Override
    public void setData(final List<Employee> data) {
    	this.employeesTable.setItems(FXCollections.observableArrayList(data));
    }

}
