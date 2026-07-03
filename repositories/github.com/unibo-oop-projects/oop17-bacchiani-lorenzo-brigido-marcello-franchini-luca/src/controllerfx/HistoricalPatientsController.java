package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import control.PatientControllerImpl;
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
import model.Person;


public class HistoricalPatientsController implements Initializable {

	@FXML
	private Hyperlink hMenù;

	@FXML
	private TableView<Person> tableViewHistoricalPatients;

	@FXML
	private TableColumn<Person, String> tableViewGender;

	@FXML
	private TableColumn<Person, String> tableViewName;

	@FXML
	private TableColumn<Person, String> tableViewSurname;

	@FXML
	private TableColumn<Person, String> tableViewIFiscalCode;

	@FXML
	private TableColumn<Person, String> tableViewDateBirth;

	@FXML
	private TableColumn<Person, String> tableViewBirthPlace;

	@FXML
	private TableColumn<Person, String> tableViewResidency;


	@FXML
	void hlBack(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/PatientArea.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();

	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		this.tableViewHistoricalPatients.setItems(FXCollections.observableList(
				PatientControllerImpl
				.instance()
				.getAllPatients()
				.stream()
				.collect(Collectors.toList())));
		this.tableViewGender.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getSex()));
		this.tableViewName.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getName()));
		this.tableViewSurname.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getSurname()));
		this.tableViewIFiscalCode.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getFiscalCode()));
		this.tableViewDateBirth.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getBirthdayDate().toString()));
		this.tableViewBirthPlace.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getBirthPlace()));
		this.tableViewResidency.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getResidency()));

	}

}
