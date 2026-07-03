package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

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

public class PatientInformationController implements Initializable {

	@FXML
	private Hyperlink hMenù;

	@FXML
	private JFXButton btnShowMedical;

	@FXML
	private JFXComboBox<String> comboBoxCF;

	@FXML
	void btnShowOnMedical(final ActionEvent event) throws IOException {
		if (this.comboBoxCF.getValue() == null) {
			new Alert(AlertType.ERROR, "Select a patient").showAndWait();
		} else {
			PatientControllerImpl.instance().setPatientForInf(this.comboBoxCF.getValue());
			final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MedicalRecord.fxml"));
			final Parent root1 = (Parent) fxmlLoader.load();
			final Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.show();
		}
	}

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
		this.comboBoxCF.setItems(FXCollections.observableList(PatientControllerImpl
				.instance()
				.getAllPatients()
				.stream()
				.map(x -> x.getFiscalCode())
				.collect(Collectors.toList())));

	}

}
