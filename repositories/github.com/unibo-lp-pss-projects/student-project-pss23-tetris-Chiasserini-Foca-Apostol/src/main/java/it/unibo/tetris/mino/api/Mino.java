package it.unibo.tetris.mino.api;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Interface for a Mino in the Tetris game.
 */
public interface Mino {
    /**
     * Creates a new {@link Mino} with the given {@link Color}.
     *
     * @param c The {@link Color} of the Mino.
     */
    public void create(Color c);

    /**
     * Sets the x and y coordinates of the {@link Mino}.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public void setXY(int x, int y);

    /**
     * Updates the x and y coordinates of the {@link Mino} 
     * based on the given direction.
     *
     * @param direction The direction to update the coordinates.
     */
    public void updateXY(int direction);

    /**
     * Sets the next direction for the {@link Mino}.
     *
     * @param direction The next direction.
     */
    public void getNextDirection(int direction);

    /**
     * Checks for collisions when moving the {@link Mino}.
     */
    public void checkMovementCollision();

    /**
     * Checks for collisions when rotating the {@link Mino}.
     */
    public void checkRotationCollision();

    /**
     * Checks for collisions when the {@link Mino} becomes static.
     */
    public void checkStaticBlockCollision();

    /**
     * Updates the {@link Mino}'s state based on its current state and game rules.
     */
    public void update();

    /**
     * Draws the {@link Mino} on the given {@link Graphics2D} object.
     *
     * @param g2 The {@link Graphics2D} object to draw on.
     */
    public void draw(Graphics2D g2);
}
