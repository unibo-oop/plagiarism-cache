package com.biaren.sportclubmanager.corebundle.views;

import com.biaren.sportclubmanager.corebundle.views.abstractviews.ASignLogInPanel;
import com.biaren.sportclubmanager.utility.enums.TextStrings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Simple login panel. Presents a two box label-field for
 * insert username and password data to submit for login.
 * Extends {@link VBox}.
 * @author nbrunetti
 *
 */
public class LoginPanel extends ASignLogInPanel{
    
    private Label welcomeLabel;
    private Label loginErrorLabel;
    
    /**
     * Creates a {@link LoginPanel}, instantiate fields
     * and set layout.
     */
    public LoginPanel() {
        super();
        this.welcomeLabel = new Label();
        this.submit  = new Button(TextStrings.LOGIN_LABEL.toString());
        this.loginErrorLabel = new Label(TextStrings.FAILED_LOGIN.toString());
        this.loginErrorLabel.setTextFill(Color.RED);
        this.loginErrorLabel.setVisible(false);
        this.setLayout();
    }
    
    /**
     * Set the layout of the LoginPanel box.
     */
    protected void setLayout() {
        super.setLayout();
        this.getChildren().add(0, this.welcomeLabel);
        this.getChildren().addAll(this.submit, this.loginErrorLabel);
    }
    
    /**
     * Enable label visibility.
     */
    protected void enableErrorLabel() {
        this.loginErrorLabel.setVisible(true);
    }
    
    /**
     * Disable label visibility.
     */
    protected void disableErrorLabel() {
        this.loginErrorLabel.setVisible(false);
    }

}
