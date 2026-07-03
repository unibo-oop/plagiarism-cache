package model.board;

import java.io.Serializable;

/**
 * This class represents a position in the form of X-Y coordinates.
 */
public class Position implements Serializable {

    private static final long serialVersionUID = -4037504735369247635L;
    private final int x;
    private final int y;

    /**
     * Creates an instance of Position.
     * 
     * @param x
     *            X coordinate
     * @param y
     *            Y coordinate
     */
    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the coordinate X.
     * 
     * @return the coordinate X
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the coordinate Y.
     * 
     * @return the coordinate Y
     */
    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

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
        return y == other.y;
    }

    @Override
    public String toString() {
        return "Position [x=" + x + ", y=" + y + "]";
    }
}