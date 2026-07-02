package controller.minigames.perilouspath;

import controller.minigames.MiniGameController;

/**
 * Represents the Perilous Path Controller.
 */
public interface PerilousPathController extends MiniGameController {

    /**
     * Initialize the game.
     */
    void startGame();

    /**
     * Check if the user can hit this position.
     * 
     * @param row
     *          the step's position row
     * @param col
     *          the step's position col
     */
    void hitAttempt(int row, int col);

    /**
     * Update the view.
     */
    void updateView();
}
