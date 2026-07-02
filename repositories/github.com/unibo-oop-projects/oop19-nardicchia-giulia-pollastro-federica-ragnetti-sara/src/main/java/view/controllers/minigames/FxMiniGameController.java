package view.controllers.minigames;

import model.DifficultyLevel;
import view.controllers.FxController;

/** 
 * This interface represents the common parts of the minigame view.
 *
 */
public interface FxMiniGameController extends FxController {

    /**
     * Show the seconds of the timer on the view.
     * 
     * @param second 
     *              the seconds of the timer
     */
    void showTimerSeconds(int second);

    /**
     * Show the end game scene.
     * 
     * @param finalScore
     *              the score of the minigame
     */
    void showEndGame(int finalScore);

    /**
     * Set the initial seconds.
     * 
     * @param initialSeconds
     *          initialSeconds of the timer
     */
    void setInitialSeconds(int initialSeconds);

    /**
     * Stop timer.
     */
    void stopTimer();

    /**
     *  Initialize the current minigame.
     *
     * @param difficulty
     *          the {@link DifficultyLevel} selected
     */
    void initGame(DifficultyLevel difficulty);
}
