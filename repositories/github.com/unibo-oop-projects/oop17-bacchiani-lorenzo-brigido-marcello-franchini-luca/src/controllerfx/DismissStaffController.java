package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import control.LoginControllerImpl;
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

public class DismissStaffController implements Initializable {

	@FXML
	private Hyperlink hMenù;

	@FXML
	private JFXButton btnConfirm;

	@FXML
	private JFXComboBox<String> comboBoxCF;

	@FXML
	private JFXDatePicker dateOfDismiss;

	@FXML
	private JFXTextField textFieldReason;

	@FXML
	void btnOnConfirm(final ActionEvent event) {
		try {
			PersonalControllerImpl.instance().deleteWorker(
					this.comboBoxCF.getValue(), 
					this.dateOfDismiss.getValue(),
					this.textFieldReason.getText());	
			this.update();
			new Alert(Alert.AlertType.INFORMATION, "Operation Confirmed").showAndWait();
		} catch (NoSuchElementException e) {
			new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
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
		// TODO Auto-generated method stub
		this.update();
	}

	private void update() {
		this.comboBoxCF.setItems(FXCollections.observableList(PersonalControllerImpl.
				instance().getWorkers().stream()
				.filter(x -> !x.getFiscalCode().equals(LoginControllerImpl.instance()
						.getStaffLogged()
						.getFiscalCode()))
				.map(x -> x.getFiscalCode())
				.collect(Collectors.toList())));	
	}

}

