package com.biaren.sportclubmanager.corebundle.views;

import java.io.File;

import com.biaren.sportclubmanager.utility.enums.TextStrings;
import com.biaren.utility.BiarenFileHandler;
import com.biaren.utility.BiarenPathHandler;
import com.biaren.utility.viewparts.VerticalLabelTextFieldBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * Represent a pane for a initial setup on first-use.
 * Placed here some base operation to setup system on user inputs.
 * @author nbrunetti
 *
 */
public class SetupPanel extends VBox {

    private Label title;
    private VerticalLabelTextFieldBox teamName;
    private VerticalLabelTextFieldBox businessName;
    private Button submit;
    private FileChooser teamLogo;
    private Button selectLogo;
    
    /**
     * Cretes a {@link SetupPanel}, instantiate fields
     * and set layout.
     */
    public SetupPanel() {
        this.title = new Label("Ancora qualche informazione...");
        this.teamName = new VerticalLabelTextFieldBox(TextStrings.TEAM_NAME.toString());
        this.businessName = new VerticalLabelTextFieldBox(TextStrings.BUSINESS_NAME.toString());
        this.submit = new Button(TextStrings.SUBMIT_LABEL.toString());
        this.teamLogo = new FileChooser();
        this.selectLogo = new Button("Aggiungi logo");
        this.setLayout();
        this.attachEvents();
    }
    
    private void setLayout() {
        this.getChildren().addAll(this.title, this.teamName, this.businessName, this.selectLogo, this.submit);
    }
    
    private void attachEvents() {
        this.selectLogo.setOnAction(e -> {
            File f = this.teamLogo.showOpenDialog(this.getScene().getWindow());
            if (f.exists()) {
                BiarenFileHandler.copyFileToResourceDir(f, BiarenPathHandler.getLogoResourcesPathString() 
                        + "\\society", TextStrings.SOCIETY_LOGO_FILE_NAME.toString());
            }
        });
    }
    
    /**
     * Get name field box
     * @return {@link VerticalLabelTextFieldBox} of name field
     */
    public VerticalLabelTextFieldBox getTeamNameField() {
        return this.teamName;
    }
    
    /**
     * Get business name  box
     * @return {@link VerticalLabelTextFieldBox} of business name field
     */
    public VerticalLabelTextFieldBox getBusinessTeamName() {
        return this.businessName;
    }
    
    /**
     * Get team name inserted by user
     * @return team name
     */
    public String getTeamNameFieldValue() {
        return this.teamName.getFieldValue();
    }
    
    /**
     * Get team business name inserted by user
     * @return team business name
     */
    public String getBusinessNameFieldValue() {
        return this.businessName.getFieldValue();
    }
    
    /**
     * Get submit button reference
     * @return {@link Button} reference
     */
    public Button getSubmitButton() {
        return this.submit;
    }
    
    /**
     * Get select logo button reference
     * @return {@link Button} reference
     */
    public Button getSelectLogoButton() {
        return this.selectLogo;
    }
}
