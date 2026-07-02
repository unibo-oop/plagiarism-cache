package com.biaren.sportclubmanager.corebundle.controller;

import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.model.interfaces.BaseModel;
import com.biaren.sportclubmanager.corebundle.views.SetupPanel;
import com.biaren.utility.BiarenFileWriter;
import javafx.stage.Stage;

/**
 * Controls the basic setup of appliation in the first-use.
 * @author nbrunetti
 *
 */
public class SetupController {
    
    private SetupPanel view;
    private BaseModel baseModel;
    
    /**
     * Create a {@link SetupController} to performs setup operations.
     * @param view to attach events to performs setup operations
     */
    public SetupController(final SetupPanel view) {
        this.view = view;
        this.baseModel = BaseModelImpl.getInstance();
        this.attachEvents();
    }
    
    private void attachEvents() {
        this.view.getSubmitButton().setOnAction(e -> this.submitHandler());
    }
    
    private void submitHandler() {
        this.baseModel.setTeamName(this.view.getTeamNameFieldValue());
        this.baseModel.setBusinessName(this.view.getBusinessNameFieldValue());
        BiarenFileWriter.createSetupFile();
        new MainController((Stage) this.view.getScene().getWindow());
    }
}
