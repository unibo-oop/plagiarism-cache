package model.properties;

/**
 * Class that represent the entity's velocity in the game.
 */
public class VelocityImpl implements Velocity {

    private final double x;
    private final double y;

    /** 
     * Constructor that passes this entity's velocity parameters to the super-class.
     * @param x 
     *          This entity's velocity axis X
     * 
     * @param y 
     *          This entity's velocity axis Y
     */
    public VelocityImpl(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public final Velocity sum(final Velocity v) {
        return new VelocityImpl(x + v.getX(), y + v.getY());
    }

    @Override
    public final double module() {
        return (double) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    @Override
    public final Velocity mul(final double fact) {
        return new VelocityImpl(this.x * fact, this.y * fact);
    }

    @Override
    public final double getX() {
        return this.x;
    }

    @Override
    public final double getY() {
        return this.y;
    }

    /**
     * @return the description of the velocity
     */
    public String toString() {
        return "Velocity[x: " + this.x + ",y: " + this.y + "]";
    }

}
