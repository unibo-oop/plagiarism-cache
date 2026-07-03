package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import control.LoginControllerImpl;
import control.PatientControllerImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import utilities.Department;

public class NewRecoveryController implements Initializable {


	@FXML
	private Hyperlink hMenù;

	@FXML
	private JFXButton btnSaveRecovery;

	@FXML
	private JFXComboBox<String> comboBoxCFPatient;

	@FXML
	private JFXComboBox<String> comboBoxRecoveryCode;

	@FXML
	private JFXComboBox<String> comboBoxRoomBed;

	@FXML
	private JFXTextField textFieldReason;

	@FXML
	private JFXComboBox<String> comboBoxDepartment;

	@FXML
	private JFXButton btnPatient;

	@FXML
	void btnOnSaveInfo(final ActionEvent event) {
		try {
			PatientControllerImpl.instance().addHospitalization(
					this.comboBoxRecoveryCode.getValue(), 
					this.comboBoxCFPatient.getValue(), 
					this.comboBoxDepartment.getValue(), 
					this.comboBoxRoomBed.getValue(), 
					this.textFieldReason.getText(), 
					LoginControllerImpl.instance().getStaffLogged());
			this.update();
			this.textFieldReason.setText("");
			new Alert(AlertType.INFORMATION, "Operation confirmed!").showAndWait();
		} catch (NoSuchElementException | IllegalStateException | IllegalArgumentException e) {
			new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
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
	void comboBoxOnDepartment(final ActionEvent event) {
		this.comboBoxRoomBed.setItems(FXCollections.observableList(PatientControllerImpl.instance()
				.freeBeds(this.comboBoxDepartment.getValue()).stream()
				.map(x -> "camera:" + x.getRoom().getNumber() + "-" + "letto:" + x.getNumber())
				.collect(Collectors.toList())));

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

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.update();
	}

	private void update() {
		this.comboBoxCFPatient.setItems(FXCollections.observableList(PatientControllerImpl.instance()
				.getAllPatients()
				.stream().map(x -> x.getFiscalCode())
				.filter(x -> !PatientControllerImpl.instance().getAllRecoveries()
						.stream()
						.filter(y -> y.getState().equals("Ricoverato") || y.getState().equals("Deceduto"))
						.anyMatch(y -> y.getPatient().getFiscalCode().equals(x)))
				.collect(Collectors.toList())));
		this.comboBoxDepartment.setItems(FXCollections.observableList(Stream.of(Department.values())
				.map(x -> x.getName())
				.collect(Collectors.toList())));
		this.comboBoxRecoveryCode.setItems(FXCollections.observableList(IntStream.iterate(0, x -> x + 1)
				.mapToObj(x -> "R" + x)
				.filter(x -> !PatientControllerImpl.instance().getAllRecoveries()
						.stream()
						.anyMatch(y -> y.getCode().equals(x)))
				.limit(500)
				.collect(Collectors.toList())));
	}

}
