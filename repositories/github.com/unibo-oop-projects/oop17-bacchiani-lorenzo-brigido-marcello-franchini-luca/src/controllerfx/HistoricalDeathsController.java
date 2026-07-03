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
import model.Hospitalization;

public class HistoricalDeathsController implements Initializable {

	@FXML
	private Hyperlink hMenù;

	@FXML
	private TableView<Hospitalization> tableViewHistoricalDeaths;

	@FXML
	private TableColumn<Hospitalization, String> tableViewGender;

	@FXML
	private TableColumn<Hospitalization, String> tableViewName;

	@FXML
	private TableColumn<Hospitalization, String> tableViewSurname;

	@FXML
	private TableColumn<Hospitalization, String> tableViewIFiscalCode;

	@FXML
	private TableColumn<Hospitalization, String> tableViewDateDeath;

	@FXML
	private TableColumn<Hospitalization, String> tableViewReasonDeath;

	@FXML
	void hlBack(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/DeceasedPatients.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.tableViewHistoricalDeaths.setItems(FXCollections.observableList(
				PatientControllerImpl
				.instance()
				.getAllRecoveries()
				.stream()
				.filter(x -> x.getState().equals("Deceduto"))
				.collect(Collectors.toList())));
		this.tableViewDateDeath.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getEndDate().toString()));
		this.tableViewName.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getPatient().getName()));
		this.tableViewGender.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getPatient().getSex()));
		this.tableViewSurname.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getPatient().getSurname()));
		this.tableViewIFiscalCode.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getPatient().getFiscalCode()));
		this.tableViewReasonDeath.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getNote()));

	}
}
