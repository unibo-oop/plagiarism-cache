package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import control.LoginControllerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DoctorLoginImplController implements Initializable {

	@FXML
	private JFXButton btnAccount;

	@FXML
	private JFXButton btnPaz;

	@FXML
	private Label labelDr;

	@FXML
	private JFXButton btnBack;



	@FXML
	void btnOnAccount(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/MyAccount.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();

	}

	@FXML
	void btnOnBack(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}

	@FXML
	void btnPazienti(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/ManagePatients.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();

	}


	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.labelDr.setText("Welcome Dr. " + LoginControllerImpl.instance().getStaffLogged().getSurname());	

	}

}
