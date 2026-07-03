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

public class HistoricalRecoveriesController implements Initializable {

	@FXML
	private Hyperlink hMenù;

	@FXML
	private TableView<Hospitalization> tableViewHistoricalRecoveries;

	@FXML
	private TableColumn<Hospitalization, String> tableViewFiscalCode;

	@FXML
	private TableColumn<Hospitalization, String> tableViewRecoveryCode;

	@FXML
	private TableColumn<Hospitalization, String> tableViewDepartment;

	@FXML
	private TableColumn<Hospitalization, String> tableViewRoom;

	@FXML
	private TableColumn<Hospitalization, String> tableViewBed;

	@FXML
	private TableColumn<Hospitalization, String> tableViewReasonRecovery;

	@FXML
	private TableColumn<Hospitalization, String> tableViewState;

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
		// TODO Auto-generated method stub
		this.tableViewHistoricalRecoveries.setItems(FXCollections.observableList(
				PatientControllerImpl
				.instance()
				.getAllRecoveries()
				.stream()
				.collect(Collectors.toList())));
		this.tableViewFiscalCode.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getPatient().getFiscalCode()));
		this.tableViewRecoveryCode.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getCode()));
		this.tableViewDepartment.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getDepartment().getName()));
		this.tableViewRoom.setCellValueFactory(cellData -> 
			new SimpleStringProperty(Integer.toString(cellData.getValue().getRoom().getNumber())));
		this.tableViewBed.setCellValueFactory(cellData -> 
			new SimpleStringProperty(Integer.toString(cellData.getValue().getBed().getNumber())));
		this.tableViewReasonRecovery.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getCause()));
		this.tableViewState.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getState()));

	}

}
