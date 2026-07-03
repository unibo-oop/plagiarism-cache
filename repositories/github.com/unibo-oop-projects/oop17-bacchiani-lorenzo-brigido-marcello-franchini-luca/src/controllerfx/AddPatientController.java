package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import control.PatientControllerImpl;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class AddPatientController implements Initializable {

	@FXML
	private JFXTextField name;

	@FXML
	private JFXTextField surname;

	@FXML
	private JFXTextField fiscalCode;

	@FXML
	private JFXDatePicker dateOfBirth;

	@FXML
	private JFXTextField birthPlace;

	@FXML
	private JFXTextField residencyPlace;

	@FXML
	private Hyperlink hMenù;

	@FXML
	private Hyperlink hMenùNE;

	@FXML
	private Hyperlink hMenùNR;

	@FXML
	private JFXButton btnSaveInfo;

	@FXML
	private JFXComboBox<String> comboBoxGender;

	@FXML
	void btnOnSaveInfo(final ActionEvent event) {
		try {
			PatientControllerImpl.instance().addPatient(name.getText(), surname.getText(),
					fiscalCode.getText(), dateOfBirth.getValue(), birthPlace.getText(), residencyPlace.getText(), comboBoxGender.getValue());
			new Alert(Alert.AlertType.INFORMATION, "Operation Confirmed").showAndWait();
		} catch (IllegalArgumentException | IllegalStateException e) {
			new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
		}
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

	@FXML
	void hlBackNE(final ActionEvent event) throws IOException {
		final Parent root = FXMLLoader.load(getClass().getResource("/view/NewExam.fxml"));
		final Scene scene = new Scene(root);
		final Stage nStage = new Stage();
		nStage.setScene(scene);
		nStage.show();
		final Stage hlLoginStage = (Stage) hMenù.getScene().getWindow();
		hlLoginStage.close();
	}

	@FXML
	void hlBackNR(final ActionEvent event) throws IOException {
		final Parent root = FXMLLoader.load(getClass().getResource("/view/NewRecovery.fxml"));
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
		this.comboBoxGender.setItems(FXCollections.observableList(Stream.of("M", "F").collect(Collectors.toList())));	
	}
}
