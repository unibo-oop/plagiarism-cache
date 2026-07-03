package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;
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
import utilities.Department;

public class AddWorkShiftController implements Initializable {

	@FXML
	private JFXButton btnConfirm;

	@FXML
	private JFXTimePicker selectEndTime;

	@FXML
	private JFXTimePicker selectStartTime;

	@FXML
	private JFXComboBox<String> comboBoxDepartment;

	@FXML
	private Hyperlink hMenù;

	@FXML
	void btnOnConfirm(final ActionEvent event) {
		try {
			PersonalControllerImpl.instance().addWorkshift(PersonalControllerImpl.instance()
					.getJustAddedWorker(), 
					this.comboBoxDepartment.getValue().toString(),
					this.selectStartTime.getValue(),
					this.selectEndTime.getValue());
			new Alert(Alert.AlertType.INFORMATION, "Operation confirmed").showAndWait();
		} catch (IllegalArgumentException e) {
			new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
		} catch (NullPointerException e) {
			new Alert(Alert.AlertType.ERROR, "Complete all fields!").showAndWait();
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
		this.comboBoxDepartment.setItems(FXCollections.observableList(
				Stream.of(Department.values())
				.map(x -> x.getName())
				.collect(Collectors.toList())));
	}

}
