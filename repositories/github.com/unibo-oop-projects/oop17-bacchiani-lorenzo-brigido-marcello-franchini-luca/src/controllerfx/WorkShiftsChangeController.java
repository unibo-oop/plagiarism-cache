package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import utilities.Department;

public class WorkShiftsChangeController implements Initializable {

	@FXML
	private JFXButton btnConfirm;

	@FXML
	private JFXComboBox<String> comboBoxCF;

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
			PersonalControllerImpl.instance().modifyWorkshift(this.comboBoxCF.getValue(), 
					this.comboBoxDepartment.getValue(), 
					this.selectStartTime.getValue(), 
					this.selectEndTime.getValue());	
			new Alert(Alert.AlertType.INFORMATION, "Operation confirmed").showAndWait();
		}  catch (IllegalStateException | IllegalArgumentException | NoSuchElementException e) {
			new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
		} 

	}

	@FXML
	void hlBack(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/WorkShifts.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();

	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.comboBoxDepartment.setItems(FXCollections.observableList(
				Stream.of(Department.values())
				.map(x -> x.getName())
				.collect(Collectors.toList())));
		this.comboBoxCF.setItems(FXCollections.observableList(PersonalControllerImpl.
				instance()
				.getWorkers()
				.stream()
				.map(x -> x.getFiscalCode())
				.collect(Collectors.toList())));	

	}

}
