package it.dpg.minigames.jumpgame.view;

import it.dpg.maingame.view.View;
import it.dpg.minigames.jumpgame.controller.input.InputObserver;

/**
 * Interface to control the view of the minigame JumpMinigame
 * @author Davide Picchiotti
 * */

public interface JumpMinigameView extends View {
    /**
     * Set the size of the game window
     * @param width the width of the window
     * @param height the height of the window
     * */
    void setGameSize(final int width, final int height);

    /**
     * Create the player on the view
     * @param x horizontal position
     * @param y vertical position
     * @param size player size
     * */
    void createPlayer(final int x, final int y, final int size);

    /**
     * Update the player position
     * @param x horizontal position
     * @param y vertical position
     * */
    void updatePlayer(final int x, final int y);

    /**
     * Update platform and state
     * @param x horizontal position
     * @param y vertical position
     * @param width platform width
     * @param height platform height
     * @param id platform id
     * @param exist if the platform still exist in the world
     * */
    void updatePlatform(final int x, final int y, int width, int height, final int id, final boolean exist);

    /**
     * Update the score on the view
     * @param score the score to set
     * */
    void updateScore(final int score);

    /**
     * Set the input observer
     * @param observer the object used to notify user input
     * */
    void setInputObserver(final InputObserver observer);
}
