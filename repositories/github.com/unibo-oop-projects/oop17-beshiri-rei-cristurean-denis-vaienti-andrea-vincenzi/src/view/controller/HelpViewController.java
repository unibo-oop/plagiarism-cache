package view.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import view.ViewManagerImpl;
/**
 * 
 * Controller class for the HelpView file.
 *
 */
public class HelpViewController extends AbstractControllerFXML {

    @FXML private BorderPane contentPane;

    /**
     * Event method for the back button.
     */
    @FXML
    public void backButtonClick() {
        ViewManagerImpl.get().pop();
    }

    /**
     * Get root.
     */
    @Override
    public final Region getRoot() {
        return contentPane;
    }

    /**
     * Set Text.
     */
    @Override
    public void setText() { }
}
