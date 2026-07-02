package it.unibo.model.interfaces;

import java.util.Optional;

/**
 * Represents a Puyo in the game.
 * A Puyo has a color, position (x, y), and an optional death and freeze timer.
 */
public interface PuyoInterface {

    /**
     * Gets the color of the Puyo.
     *
     * @return The color as a {@link String}.
     */
    String getColor();

    /**
     * Sets the color of the Puyo.
     *
     * @param color The new color as a {@link String}.
     */
    void setColor(String color);

    /**
     * Gets the x-coordinate of the Puyo in the grid.
     *
     * @return The x-coordinate as an integer.
     */
    int getX();

    /**
     * Sets the x-coordinate of the Puyo in the grid.
     *
     * @param x The new x-coordinate as an integer.
     */
    void setX(int x);

    /**
     * Gets the y-coordinate of the Puyo in the grid.
     *
     * @return The y-coordinate as an integer.
     */
    int getY();

    /**
     * Sets the y-coordinate of the Puyo in the grid.
     *
     * @param y The new y-coordinate as an integer.
     */
    void setY(int y);

    /**
     * Moves the Puyo one step down in the grid.
     * This increases its y-coordinate by 1.
     */
    void moveDown();

    /**
     * Gets the unique identifier of the Puyo.
     *
     * @return The unique identifier as a long.
     */
    long getIdentifier();

    /**
     * Gets the optional death clock value.
     * The death clock represents the remaining time before the Puyo disappears.
     *
     * @return An {@link Optional} containing the death clock value, or empty if not set.
     */
    Optional<Integer> getDeathClock();

    /**
     * Sets the death clock for the Puyo.
     *
     * @param deathClock An {@link Optional} containing the new death clock value.
     */
    void setDeathClock(Optional<Integer> deathClock);

    /**
     * Gets the optional freeze clock value.
     * The freeze clock represents the time the Puyo remains frozen.
     *
     * @return An {@link Optional} containing the freeze clock value, or empty if not set.
     */
    Optional<Integer> getFreezeClock();

    /**
     * Sets the freeze clock for the Puyo.
     *
     * @param freezeClock An {@link Optional} containing the new freeze clock value.
     */
    void setFreezeClock(Optional<Integer> freezeClock);
}
