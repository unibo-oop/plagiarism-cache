package view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import view.ViewManagerImpl;
import view.utility.ViewUtils;

/**
 * 
 * Controller class for the OptionsMenuView file.
 *
 */
public class OptionsMenuViewController extends AbstractControllerFXML {

    @FXML private CheckBox godModeCheckBox;
    @FXML private CheckBox survivalModeCheckBox;
    @FXML private BorderPane contentPane;

    /**
     * Initialize method that loads previous configuration.
     */
    @FXML
    public void initialize() {
        godModeCheckBox.setSelected(ViewUtils.isGodModeSelected());
        survivalModeCheckBox.setSelected(ViewUtils.isSurvivalModeSelected());
    }

    /**
     * Event method to close the displayed view and save the changes.
     */
    @FXML
    public void applyButtonClick() {
        if (godModeCheckBox.isSelected() && survivalModeCheckBox.isSelected()) {
            final Alert alert = new Alert(AlertType.INFORMATION, "The Survival Mode does NOT support the God Mode. Survival Mode will be deactivated.", ButtonType.OK);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.showAndWait();
            ViewUtils.setGodMode(godModeCheckBox.isSelected());
            ViewUtils.setSurvivalMode(false);
        } else {
            ViewUtils.setGodMode(godModeCheckBox.isSelected());
            ViewUtils.setSurvivalMode(survivalModeCheckBox.isSelected());
        }
        ViewManagerImpl.get().pop();
    }

    /**
     * Event method to close the displayed view.
     */
    @FXML
    public void exitButtonClick() {
        godModeCheckBox.setSelected(ViewUtils.isGodModeSelected());
        survivalModeCheckBox.setSelected(ViewUtils.isSurvivalModeSelected());
        ViewManagerImpl.get().pop();
    }

    /**
     * Get root.
     */
    @Override
    public final Region getRoot() {
        return this.contentPane;
    }

    /**
     * Set text.
     */
    @Override
    public void setText() { }
}
