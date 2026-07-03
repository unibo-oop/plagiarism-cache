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

import control.PatientControllerImpl;
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
import utilities.Medicine;

public class NewPrescriptionController implements Initializable {

	@FXML
	private Hyperlink hMenù;

	@FXML
	private JFXButton btnSavePrescrition;

	@FXML
	private JFXComboBox<String> comboBoxMedicineName;

	@FXML
	private JFXComboBox<String> comboBoxCodeRecipe;

	@FXML
	private JFXTextField textFieldPrice;

	@FXML
	private JFXComboBox<String> comboBoxCFPatient;

	@FXML
	void btnOnSavePrescrition(final ActionEvent event) {
		try {
			PersonalControllerImpl.instance().addPrescription(this.comboBoxMedicineName.getValue(),
					this.comboBoxCFPatient.getValue(),
					this.comboBoxCodeRecipe.getValue());
			new Alert(Alert.AlertType.INFORMATION, "Operation Confirmed").showAndWait();
		} catch (NoSuchElementException | IllegalArgumentException e) {
			new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
		} catch (NullPointerException e) {
			new Alert(Alert.AlertType.ERROR, "Complete all fields!").showAndWait();
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
	void chooseMedicine(final ActionEvent event) {
		this.textFieldPrice.setText(Stream.of(Medicine.values())
				.filter(x -> x.getNome().equals(this.comboBoxMedicineName.getValue()))
				.findFirst()
				.get()
				.getPrezzo());
	}
	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.comboBoxCodeRecipe.setItems(FXCollections.observableList(IntStream.iterate(0, x -> x + 1)
				.mapToObj(x -> "P" + x)
				.filter(x -> !PersonalControllerImpl.instance().getPrescriptions()
						.stream()
						.anyMatch(y -> y.getCode().equals(x)))
				.limit(500)
				.collect(Collectors.toList())));	
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
		this.comboBoxMedicineName.setItems(FXCollections.observableList(Stream.of(Medicine.values())
				.map(x -> x.getNome())
				.collect(Collectors.toList())));

	}

}
