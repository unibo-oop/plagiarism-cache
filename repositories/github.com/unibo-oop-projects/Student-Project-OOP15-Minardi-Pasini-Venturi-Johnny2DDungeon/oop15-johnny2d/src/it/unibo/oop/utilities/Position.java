package it.unibo.oop.utilities;
import it.unibo.oop.model.Entity;

/**
 * This class represents the position in the screen of every single
 * {@link Entity}.
 */
public class Position implements Point2 {

    private double x;
    private double y;

    /**
     * Constructor that creates a {@link Position} with X and Y.
     * 
     * @param x
     *            Integer value of the X
     * @param y
     *            Integer value of the Y
     */
    public Position(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor that creates a position with a precedent {@link Position}.
     * 
     * @param p the initial position
     */
    public Position(final Position p) {
        this(p.getX(), p.getY());
    }

    public double getX() {
        return this.x;
    }

    public int getIntX() {
        return (int) this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getIntY() {
        return (int) this.y;
    }

    public void setX(final double newX) {
        this.x = newX;
    }

    public void setY(final double newY) {
        this.y = newY;
    }

    /**
     * Sums to the current point.
     * @param movement the movement vector
     * @return Returns the new Position of the summed vector
     */
    public Position sumVector(final Vector2 movement) {
        if (movement.getX() == 0 && movement.getY() == 0) {
            return this;
        }
        return new Position((this.getX() + movement.getX()), this.getY() + movement.getY());
    }

    /**
     * Calculates the distance between 2 points.
     * 
     * @param a
     *            point 1
     * @param b
     *            point 2
     * @return the distance between 2 points
     */
    public static double pointsDistance(final Position a, final Position b) {
        final double xDistance = Math.abs(a.getX() - b.getX());
        final double yDistance = Math.abs(a.getY() - b.getY());
        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
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
        if (y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }
}