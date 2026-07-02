package javagotchi.view.minigame;

import javagotchi.view.minigame.mainview.MiniGameView;
import javagotchi.view.minigame.menu.MenuMiniGame;

/**
 * 
 * @author marica
 *
 */
public interface MiniView {

    /**
     * Gets main menu.
     * 
     * @return {@link MenuMiniGame}
     */
    MenuMiniGame getMainMenu();

    /**
     * Gets game view.
     * 
     * @return {@link MiniGameView}
     */
    MiniGameView getViewMiniGame();

    /**
     * Sets game view.
     * 
     * @param viewMiniGame
     *            new game view
     */
    void setViewMiniGame(MiniGameView viewMiniGame);

    /**
     * Update the time progress with the method {@link MiniGameView#setTime(int)}.
     * 
     * @param sec
     *            new seconds
     */
    void updateTimeGui(int sec);

    /**
     * Update the score text  with the method {@link MiniGameView#setScore(String)}.
     * 
     * @param textScore
     *            new score text
     */
    void updateScoreGui(String textScore);

}
