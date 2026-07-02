package iuniversity.view.login;

import iuniversity.controller.login.LoginController;
import iuniversity.view.AbstractView;
import iuniversity.view.PageSwitcher;
import iuniversity.view.Pages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * The login view.
 */
public class LoginViewImpl extends AbstractView implements LoginView {

    private static final String INCORRECT_CREDENTIALS_MESSAGE = "Username o password non corretti";

    @FXML
    private Label errorLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    @Override
    public final void login(final String username, final String password) {
        ((LoginController) this.getController()).login(username, password);
    }

    @Override
    public final void incorrectCredentials() {
        this.errorLabel.setText(INCORRECT_CREDENTIALS_MESSAGE);

    }

    @FXML
    public final void initialize() {
        loginButton.setOnAction(e -> {
            this.login(usernameTextField.getText(), passwordTextField.getText());
        });
    }

    @Override
    public void start() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToAdminHomePage() {
        PageSwitcher.goToPage(this.getStage(), Pages.ADMIN_HOME, this.getController().getModel());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void goToStudentHomePage() {
        PageSwitcher.goToPage(this.getStage(), Pages.STUDENT_HOME, this.getController().getModel());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void goToTeacherHomePage() {
        PageSwitcher.goToPage(this.getStage(), Pages.TEACHER_HOME, this.getController().getModel());
    }

}
