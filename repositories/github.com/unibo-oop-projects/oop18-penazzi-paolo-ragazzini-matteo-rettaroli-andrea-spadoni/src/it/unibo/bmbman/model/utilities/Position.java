package it.unibo.bmbman.model.utilities;

import it.unibo.bmbman.model.TerrainFactoryImpl;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;

/**
 * Class to manage the position of entities.
 */
public class Position {
    private int x;
    private int y;
    private static final int TILEDIM = TerrainFactoryImpl.CELL_DIMENSION *  ScreenToolUtils.SCALE;
    private static final int HALFTILEDIM = TILEDIM / 2;

    /**
     * Create a new position in the specified coordinate.
     * @param x coordinate
     * @param y coordinate
     */
    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Create a new position in the specified coordinate.
     * @param pos a {@link Position}
     */
    public Position(final Position pos) {
        this.x = pos.getX();
        this.y = pos.getY();
    }
    /**
     * Method to get the x coordinate.
     * @return x coordinate
     */
    public int getX() {
        return x;
    }
    /**
     * Method to set the x coordinate of the position.
     * @param x new x coordinate
     */
    public void setX(final int x) {
        this.x = x;
    }
    /**
     * Method to get the y coordinate.
     * @return y coordinate
     */
    public int getY() {
        return y;
    }
    /**
     * Method to set the y coordinate of the position.
     * @param y new y coordinate
     */
    public void setY(final int y) {
        this.y = y;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Position [x=" + this.getX() + "y=" + this.getY() + "]";
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (x != other.x) {
            return false;
        }
        if (y != other.y) {
            return false;
        }
        return true;
    }
    /**
     * Method used to get the centered position of a cell.
     * @param position actual position of an entity
     * @return a new centered position
     */
    public static  Position getCenteredPosition(final Position position) {
        Position newPosition = new Position(((position.getX() + HALFTILEDIM) / TILEDIM) * TILEDIM, 
                ((position.getY() + HALFTILEDIM) / TILEDIM) * TILEDIM);
        return newPosition;
    }
}
