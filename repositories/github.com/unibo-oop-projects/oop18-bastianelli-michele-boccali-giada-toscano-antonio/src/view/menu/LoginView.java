package view.menu;

import common.MsgStrings;
import controller.menu.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.GenericView;

public class LoginView extends GenericView {

    private final TextField usernameField;
    private final PasswordField passwordField;
    private final Label error;

    public LoginView(final Controller c) {
        super(c);
        final BorderPane borderPane = new BorderPane();
        super.init(borderPane, 90.0, 150.0, 40.0, 40.0);

        // title
        final Label title = new Label("Jump Mania");
        title.setId("title");
        final HBox titleBox = new HBox();
        titleBox.getChildren().add(title);
        borderPane.setTop(titleBox);

        // central
        final VBox textBox = new VBox();

        final Label username = createLabel("Username");
        usernameField = new TextField();

        final Label password = createLabel("Password");
        passwordField = new PasswordField();

        error = new Label();
        error.setVisible(false);
        error.setId("error-label");

        textBox.getChildren().addAll(username, usernameField, password, passwordField, error);
        borderPane.setCenter(textBox);

        // bottom
        final HBox buttonBox = new HBox();

        final Button loginBtn = new MsgEventButton(this, MsgStrings.LOGIN).getButton();
        final Button newProfileBtn = new MsgEventButton(this, MsgStrings.REGISTER).getButton();

        buttonBox.getChildren().addAll(loginBtn, newProfileBtn);
        borderPane.setBottom(buttonBox);
    }

    private Label createLabel(final String name) {
        return new Label(name);
    }

    /**
     * Returns the given input username
     * 
     * @return the username
     */
    public String getUsername() {
        return usernameField.getText();
    }

    /**
     * Returns the given input password
     * 
     * @return the password
     */
    public String getPassword() {
        return passwordField.getText();
    }

    /**
     * Set the error message and set it visible
     * 
     * @param errorMsg : String with the error message
     */
    public void setErrorMsg(final String errorMsg) {
        error.setText(errorMsg);
        error.setVisible(true);
    }
}
