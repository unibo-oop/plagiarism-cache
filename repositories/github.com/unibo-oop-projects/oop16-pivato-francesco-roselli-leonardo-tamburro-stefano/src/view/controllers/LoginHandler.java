package view.controllers;

import static view.ViewHandler.getObserver;
import static view.ViewUtils.setVista;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.enumerations.Status;
import view.Login;
import view.Page;
import view.ViewUtils;

public class LoginHandler implements Login{
	
	 @FXML private TextField userId;
	 @FXML private PasswordField password;
	 @FXML private Button loginBtn;
	 @FXML private ImageView title;
	 @FXML private BorderPane mainPane;

	
	public LoginHandler() {}
	
	@FXML private void actionLogin() {
		Status status = getObserver().login();
		if(status == Status.ALL_RIGHT) {
			setVista(this.loginBtn.getScene(), Page.MENU, true);
		}
		else {
			ViewUtils.showAlertMessage(status.name(), status.getText());
		}
	}

	@Override
	public String getUser() {
		return this.userId.getText();
	}

	@Override
	public String getPassword() {
		return this.password.getText();
	}

}
