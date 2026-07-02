package controller.minigames.colorgama;

import controller.minigames.MiniGameController;

/**
 * The controller interface of ColorGama minigame.
 *
 */
public interface ColorGamaController extends MiniGameController {

    /**
     * Start ColorGama minigame.
     */
    void startGame();

    /**
     * Check the response given by the user.
     * 
     * @param index
     *          the button's index 
     */
    void checkAnswer(int index);
}
