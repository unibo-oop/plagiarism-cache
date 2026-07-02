package model.hitbox;

/**
 * Abstract HitBox to avoid the initialization of HitBox. Only the subclass can
 * be initialized.
 */
public abstract class AbstractHitBox implements HitBox {

    private double x;
    private double y;

    /**
     * Constructor for this class.
     * @param x
     *            Initial X value.
     * @param y
     *            Initial Y value.
     */
    public AbstractHitBox(final double x, final double y) {
        changePosition(x, y);
    }

    /**
     * Method used to change position of entity.
     */
    @Override
    public void changePosition(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x position.
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * Getter for Y position.
     */
    @Override
    public double getY() {
        return y;
    }
}
