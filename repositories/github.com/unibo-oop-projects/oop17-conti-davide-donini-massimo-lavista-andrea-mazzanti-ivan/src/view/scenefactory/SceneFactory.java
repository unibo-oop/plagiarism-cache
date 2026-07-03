package view.scenefactory;

import javafx.stage.Stage;
import utility.GameModes;

/**
 * 
 * Represents a factory of the game scenes.
 *
 */
public interface SceneFactory {

    /**
     * Sets a new game stage.
     * 
     * @param stage
     *            the stage that must to be loaded.
     */
    void setStage(Stage stage);

    /**
     * Gets the current stage.
     * 
     * @return the current game stage.
     */
    Stage getStage();

    /**
     * Opens the main menu scene.
     */
    void openMenuScene();

    /**
     * Opens the main menu settings scene.
     */
    void openSettingsScene();

    /**
     * Opens the main menu achievements scene.
     */
    void openAchievementsScene();

    /**
     * Opens the main menu high scores scene.
     */
    void openHighScoresScene();

    /**
     * Opens the main menu manual scene.
     */
    void openManualScene();

    /**
     * Opens the pause scene.
     */
    void openPauseScene();

    /**
     * Opens the game scene.
     */
    void openGameScene();

    /**
     * Opens the game mode selection scene.
     */
    void openSelectModeScene();

    /**
     * Opens the game over scene.
     */
    void openGameOverScene();

    /**
     * Sets the game over scene.
     * 
     * @param gameMode
     *            the game mode that must be loaded.
     */
    void setGameMode(GameModes gameMode);

}
