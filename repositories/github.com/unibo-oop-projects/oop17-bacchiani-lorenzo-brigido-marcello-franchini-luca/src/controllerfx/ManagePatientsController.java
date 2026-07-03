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
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class ManagePatientsController implements Initializable {

	@FXML
	private JFXButton btnDeceasedPatient;

	@FXML
	private JFXButton btnNewRecovery;

	@FXML
	private JFXButton btnNewExam;

	@FXML
	private Hyperlink hMenù;

	@FXML
	private JFXButton btnExamDay;

	@FXML
	private JFXButton btnDismissPazient;

	@FXML
	private JFXButton btnNewPrescription;


	@FXML
	void btnDismissPatient(final ActionEvent event) throws IOException {

		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/LeavePatient.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();

	}

	@FXML
	void btnExamsOnDay(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/ExamsDay.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();

	}

	@FXML
	void btnOnDeceasedPatients(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/DeceasedPatients.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();

	}

	@FXML
	void btnNewExam(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/NewExam.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}

	@FXML
	void btnNewRecovery(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/NewRecovery.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();

	}

	@FXML
	void btnNewPrescription(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/NewPrescription.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();

	}

	@FXML
	void hlBack(final ActionEvent event) throws IOException {
        if (LoginControllerImpl.instance().getStaffLogged().getRole().getCod().equals("O9")) {
            final Parent root = FXMLLoader.load(getClass().getResource("/view/PrimaryLoginImpl.fxml"));
            final Scene scene = new Scene(root);
            final Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.show();

            final Stage hlLoginStage = (Stage) hMenù.getScene().getWindow();
            hlLoginStage.close();
        } else {
            final Parent root = FXMLLoader.load(getClass().getResource("/view/DoctorLoginImpl.fxml"));
            final Scene scene = new Scene(root);
            final Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.show();

            final Stage hlLoginStage = (Stage) hMenù.getScene().getWindow();
            hlLoginStage.close();
        }

	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
