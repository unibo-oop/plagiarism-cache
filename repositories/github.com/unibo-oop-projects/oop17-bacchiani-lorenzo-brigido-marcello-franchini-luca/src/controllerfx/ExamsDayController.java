package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import control.LoginControllerImpl;
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
import model.ExamReservation;

public class ExamsDayController implements Initializable {

	@FXML
	private Hyperlink hMenù;

	@FXML
	private TableView<ExamReservation> tableViewExamsDay;

	@FXML
	private TableColumn<ExamReservation, String> tableViewPatientFiscalCode;

	@FXML
	private TableColumn<ExamReservation, String> tableViewExamName;

	@FXML
	private TableColumn<ExamReservation, String> tableViewTime;



	@FXML
	void hlBack(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/ManagePatients.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();

	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.tableViewExamsDay.setItems(FXCollections.observableList(PatientControllerImpl.instance()
				.getExams()
				.stream()
				.filter(x -> x.getDate().equals(LocalDate.now()))
				.filter(x -> x.getStaff().getFiscalCode()
						.equals(LoginControllerImpl.instance().getStaffLogged().getFiscalCode()))
				.collect(Collectors.toList())));
		this.tableViewExamName.setCellValueFactory(cellData -> 
		new SimpleStringProperty(cellData.getValue().getExam().getExamName()));
		this.tableViewTime.setCellValueFactory(cellData -> 
		new SimpleStringProperty(cellData.getValue().getHour().toString()));
		this.tableViewPatientFiscalCode.setCellValueFactory(cellData -> 
		new SimpleStringProperty(cellData.getValue().getPatient().getFiscalCode()));

	}

}
