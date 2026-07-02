package com.biaren.sportclubmanager.corebundle.controller;

import com.biaren.sportclubmanager.adminbundle.controller.LoginController;
import com.biaren.sportclubmanager.corebundle.views.LoginPanel;
import com.biaren.utility.BiarenUtil;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Basic controller for {@link LoginPanel}.
 * @author nbrunetti
 *
 */
public class LoginFormController {
    
    private LoginPanel view;
    
    /**
     * Creates a {@link LoginFormController},
     * set the panel to attach this controller.
     * @param view panel
     */
    public LoginFormController(final LoginPanel view) {
        this.view = view;
        this.attachEvents();
    }
    
    private void attachEvents() {
        this.view.getSubmitButton().setOnAction(e -> this.submitHandler());
    }
    
    private void submitHandler() {
        if (LoginController.getInstance().loginUser(this.view.getUsernameFieldValue(), this.view.getPasswordFieldValue())) {
            Stage oldStage = (Stage) this.view.getScene().getWindow();
            oldStage.close();
            Stage newStage = new Stage();
            new MainController(newStage);
        } else {
            BiarenUtil.showBasicAlert(AlertType.ERROR, "Username o password errati", "", "");
        }
    }
}
