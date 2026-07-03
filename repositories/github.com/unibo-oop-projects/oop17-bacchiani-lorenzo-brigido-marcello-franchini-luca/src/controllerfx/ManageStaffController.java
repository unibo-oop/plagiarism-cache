package controllerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class ManageStaffController implements Initializable {

	@FXML
	private JFXButton btnStaff;

	@FXML
	private JFXButton btnDismissStaff;

	@FXML
	private JFXButton btnWorkShifts;

	@FXML
	private Hyperlink hMenù;


	@FXML
	private JFXButton btnStaffList;


	@FXML
	void btnDismissStaff(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/DismissStaff.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}

	@FXML
	void btnOnWorkShifts(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/WorkShifts.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}

	@FXML
	void btnIncrementDoctor(final ActionEvent event) throws IOException {
		final Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/AddWorker.fxml"));
		final Scene tableViewScene = new Scene(tableViewParent);

		//This line gets the Stage information
		final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();

	}

	@FXML
	void hlBack(final ActionEvent event) throws IOException {
		final Parent root = FXMLLoader.load(getClass().getResource("/view/PrimaryLoginImpl.fxml"));
		final Scene scene = new Scene(root);
		final Stage nStage = new Stage();
		nStage.setScene(scene);
		nStage.show();

		final Stage hlLoginStage = (Stage) hMenù.getScene().getWindow();
		hlLoginStage.close();

	}

	@FXML
	void btnStaffList(final ActionEvent event) throws IOException {
		final Parent root = FXMLLoader.load(getClass().getResource("/view/StaffList.fxml"));
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

	}

}
