package view.controller;

import model.data.GameData;

/**
 * Representation of the scene that appears after the end of the game.
 */
public interface GameOverSceneController {

    /**
     * 
     * @param gameData
     *            game data received
     */
    void setGameData(GameData gameData);

}
