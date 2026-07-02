package view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import view.ViewManagerImpl;
import view.scene.MainMenuScene;
import view.utility.ViewUtils;

/**
 * Controller class for the WinView file.
 */
public class LostViewController extends AbstractControllerFXML {

    @FXML private BorderPane contentPane;
    @FXML private TextField scoreText;

    /**
     * Event method to acknowledge the lost and continue using the app.
     */
    @FXML
    public void okButtonClick() {
        while (!(ViewManagerImpl.get().getCurrentScene() instanceof MainMenuScene)) {
            ViewManagerImpl.get().pop();
        }
    }

    /**
     * Get root.
     */
    @Override
    public final Region getRoot() {
        return contentPane;
    }

    /**
     * Set text.
     */
    @Override
    public void setText() {
        scoreText.setText(Integer.toString(ViewUtils.getPoints()));
    }
}
