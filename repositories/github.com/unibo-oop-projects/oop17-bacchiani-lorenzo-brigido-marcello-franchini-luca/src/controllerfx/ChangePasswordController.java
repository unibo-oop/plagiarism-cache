package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import control.LoginControllerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class ChangePasswordController implements Initializable {

	@FXML
	private JFXButton btnConfirm;

	@FXML
	private JFXTextField textFieldNewPassword;

	@FXML
	private Hyperlink hMenù;

	@FXML
	void btnOnConfirm(final ActionEvent event) {
		try {
			LoginControllerImpl.instance().modifyPassword(this.textFieldNewPassword.getText());
			new Alert(Alert.AlertType.INFORMATION, "Operation confirmed").showAndWait();
		} catch (IllegalStateException e) {
			new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
		}
	}

	@FXML
	void hlBack(final ActionEvent event) throws IOException {
		//This lines close the Stage
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.close();
	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
