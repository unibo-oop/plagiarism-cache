package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.NoSuchElementException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class DeceasedPatientsController implements Initializable {

	@FXML
	private Hyperlink hMenù;

	@FXML
	private JFXButton btnConfirm;

	@FXML
	private JFXComboBox<String> comboBoxCF;

	@FXML
	private JFXDatePicker dateOfDeath;

	@FXML
	private JFXTextField textFieldReason;

	@FXML
	private JFXButton btnHDeceased;

	@FXML
	void btnGoHDeceased(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/HistoricalDeaths.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}

	@FXML
	void btnOnConfirm(final ActionEvent event) {
		try {

			PatientControllerImpl.instance().modifyHospitalizationState(
					this.comboBoxCF.getValue(),
					"Deceduto", 
					this.dateOfDeath.getValue(),
					this.textFieldReason.getText());
			this.update();
			new Alert(AlertType.INFORMATION, "Operation confirmed!").showAndWait();
		} catch (NoSuchElementException  | IllegalStateException e) {
			new Alert(AlertType.ERROR, e.getMessage()).showAndWait();

		}

	}

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
		this.update();

	}

	private void update() {
		this.comboBoxCF.setItems(FXCollections.observableList(
				PatientControllerImpl
				.instance()
				.getAllRecoveries()
				.stream()
				.filter(x -> x.getStaff()
						.getFiscalCode()
						.equals(LoginControllerImpl.instance().getStaffLogged().getFiscalCode()))
				.filter(x -> x.getState().equals("Ricoverato"))
				.map(x -> x.getPatient().getFiscalCode())
				.collect(Collectors.toList())));
	}
}
