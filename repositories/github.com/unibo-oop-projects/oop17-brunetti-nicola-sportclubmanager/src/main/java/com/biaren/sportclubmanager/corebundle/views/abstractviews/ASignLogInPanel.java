package com.biaren.sportclubmanager.corebundle.views.abstractviews;

import com.biaren.utility.viewparts.VerticalLabelTextFieldBox;
import com.biaren.utility.viewparts.VerticalLabelPasswordFieldBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Abstract class that represent a Signin-Login panel, with classical label-fields
 * to let user sign or log in.
 * @author nbrunetti
 *
 */
public abstract class ASignLogInPanel extends VBox {
    private static final int MIN_HEIGHT = 300;
    private static final int MIN_WIDTH = 500;
    
    /** Username field box */
    protected VerticalLabelTextFieldBox username;
    /** Password field box */
    protected VerticalLabelPasswordFieldBox password;
    /** Button submit */
    protected Button submit;
    
    /**
     * Constuctor, not directly callable because is an abstract class,
     * instantiate username and password box.
     */
    protected ASignLogInPanel() {
        this.username = new VerticalLabelTextFieldBox("Username");
        this.password = new VerticalLabelPasswordFieldBox();
    }
    
    /**
     * Set panel layout
     */
    protected void setLayout() {
        this.setPadding(new Insets(10, 50, 50, 50));
        this.setSpacing(10);
        this.setMinWidth(MIN_WIDTH);
        this.setMinHeight(MIN_HEIGHT);
        this.getChildren().addAll(this.username, this.password);
    }

    /**
     * Clear {@link TextField} 
     */
    protected void clearFields() {
        this.username.getField().clear();
        this.password.getField().clear();
    }
    
    /**
     * Get username field value
     * @return {@link String} of username user input
     */
    public String getUsernameFieldValue() {
        return this.username.getField().getText();
    }
    
    /**
     * Get password field value
     * @return {@link String} of password user input
     */
    public String getPasswordFieldValue() {
        return this.password.getField().getText();
    }
    
    /**
     * Get submit button reference
     * @return {@link Button} reference
     */
    public Button getSubmitButton() {
        return this.submit;
    }
    
}
