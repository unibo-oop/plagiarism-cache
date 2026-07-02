package aoc.utilities;

/**
 * This class manages a couple of Double as coordinates in the game world.
 * These coordinates could be used as position vector or as translation vector.
 */
public class Vector {

    /**
     * The pair containing the coordinates.
     */
    private final Pair<Double, Double> vector;

    /**
     * The constructor of the class.
     * 
     * @param x
     *          The x coordinate of the entity.
     * @param y
     *          The y coordinate of the entity.
     */
    public Vector(final double x, final double y) {
        this.vector = new Pair<>(x, y);
    }
	
    /**
     * Getter of the x coordinate. 
     * 
     * @return
     *     The x coordinate.
     */
    public Double getX() {
        return this.vector.getFirst();
    }

    /**
     * Getter of the y coordinate.
     * 
     * @return
     *    The y coordinate.
     */
    public Double getY() {
        return this.vector.getSecond();
    }
    
    /**
     * Increments the x value of the vector of the given amount.
     * 
     * @param amount
     *          How much the x coordinate is increased.
     */
    public Vector increaseX(final double amount) {
		return new Vector(this.vector.getFirst() + amount, this.vector.getSecond());
	}
	
    /**
     * Increments the y value of the vector of the given amount.
     * 
     * @param amount
     *          How much the y coordinate is increased.
     */
    public Vector increaseY(final double amount) {
        return new Vector(this.vector.getFirst(), this.vector.getSecond() + amount);
    }

    /**
     * Increments both X and Y of a Vector.
     * 
     * @param modifier
     *          the X and Y to add to the vector
     *          
     * @return the modified vector
     */
    public Vector increaseWithVector(final Vector modifier) {
        return new Vector(this.vector.getFirst() + modifier.getX(), this.vector.getSecond() + modifier.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.vector.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Vector) {
            return this.vector.equals(new Pair<> (((Vector) obj).getX(), ((Vector) obj).getY()));
            }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.vector.toString();
    }
}
