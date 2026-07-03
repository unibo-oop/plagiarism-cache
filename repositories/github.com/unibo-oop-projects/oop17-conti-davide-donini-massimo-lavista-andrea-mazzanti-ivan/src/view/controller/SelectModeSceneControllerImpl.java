package view.controller;

import javafx.fxml.FXML;
import utility.GameModes;

/**
 * 
 * Implements the methods to launch the game in different modes.
 *
 */
public final class SelectModeSceneControllerImpl extends AbstractSecondarySceneController {

    @FXML
    private void storyMode() {
        this.getSceneFactory().setGameMode(GameModes.STORY_MODE);
        this.getSceneFactory().openGameScene();
    }

    @FXML
    private void survivalMode() {
        this.getSceneFactory().setGameMode(GameModes.SURVIVAL_MODE);
        this.getSceneFactory().openGameScene();
    }

}
