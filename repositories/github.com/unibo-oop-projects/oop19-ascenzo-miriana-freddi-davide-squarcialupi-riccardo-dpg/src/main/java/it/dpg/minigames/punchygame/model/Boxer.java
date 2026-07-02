package it.dpg.minigames.punchygame.model;

/**
 * Interface for the boxer in PunchyMinigame
 * @author Davide Picchiotti
 * */

public interface Boxer {

    /**
     * Set the boxer direction
     * @param direction the direction to set
     * @see Direction
     * */
    void setDirection(final Direction direction);

    /**
     * @return the boxer direction
     * @see Direction
     * */
    Direction getDirection();
}
