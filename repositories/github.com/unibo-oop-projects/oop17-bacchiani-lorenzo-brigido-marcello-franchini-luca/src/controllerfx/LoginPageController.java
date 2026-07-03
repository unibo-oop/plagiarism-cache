package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import control.LoginControllerImpl;

public class LoginPageController implements Initializable {

	@FXML
	private JFXTextField tfUsername;

	@FXML
	private JFXPasswordField pfPassword;

	@FXML
	private JFXButton btnLogin;

	@FXML
	private Hyperlink hlHelp;

	@FXML
	private Hyperlink hMenù;

	@FXML
	void hlBack(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/MainPanel.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}

	@FXML
	void btnOnLogin(final ActionEvent event) throws IOException {
		if (LoginControllerImpl
				.instance()
				.verifyAccount(this.tfUsername.getText(), this.pfPassword.getText())) {
			if (LoginControllerImpl
					.instance()
					.isPrimary(this.tfUsername.getText(), this.pfPassword.getText())) {
				LoginControllerImpl
				.instance()
				.setStaffLogged(this.tfUsername.getText(), this.pfPassword.getText());
				final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/PrimaryLoginImpl.fxml"));
				final Scene tableViewScene = new Scene(tableViewParent);
				//This line gets the Stage information
				final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();
			} else {
				LoginControllerImpl.instance().setStaffLogged(this.tfUsername.getText(), this.pfPassword.getText());
				final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/DoctorLoginImpl.fxml"));
				final Scene tableViewScene = new Scene(tableViewParent);
				//This line gets the Stage information
				final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();
			}
		} else {
			new Alert(Alert.AlertType.ERROR, "Username or password are wrong").showAndWait();
		}
	}

	@FXML
	void hlOnHelp(final ActionEvent event) {
		new Alert(Alert.AlertType.INFORMATION, "If you are a primary, simply log in with your credentials. Otherwise if you are a doctor and do not know your data, contact your hospital's primary.").showAndWait();

	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
