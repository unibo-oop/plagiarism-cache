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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MyAccountController implements Initializable {

	@FXML
	private Hyperlink hMenù;

	@FXML
	private Label labelUsername;

	@FXML
	private Label labelGender;

	@FXML
	private Label labelName;

	@FXML
	private Label labelSurname;

	@FXML
	private Label labelCF;

	@FXML
	private Label labelDateBirth;

	@FXML
	private Label labelBirthPlace;

	@FXML
	private Label labelResidency;

	@FXML
	private Label labelOccupation;

	@FXML
	private JFXButton btnChangeUsername;

	@FXML
	private JFXButton btnChangePassword;

	@FXML
	void btnOnChangePassword(final ActionEvent event) throws IOException {
		final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ChangePassword.fxml"));
		final Parent root1 = (Parent) fxmlLoader.load();
		final Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
	}

	@FXML
	void btnOnChangeUsername(final ActionEvent event) throws IOException {
		final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ChangeUsername.fxml"));
		final Parent root1 = (Parent) fxmlLoader.load();
		final Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
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
		this.labelUsername.setText(LoginControllerImpl
				.instance()
				.getAccounts()
				.stream()
				.filter(x -> x.getStaff().getFiscalCode().equals(LoginControllerImpl
						.instance().getStaffLogged().getFiscalCode()))
				.findFirst()
				.get()
				.getUser());
		this.labelBirthPlace.setText(LoginControllerImpl.instance().getStaffLogged().getBirthPlace());
		this.labelCF.setText(LoginControllerImpl.instance().getStaffLogged().getFiscalCode());
		this.labelDateBirth.setText(LoginControllerImpl.instance().getStaffLogged().getBirthdayDate().toString());
		this.labelGender.setText(LoginControllerImpl.instance().getStaffLogged().getSex());
		this.labelName.setText(LoginControllerImpl.instance().getStaffLogged().getName());
		this.labelOccupation.setText(LoginControllerImpl.instance().getStaffLogged().getRole().getDescr());
		this.labelResidency.setText(LoginControllerImpl.instance().getStaffLogged().getResidency());
		this.labelSurname.setText(LoginControllerImpl.instance().getStaffLogged().getSurname());

	}

}
