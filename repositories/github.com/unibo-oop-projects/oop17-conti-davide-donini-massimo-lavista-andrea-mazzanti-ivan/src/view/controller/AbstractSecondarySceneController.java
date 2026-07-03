package view.controller;

import javafx.fxml.FXML;

/**
 * 
 * Factories the shared methods of the secondary menu scenes.
 *
 */
abstract class AbstractSecondarySceneController extends AbstractSceneController {

    /**
     * Opens the main menu scene.
     */
    @FXML
    protected void backtoMenu() {
        super.getSceneFactory().openMenuScene();
    }

}
