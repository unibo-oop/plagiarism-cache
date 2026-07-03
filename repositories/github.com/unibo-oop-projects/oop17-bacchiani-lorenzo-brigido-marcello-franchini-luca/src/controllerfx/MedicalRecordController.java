package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import control.PatientControllerImpl;
import control.PersonalControllerImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.ExamReservation;
import model.Hospitalization;
import model.Person;
import model.Prescription;


public class MedicalRecordController implements Initializable {

    @FXML
	private Hyperlink hMenù;

	@FXML
	private Label labelPatient;

	@FXML
	private TableView<Person> tableViewPersonalData;

	@FXML
	private TableColumn<Person, String> tableViewGender;

	@FXML
	private TableColumn<Person, String> tableViewName;

	@FXML
	private TableColumn<Person, String> tableViewSurname;

	@FXML
	private TableColumn<Person, String> tableViewIFiscalCode;

	@FXML
	private TableColumn<Person, String> tableViewBirth;

	@FXML
	private TableColumn<Person, String> tableViewBirthPlace;

	@FXML
	private TableColumn<Person, String> tableViewRecidencyPlace;

	@FXML
	private TableView<Hospitalization> tableViewRecoveriesData;

	@FXML
	private TableColumn<Hospitalization, String> tableViewStartDate;

	@FXML
	private TableColumn<Hospitalization, String> tableViewEndDate;

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
	private TableView<ExamReservation> tableViewExamsData;

	@FXML
	private TableColumn<ExamReservation, String> tableViewExamDoctorFC;

	@FXML
	private TableColumn<ExamReservation, String> tableViewExamName;

	@FXML
	private TableColumn<ExamReservation, String> tableViewDate;

	@FXML
	private TableColumn<ExamReservation, String> tableViewTime;

	@FXML
	private TableView<Prescription> tableViewMedicinesData;

	@FXML
	private TableColumn<Prescription, String> tableViewMedicineDoctorFC;

	@FXML
	private TableColumn<Prescription, String> tableViewMedicineName;

	@FXML
	private TableColumn<Prescription, String> tableViewRecipeCode;

	@FXML
	private TableColumn<Prescription, String> tableViewPrice;


	@FXML
	void hlBack(final ActionEvent event) throws IOException {
		//This lines close the Stage
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.close();
	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.labelPatient.setText("Medical Record of " + 
		PatientControllerImpl.instance().getPatientForInf().getSurname());
		this.tableViewPersonalData.setItems(FXCollections.observableList(Stream.of(PatientControllerImpl
				.instance()
				.getPatientForInf())
				.collect(Collectors.toList())));
		this.tableViewGender.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getSex()));
		this.tableViewName.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getName()));
		this.tableViewSurname.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getSurname()));
		this.tableViewIFiscalCode.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getFiscalCode()));
		this.tableViewBirth.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getBirthdayDate().toString()));
		this.tableViewBirthPlace.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getBirthPlace()));
		this.tableViewRecidencyPlace.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getResidency()));

		this.tableViewRecoveriesData.setItems(FXCollections.observableList(PatientControllerImpl
				.instance()
				.getAllRecoveries()
				.stream()
				.filter(x -> x.getPatient().getFiscalCode().equals(PatientControllerImpl
						.instance().getPatientForInf().getFiscalCode()))
				.collect(Collectors.toList())));
		this.tableViewRecoveryCode.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getCode()));
		this.tableViewDepartment.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getDepartment().getName()));
		this.tableViewRoom.setCellValueFactory(cellData -> 
			new SimpleStringProperty(Integer.toString(cellData
				.getValue().getRoom().getNumber())));
		this.tableViewBed.setCellValueFactory(cellData -> 
			new SimpleStringProperty(Integer.toString(cellData
				.getValue().getBed().getNumber())));
		this.tableViewReasonRecovery.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getCause()));
		this.tableViewStartDate.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getInitDate().toString()));
		this.tableViewEndDate.setCellValueFactory(cellData -> 
			cellData.getValue().getEndDate() == null ? new SimpleStringProperty("") 
			        : new SimpleStringProperty(cellData.getValue().getEndDate().toString()));

		this.tableViewExamsData.setItems(FXCollections.observableList(
				PatientControllerImpl
				.instance()
				.getExams()
				.stream()
				.filter(x -> x.getPatient().getFiscalCode().equals(PatientControllerImpl
						.instance().getPatientForInf().getFiscalCode()))
				.collect(Collectors.toList())));
		this.tableViewExamDoctorFC.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue()
				.getStaff().getFiscalCode()));
		this.tableViewExamName.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getExam().getExamName()));
		this.tableViewDate.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getDate().toString()));
		this.tableViewPrice.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getMedicine().getPrezzo()));
		this.tableViewTime.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getHour().toString()));

		this.tableViewMedicinesData.setItems(FXCollections.observableList(
				PersonalControllerImpl
				.instance()
				.getPrescriptions()
				.stream()
				.filter(x -> x.getPatient().getFiscalCode().equals(PatientControllerImpl
						.instance().getPatientForInf().getFiscalCode()))
				.collect(Collectors.toList())));
		this.tableViewMedicineDoctorFC.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getStaff().getFiscalCode()));
		this.tableViewMedicineName.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getMedicine().getNome()));
		this.tableViewRecipeCode.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getCode()));
		this.tableViewMedicineDoctorFC.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getStaff().getFiscalCode()));





	}

}
