package view.sceneloader;

import javafx.stage.Stage;
import utility.GameModes;
import view.utilities.Screens;

/**
 * 
 * Interface that provides the necessary methods to load game scenes.
 *
 */
public interface SceneLoader {

    /**
     * Loads a game scene.
     * 
     * @param stage
     *            the stage that must be loaded.
     * 
     * @param screen
     *            the required scene name.
     * 
     * @param gameMode
     *            the game mode selected by the user.
     */
    void loadScene(Stage stage, Screens screen, GameModes gameMode);
}
