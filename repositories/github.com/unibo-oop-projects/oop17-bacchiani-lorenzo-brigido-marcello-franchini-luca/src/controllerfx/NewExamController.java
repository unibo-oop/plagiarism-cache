package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.ExamReservation;
import utilities.Exam;

public class NewExamController implements Initializable {

	@FXML
	private JFXDatePicker dateOfExam;

	@FXML
	private Hyperlink hMenù;

	@FXML
	private JFXButton btnSavePrenotation;

	@FXML
	private JFXComboBox<String> comboBoxExamName; 

	@FXML
	private JFXComboBox<String> comboBoxCFPatient;

	@FXML
	private JFXTimePicker selectTime;

	@FXML
	private JFXButton btnPatient;

	@FXML
	private TableView<ExamReservation> tableViewSelectedExams;

	@FXML
	private TableColumn<ExamReservation, String> tableViewDate;

	@FXML
	private TableColumn<ExamReservation, String> tableViewTime;


	@FXML
	void btnOnSaveInfo(final ActionEvent event) {
		try {
			PatientControllerImpl.instance().addBook(this.dateOfExam.getValue(), 
					this.selectTime.getValue(),
					this.comboBoxCFPatient.getValue(),
					this.comboBoxExamName.getValue(), 
					LoginControllerImpl.instance().getStaffLogged());
			new Alert(Alert.AlertType.INFORMATION, "Operation Confirmed!").showAndWait();
		} catch (NullPointerException e) {
			new Alert(Alert.AlertType.ERROR, "Complete all fields!").showAndWait();
		} catch (NoSuchElementException | IllegalArgumentException e) {
			new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
		}
	}



	@FXML
	void btnIncrementPatients(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/AddPatient.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}

	@FXML
	void onSelectedExam(final ActionEvent event) {
		this.tableViewSelectedExams.setItems(FXCollections
				.observableList(
						PatientControllerImpl
						.instance()
						.getExams()
						.stream()
						.filter(x -> x.getExam().getExamName()
								.equals(this.comboBoxExamName.getValue()))
						.filter(x -> x.getDate().compareTo(LocalDate.now()) >= 0)
						.collect(Collectors.toList())));
		this.tableViewDate.setCellValueFactory(cellData -> 
		new SimpleStringProperty(cellData.getValue().getDate().toString()));
		this.tableViewTime.setCellValueFactory(cellData -> 
		new SimpleStringProperty(cellData.getValue().getHour().toString()));	


	}

	@FXML
	void hlBack(final ActionEvent event) throws IOException {
		final Parent root = FXMLLoader.load(getClass().getResource("/view/ManagePatients.fxml"));
		final Scene scene = new Scene(root);
		final Stage nStage = new Stage();
		nStage.setScene(scene);
		nStage.show();

		final Stage hlLoginStage = (Stage) hMenù.getScene().getWindow();
		hlLoginStage.close();

	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {

		this.comboBoxCFPatient.setItems(FXCollections.observableList(
				PatientControllerImpl.instance().getAllPatients()
				.stream()
				.filter(x -> !PatientControllerImpl
						.instance()
						.getAllRecoveries()
						.stream()
						.filter(y -> y.getState().equals("Deceduto"))
						.anyMatch(y -> y.getPatient().getFiscalCode().equals(x.getFiscalCode())))
				.map(x -> x.getFiscalCode())
				.collect(Collectors.toList())));
		this.comboBoxExamName.setItems(FXCollections.observableList(
				Stream.of(Exam.values())
				.map(x -> x.getExamName())
				.collect(Collectors.toList())));
	}

}
