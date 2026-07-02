package it.unibo.model;

import it.unibo.model.interfaces.PuyoInterface;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a single Puyo in the game.
 * A Puyo has a unique identifier, a color, and a position (x, y) in the grid.
 * It may also have optional timers for its "death" and "freeze" states.
 */
public class Puyo implements PuyoInterface {
    private long identifier;
    private String color;
    private int x;
    private int y;

    private Optional<Integer> deathClock;
    private Optional<Integer> freezeClock;

    /**
     * Constructs a new Puyo with the specified color and position.
     * Each Puyo is assigned a unique identifier upon creation.
     *
     * @param color The color of the Puyo.
     * @param x     The initial x-coordinate of the Puyo in the grid.
     * @param y     The initial y-coordinate of the Puyo in the grid.
     */
    public Puyo(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.identifier = Math.abs(ThreadLocalRandom.current().nextLong());

        this.deathClock = Optional.empty();
        this.freezeClock = Optional.empty();
    }

    /**
     * Returns the optional death clock value.
     * The death clock represents the remaining time before the Puyo disappears.
     *
     * @return An {@link Optional} containing the death clock value, or empty if not set.
     */
    @Override
    public Optional<Integer> getDeathClock() {
        return deathClock;
    }

    /**
     * Sets the death clock for the Puyo.
     *
     * @param deathClock An {@link Optional} containing the new death clock value.
     */
    @Override
    public void setDeathClock(Optional<Integer> deathClock) {
        this.deathClock = deathClock;
    }

    /**
     * Returns the optional freeze clock value.
     * The freeze clock represents the time the Puyo remains frozen.
     *
     * @return An {@link Optional} containing the freeze clock value, or empty if not set.
     */
    @Override
    public Optional<Integer> getFreezeClock() {
        return freezeClock;
    }

    /**
     * Sets the freeze clock for the Puyo.
     *
     * @param freezeClock An {@link Optional} containing the new freeze clock value.
     */
    @Override
    public void setFreezeClock(Optional<Integer> freezeClock) {
        this.freezeClock = freezeClock;
    }

    /**
     * Returns the unique identifier of the Puyo.
     *
     * @return The unique identifier.
     */
    @Override
    public long getIdentifier() {
        return identifier;
    }

    /**
     * Returns the color of the Puyo.
     *
     * @return The color as a string.
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of the Puyo.
     *
     * @param color The new color as a string.
     */
    @Override
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Returns the current x-coordinate of the Puyo.
     *
     * @return The x-coordinate.
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the Puyo.
     *
     * @param x The new x-coordinate.
     */
    @Override
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the current y-coordinate of the Puyo.
     *
     * @return The y-coordinate.
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the Puyo.
     *
     * @param y The new y-coordinate.
     */
    @Override
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Moves the Puyo one step down in the grid.
     * This method increases the y-coordinate by 1.
     */
    @Override
    public void moveDown() {
        y += 1;
    }
}
