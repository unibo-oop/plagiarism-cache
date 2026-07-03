package view.controller;

import java.util.Optional;

import javafx.scene.canvas.Canvas;
import model.data.GameData;

/**
 * 
 * Represents the game scene controller.
 *
 */
public interface GameSceneController {

    /**
     * 
     * @return the canvas graphic node.
     */
    Canvas getCanvas();

    /**
     * 
     * @param gameData
     *            game data to display into labels
     */
    void setGameData(GameData gameData);

    /**
     * 
     * @return the name written by player at the end of the game
     */
    Optional<String> askPlayerName();

}
