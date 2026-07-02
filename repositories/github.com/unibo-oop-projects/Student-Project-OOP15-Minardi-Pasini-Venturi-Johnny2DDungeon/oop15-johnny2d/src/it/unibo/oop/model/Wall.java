package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.*;

/**
 * Class that represents a block of Wall that is used to delimit the arena
 */
public class Wall extends AbstractEntity {

    /**
     * Constructor to create a {@link Wall} in the defined position
     * 
     * @param startingX
     *            Starting X position
     * @param startingY
     *            Starting Y position
     */
    public Wall(final double startingX, final double startingY) {
        super(startingX, startingY);
    }

    /**
     * Gets the {@link Wall} height
     * @return the height
     */
    protected int getEntityHeight() {
        return WALL.getHeight();
    }

    /**
     * Gets the {@link Wall} width
     * @return the width
     */
    protected int getEntityWidth() {
        return WALL.getWidth();
    }
}