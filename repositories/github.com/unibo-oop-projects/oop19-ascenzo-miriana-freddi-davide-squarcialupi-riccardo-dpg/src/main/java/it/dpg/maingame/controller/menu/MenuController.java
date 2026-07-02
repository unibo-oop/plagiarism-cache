package it.dpg.maingame.controller.menu;

import it.dpg.maingame.model.character.Difficulty;

import java.util.Map;

/**
 * Interface for MenuControllerImpl
 * @author Riccardo Squarcialupi
 */
public interface MenuController {

    /**
     * Save player's option from Map
     * @param numPlayer number of PLayer that are in game
     */
    void setOptionsPlayer(Integer numPlayer);

    /**
     * Save AI's option from Map
     * @param numAI number of AI that are in game
     */
    void setOptionsAI(Integer numAI);

    /**
     * return the Map of Player options
     */
    Map<Integer, String> getOptionsPlayer();

    /**
     * return the Map of AI options
     */
    Map<String, Difficulty> getOptionsAI();

    /**
     *
     * @param whichAI AI to set Difficulty
     * @param dif which Difficulty to set
     */
    void setAIDifficulty(Integer whichAI, Difficulty dif);

    /**
     * initialize the game with input parameters from option
     */
    void startGame();

}
