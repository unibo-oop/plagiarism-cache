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

import control.PersonalControllerImpl;
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
import utilities.Role;

public class AddWorkerController implements Initializable {

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
	private JFXComboBox<String> comboBoxRole;

	@FXML
	private JFXComboBox<String> comboBoxGender;

	@FXML
	private JFXButton btnSaveInfo;

	@FXML
	void btnOnSaveInfo(final ActionEvent event) throws IOException {
		try {
			if (this.comboBoxRole.getValue().equals("Primario")
					|| this.comboBoxRole.getValue().equals("Caposala")
					|| this.comboBoxRole.getValue().equals("Medico")) {
				this.addWorker();
				new Alert(Alert.AlertType.INFORMATION, "Operation Confirmed").showAndWait();
				final Parent root = FXMLLoader.load(getClass().getResource("/view/RegistrationPage.fxml"));
				final Scene scene = new Scene(root);
				final Stage nStage = new Stage();
				nStage.setScene(scene);
				nStage.show();
				final Stage hlLoginStage = (Stage) hMenù.getScene().getWindow();
				hlLoginStage.close();
			} else {
				this.addWorker();
				new Alert(Alert.AlertType.INFORMATION, "Operation confirmed").showAndWait();
				final Parent root = FXMLLoader.load(getClass().getResource("/view/AddWorkShift.fxml"));
				final Scene scene = new Scene(root);
				final Stage nStage = new Stage();
				nStage.setScene(scene);
				nStage.show();
				final Stage hlLoginStage = (Stage) hMenù.getScene().getWindow();
				hlLoginStage.close();
			}
		} catch (IllegalStateException | IllegalArgumentException e) {
			new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
		} catch (NullPointerException e) {
			new Alert(Alert.AlertType.ERROR, "Complete all fields").showAndWait();
		}

	}

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
		this.comboBoxRole.setItems(FXCollections.observableList(Stream.of(Role.values())
				.map(x -> x.getDescr()).collect(Collectors.toList())));
		this.comboBoxGender.setItems(FXCollections.observableList(Stream.of("M", "F").collect(Collectors.toList())));	

	}

	private void addWorker() {
		PersonalControllerImpl.instance().addWorker(
				this.name.getText(), 
				this.surname.getText(), 
				this.fiscalCode.getText(), 
				this.birthPlace.getText(), 
				this.residencyPlace.getText(), 
				this.comboBoxGender.getValue(), 
				this.comboBoxRole.getValue(), 
				this.dateOfBirth.getValue());
	}
}
