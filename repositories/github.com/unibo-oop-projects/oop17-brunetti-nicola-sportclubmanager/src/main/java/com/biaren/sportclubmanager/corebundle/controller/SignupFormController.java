package com.biaren.sportclubmanager.corebundle.controller;

import com.biaren.sportclubmanager.adminbundle.controller.SignupController;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.services.DataPersistance;
import com.biaren.sportclubmanager.corebundle.views.SignupPanel;
import com.biaren.sportclubmanager.utility.enums.JsonFileName;
import com.biaren.utility.BiarenFileWriter;
import com.biaren.utility.BiarenPathHandler;
import com.biaren.utility.BiarenUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Controller for {@link SignupPanel}.
 * Performs various operation to signup a User.
 * @author nbrunetti
 *
 */
public class SignupFormController {

    private SignupPanel view;
    
    /**
     * Create a {@link SignupFormController},
     * set the panel to attach some events to performs operations.
     * @param view to attach some events to performs operations.
     */
    public SignupFormController(final SignupPanel view) {
        this.view = view;
        this.attachEvents();
    }
    
    private void attachEvents() {
        this.view.getSubmitButton().setOnAction(e -> this.submitHandler());
    }
    
    private void submitHandler() {
        if (BaseModelImpl.isFirstOpening()) {
            this.createAdministrator();
            this.closePanel();
            new MainController(new Stage());
            DataPersistance.saveData(
                    BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.USERS.getNameForPath(), 
                    BaseModelImpl.getInstance().getUsersSet());
        } else if (SignupController.getInstance().checkAvailableUsername(this.view.getUsernameFieldValue())) {
            this.createUser();
            new MainController(new Stage());
            DataPersistance.saveData(
                    BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.USERS.getNameForPath(), 
                    BaseModelImpl.getInstance().getUsersSet());
        } else {
            BiarenUtil.showBasicAlert(AlertType.ERROR, "username no disponibile", "", "");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Username non disponibile.");
            alert.showAndWait();
        }

    }
    
    
    private void closePanel() {
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.close();
    }
    
    private void createAdministrator() {
        BaseModelImpl.getInstance().addNewUser(SignupController.createNewAdministrator(
                view.getUsernameFieldValue(),
                view.getPasswordFieldValue(), 
                view.getEmailFieldValue()));
        BiarenFileWriter.createFirstOpeningFile();
    }
    
    private void createUser() {
        BaseModelImpl.getInstance().addNewUser(SignupController.createNewUser(
                view.getUsernameFieldValue(),
                view.getPasswordFieldValue(), 
                view.getEmailFieldValue()));
    }
}
