package view.controller;

import javafx.fxml.FXML;

/**
 * 
 * Implements the pause scene of the game.
 *
 */
public final class PauseSceneControllerImpl extends AbstractSecondarySceneController {

    @FXML
    private void resumeGame() {
        this.getSceneFactory().openGameScene();
    }

}
