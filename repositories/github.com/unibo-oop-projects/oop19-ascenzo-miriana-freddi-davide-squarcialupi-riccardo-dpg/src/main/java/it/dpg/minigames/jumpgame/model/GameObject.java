package it.dpg.minigames.jumpgame.model;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Interface for generic game objects
 * @author Davide Picchiotti
 * */

public interface GameObject {

    /**
     * @return the object height
     * */
    int getHeight();

    /**
     * @return the object width
     * */
    int getWidth();

    /**
     * @return the object position
     * @see Pair
     * */
    Pair<Integer, Integer> getPosition();

    /**
     * Set the object position
     * @param position the position to set
     * @see Pair
     * */
    void setPosition(final Pair<Integer, Integer> position);

    /**
     * @return the object horizontal speed
     * */
    int getSpeedX();

    /**
     * @return the object horizontal speed
     * */
    int getSpeedY();

    /**
     * Set the object horizontal speed
     * @param speedX the speed to set
     * */
    void setSpeedX(final int speedX);

    /**
     * Set the object vertical speed
     * @param speedY the speed to set
     * */
    void setSpeedY(final int speedY);

    /**
     * Update character position based on the speed (call at each game frame)
     * */
    void updatePosition();
}
