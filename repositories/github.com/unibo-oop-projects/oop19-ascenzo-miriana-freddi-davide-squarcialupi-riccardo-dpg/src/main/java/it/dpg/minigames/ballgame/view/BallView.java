package it.dpg.minigames.ballgame.view;

import it.dpg.maingame.view.View;
import it.dpg.minigames.ballgame.controller.BallMinigameLevel;

public interface BallView extends View {

    /**
     * display ready message
     */
    void setReady();

    /**
     * remove ready message
     */
    void removeReady();

    /**
     * set go message
     */
    void setGo();

    /**
     * remove go message
     */
    void removeGo();

    /**
     * shows the selected level
     *
     * @param level the selected level
     */
    void setupLevel(BallMinigameLevel level);

    /**
     * position the ball at the given coordinates
     *
     * @param xPos ball's horizontal position
     * @param yPos ball's vertical position
     */
    void positionBall(final double xPos, final double yPos);

    /**
     * sets the current score
     *
     * @param score player's current score
     */
    void setScore(int score);

    /**
     * show the game has been beaten
     */
    void setVictory();
}
