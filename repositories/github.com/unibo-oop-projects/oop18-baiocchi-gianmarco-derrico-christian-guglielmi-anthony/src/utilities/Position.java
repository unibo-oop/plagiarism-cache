package utilities;

import graphics.SpriteSheet;

/**
 * Utility class that represents a position.
 */
public class Position {

    private double x;
    private double y;

    /**
     * Constructor for a new position.
     * @param x : coordinate x for position
     * @param y : coordinate y for position
     */
    public Position(final double x, final double y) {
        this.y = y;
        this.x = x;
    }

    /**
     * Constructor for a new position.
     * @param p : new position
     */
    public Position(final Position p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Getter for coordinate x.
     * @return coordinate x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Setter for coordinate x.
     * @param x : new x coordinate to set
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Getter for coordinate y.
     * @return coordinate y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Setter for coordinate y.
     * @param y : new y coordinate to set
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Check if two positions are equals.
     * @param p : position to compare
     * @return true if positions are equals, otherwise false
     */
    public boolean compareTo(final Position p) {
        return p.getX() == this.x && p.getY() == this.y;
    }

    /**
     * Set position correctly inside game tile. This function sets properly the
     * taken position from the input related to the minimum distance between two
     * game tile.
     *
     * @param position to be centered
     * @return a new centered position
     */
    public Position setInTile(final Position position) {
        final double dimSprite = SpriteSheet.SPRITE_SIZE_IN_GAME;
        final double centreSprite = dimSprite / 2;
        return new Position(Math.round((position.getX() + centreSprite) / dimSprite) * dimSprite,
                Math.round((position.getY() + centreSprite) / dimSprite) * dimSprite);
    }
}
