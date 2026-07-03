package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import control.LoginControllerImpl;
import control.PersonalControllerImpl;
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

public class RegistrationPageController implements Initializable {

	@FXML
	private Hyperlink hMenù;

	@FXML
	private JFXTextField textFieldUser;

	@FXML
	private JFXPasswordField textFieldPass;

	@FXML
	private JFXPasswordField textFieldRePass;

	@FXML
	private JFXButton btnSignup;



	@FXML
	void btnOnSignup(final ActionEvent event) throws IOException {
		if (this.textFieldPass.getText().equals(this.textFieldRePass.getText())) {
			try {
				LoginControllerImpl.instance().addAccount(
						PersonalControllerImpl.instance().getJustAddedWorker(), 
						this.textFieldUser.getText(), 
						this.textFieldPass.getText());
				new Alert(Alert.AlertType.INFORMATION, "Operation Confirmed").showAndWait();
				final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/AddWorkShift.fxml"));
				final Scene tableViewScene = new Scene(tableViewParent);

				//This line gets the Stage information
				final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();
			} catch (NoSuchElementException | IllegalStateException | IllegalArgumentException e) {
				new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
			} 
		} else {
			new Alert(Alert.AlertType.ERROR, "Incorrect password").showAndWait();
		}
	}



	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
