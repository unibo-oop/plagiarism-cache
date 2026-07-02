package model.players;

import java.io.Serializable;

import utilities.Colors;

/**
 * Interface used to implements the users of the game.
 * Andrea Serafini.
 *
 */
public interface User extends Serializable {

    /**
     * Change the color of the player.
     * @param color
     *            the new color of the user
     */
    void changeColor(Colors color);

    /**
     * Change the name of the player.
     * @param name
     *            the new name of the user
     */
    void changeName(String name);

    /**
     *
     * @return the color of the user
     */
    Colors getColor();

    /**
     *
     * @return the name of the user
     */
    String getName();

    /**
     *
     * @return the number of turn played by the user
     */
    int getTurns();

    /**
     * Increment the number of turn played.
     */
    void incrementTurn();

    /**
     * Reset to zero the number of turn played.
     */
    void resetTurn();
}
