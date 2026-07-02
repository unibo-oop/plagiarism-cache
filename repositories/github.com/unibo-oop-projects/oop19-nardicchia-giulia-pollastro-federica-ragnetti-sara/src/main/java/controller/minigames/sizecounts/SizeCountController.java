package controller.minigames.sizecounts;

import controller.minigames.MiniGameController;

/**
 * 
 * A interface controller for the game size count.
 *
 */
public interface SizeCountController extends MiniGameController {
    /**
     * Informs the model of the new attempt of the user.
     * 
     * @param answer 
     *            the submitted answer
     */
    void newAttempt(String answer);
}
