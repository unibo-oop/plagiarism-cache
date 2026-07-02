package it.unibo.pacman.model.utilities;

import java.util.Objects;
/**
 * A class which represents the positions of the various entities in this game.
 */
public class Position {
    private final int x;
    private final int y;
    /**
     * Keeps positions.
     * 
     * @param x value of x-axis
     * @param y value of y-axis
     */
    public Position(final int x, final int y) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
    }
    /**
     * 
     * @return x-axis value.
     */
    public final int getX() {
        return x;
    }
    /**
     * 
     * @return y-axis value.
     */
    public final int getY() {
        return y;
    }
    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
    @Override
    public final boolean equals(final Object obj) {
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
        /*if (y != other.y) {
            return false;
        }
        return true;*/
        return y == other.y;
    }
    @Override
    public final String toString() {
        return "Position [x=" + x + ", y=" + y + "]";
    }
}
