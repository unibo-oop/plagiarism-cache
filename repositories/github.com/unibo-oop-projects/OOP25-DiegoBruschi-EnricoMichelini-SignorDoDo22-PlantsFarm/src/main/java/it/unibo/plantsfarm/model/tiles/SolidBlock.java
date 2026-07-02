package it.unibo.plantsfarm.model.tiles;

import java.awt.Rectangle;

/**
 * Represents a solid block in the game, which is an area that cannot be traversed by the player.
 */
public final class SolidBlock {
    private final Rectangle blockCoordinates;

    /**
     * Creates a new SolidBlock with the specified coordinates.
     * 
     * @param blockCoordinates The coordinates of the solid block as a Rectangle.
     */
    public SolidBlock(final Rectangle blockCoordinates) {
        this.blockCoordinates = new Rectangle(blockCoordinates);
    }

    /**
     * Gets the coordinates of the solid block.
     * 
     * @return The coordinates of the solid block as a Rectangle.
     */
    public Rectangle getCoordinate() {
        return new Rectangle(this.blockCoordinates);
    }
}
