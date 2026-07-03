package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import control.LoginControllerImpl;
import control.PersonalControllerImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Worker;

public class StaffListController implements Initializable {

	@FXML
	private Hyperlink hMenù;

	@FXML
	private TableView<Worker> tableViewStaffList;

	@FXML
	private TableColumn<Worker, String> tableViewGender;

	@FXML
	private TableColumn<Worker, String> tableViewName;

	@FXML
	private TableColumn<Worker, String> tableViewSurname;

	@FXML
	private TableColumn<Worker, String> tableViewIFiscalCode;

	@FXML
	private TableColumn<Worker, String> tableViewDateBirth;

	@FXML
	private TableColumn<Worker, String> tableViewBirthPlace;

	@FXML
	private TableColumn<Worker, String> tableViewResidency;

	@FXML
	private TableColumn<Worker, String> tableViewOccupation;

	@FXML
	private TableColumn<Worker, String> tableViewUsername;

	@FXML
	void hlBack(final ActionEvent event) throws IOException {
		final Parent root = FXMLLoader.load(getClass().getResource("/view/ManageStaff.fxml"));
		final Scene scene = new Scene(root);
		final Stage nStage = new Stage();
		nStage.setScene(scene);
		nStage.show();

		final Stage hlLoginStage = (Stage) hMenù.getScene().getWindow();
		hlLoginStage.close();

	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.tableViewStaffList.setItems(
				FXCollections.observableList(PersonalControllerImpl
						.instance()
						.getWorkers()
						.stream()
						.collect(Collectors.toList())));
		this.tableViewBirthPlace.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getBirthPlace()));
		this.tableViewDateBirth.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getBirthdayDate().toString()));
		this.tableViewGender.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getSex()));
		this.tableViewIFiscalCode.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getFiscalCode()));
		this.tableViewName.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getName()));
		this.tableViewOccupation.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getRole().getDescr()));
		this.tableViewResidency.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getResidency()));
		this.tableViewSurname.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getSurname()));
		this.tableViewUsername.setCellValueFactory(cellData -> 
			new SimpleStringProperty(
				LoginControllerImpl
				.instance()
				.getAccounts()
				.stream()
				.filter(x -> x.getStaff().getFiscalCode()
						.equals(cellData.getValue().getFiscalCode()))
				.findFirst()
				.get()
				.getUser()));

	}

}
