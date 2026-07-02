package it.unibo.oop18.cfc.Util;

import it.unibo.oop18.cfc.TileMap.Tile;

/**
 * Utility class that represents a position in game.
 */
public class Position {

    private double x;
    private double y;

    /**
     * Creates a new {@code Position}.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public Position(final double x, final double y) {
        this.y = y;
        this.x = x;
    }

    /**
     * Create a new {@code Position}.
     *
     * @param p is the new position
     */
    public Position(final Position p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Gets coordinate x.
     *
     * @return coordinate x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Sets coordinate x.
     *
     * @param x value to set
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Gets coordinate y.
     *
     * @return coordinate y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets coordinate y.
     *
     * @param y value to set
     */
    public void setY(final double y) {
        this.y = y;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Controls if a {@code Position} is equals to the center of a tile.
     *
     * @return true if position is the center of a tile, otherwise false
     */
    public boolean isCentered() {
        return Math.round(this.x) % (Tile.SPRITE_SIZE) == 0
               && Math.round(this.y) % (Tile.SPRITE_SIZE) == 0;
    }

    /**
     * Sets position correctly inside game tile. This function sets properly the
     * taken position from the input related to the minimum distance between two
     * game tile.
     *
     * @param position to be centered
     * @return a new centered position
     */
    public Position setInTile(final Position position) {
        final double dimSprite = Tile.SPRITE_SIZE;
        return new Position(Math.round(position.getX() / dimSprite) * dimSprite,
                Math.round(position.getY() / dimSprite) * dimSprite);
    }

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

}
