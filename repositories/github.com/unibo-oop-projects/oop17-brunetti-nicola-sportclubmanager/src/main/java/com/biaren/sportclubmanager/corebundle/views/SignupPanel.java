package com.biaren.sportclubmanager.corebundle.views;

import com.biaren.sportclubmanager.adminbundle.model.interfaces.User;
import com.biaren.sportclubmanager.corebundle.views.abstractviews.ASignLogInPanel;
import com.biaren.sportclubmanager.utility.enums.TextStrings;
import com.biaren.utility.viewparts.VerticalLabelPasswordFieldBox;
import com.biaren.utility.viewparts.VerticalLabelTextFieldBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Represent a panel view signup a new {@link User}.
 * Extends {@link ASignLogInPanel}.
 * @author nbrunetti
 *
 */
public class SignupPanel extends ASignLogInPanel {
    
    private Label signInLabel;
    private VerticalLabelTextFieldBox emailBox;
    protected VerticalLabelPasswordFieldBox passwordConfirm;
    
    /**
     * Creates a {@link SignupPanel}, instantiate fields and set layout.
     */
    public SignupPanel() {
        super();
        this.passwordConfirm = new VerticalLabelPasswordFieldBox();
        this.passwordConfirm.getLabel().setText(TextStrings.PASSWORD_CONFIRM.toString());
        this.signInLabel = new Label(TextStrings.REGISTER_NEW_USER.toString());
        this.emailBox = new VerticalLabelTextFieldBox(TextStrings.EMAIL_LABEL.toString());
        this.submit = new Button(TextStrings.REGISTER_LABEL.toString());
        this.setLayout();
    }
    
    @Override
    protected void setLayout() {
        super.setLayout();
        this.getChildren().add(0, this.signInLabel);
        this.getChildren().addAll(this.emailBox, this.submit);
    }
    
    /**
     * Get password confirm value inserted by user from field
     * @return {@link String} of password confirm value
     */
    public String getPasswordConfirmFieldValue() {
        return this.passwordConfirm.getField().getText();
    }
    
    /**
     * Get email value inserted by user from field
     * @return {@link String} of email value
     */
    public String getEmailFieldValue() {
        return this.emailBox.getField().getText();
    }
}
