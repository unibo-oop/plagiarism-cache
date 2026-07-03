package view.controller;

import javafx.fxml.FXML;

/**
 * 
 * Implements the main menu of the game.
 *
 */
public class MenuSceneControllerImpl extends AbstractSceneController {

    @FXML
    private void openGameModeSelection() {
        this.getSceneFactory().openSelectModeScene();
    }

    @FXML
    private void openSettings() {
        this.getSceneFactory().openSettingsScene();
    }

    @FXML
    private void openAchievements() {
        this.getSceneFactory().openAchievementsScene();
    }

    @FXML
    private void openHighScores() {
        this.getSceneFactory().openHighScoresScene();
    }

    @FXML
    private void openManual() {
        this.getSceneFactory().openManualScene();
    }

    @FXML
    private void quitGame() {
        Runtime.getRuntime().exit(0);
    }

}
