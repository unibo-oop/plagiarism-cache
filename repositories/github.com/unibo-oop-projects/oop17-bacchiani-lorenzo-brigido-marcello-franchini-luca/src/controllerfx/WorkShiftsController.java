package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;

import control.PersonalControllerImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Workshift;

public class WorkShiftsController implements Initializable {

	@FXML
	private Hyperlink hMenù;

	@FXML
	private TableView<Workshift> tableViewWorkShifts;

	@FXML
	private TableColumn<Workshift, String> tableViewOccupation;

	@FXML
	private TableColumn<Workshift, String> tableViewName;

	@FXML
	private TableColumn<Workshift, String> tableViewSurname;

	@FXML
	private TableColumn<Workshift, String> tableViewIFiscalCode;

	@FXML
	private TableColumn<Workshift, String> tableViewDepartment;

	@FXML
	private TableColumn<Workshift, String> tableViewStartTime;

	@FXML
	private TableColumn<Workshift, String> tableViewEndTime;

	@FXML
	private TableColumn<Workshift, String> tableViewState;

	@FXML
	private JFXButton btnChangeWorkShifts;

	@FXML
	void btnOnChangeWorkShifts(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/WorkShiftsChange.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}

	@FXML
	void hlBack(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/ManageStaff.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();

	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.tableViewWorkShifts.setItems(
				FXCollections.observableList(PersonalControllerImpl
						.instance()
						.getWorkshifts()
						.stream()
						.collect(Collectors.toList())));
		this.tableViewDepartment.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getDepartment().getName()));
		this.tableViewEndTime.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getEndTime().toString()));
		this.tableViewIFiscalCode.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getWorker().getFiscalCode()));
		this.tableViewName.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getWorker().getName()));
		this.tableViewOccupation.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getWorker().getRole().getDescr()));
		this.tableViewStartTime.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getStartingTime().toString()));
		this.tableViewState.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getState()));
		this.tableViewSurname.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getWorker().getSurname()));
	}

}
