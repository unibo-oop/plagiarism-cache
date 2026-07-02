package it.dpg.minigames.jumpgame.model;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Interface representing game world (model) for JumpMinigame
 * @author Davide Picchiotti
 * @see it.dpg.minigames.jumpgame.JumpMinigame
 * */

public interface World {

    /**
     * @return get the world width
     * */
    int getWidth();

    /**
     * @return get the world height
     * */
    int getHeight();

    /**
     * Update the world
     * */
    void update();

    /**
     * @return if the game is over
     * */
    boolean isGameOver();

    /**
     * @return the player position
     * @see Pair
     * */
    Pair<Integer, Integer> getPlayerPosition();

    /**
     * @return the player size
     * */
    int getPlayerSize();

    /**
     * @return the list of platforms
     * @see Platform
     * */
    List<Platform> getPlatforms();

    /**
     * Set the player horizontal speed
     * @param speedX the speed to set in pixel/frame
     * */
    void setPlayerSpeedX(final int speedX);

    /**
     * @return the game actual score
     * */
    int getScore();
}
