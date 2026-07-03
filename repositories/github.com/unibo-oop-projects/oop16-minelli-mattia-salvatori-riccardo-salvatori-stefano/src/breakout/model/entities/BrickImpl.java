package breakout.model.entities;

import breakout.model.physics.MyBoundingBox;
import breakout.model.physics.Vector2D;
import javafx.geometry.Point2D;

/**
 * Implementation of the {@link Brick} interface.
 */
public class BrickImpl extends AbstractGameObject implements Brick {

    private int remainingLife;
    private final BrickType brickType;


    /**
     * Override of the hash code.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((brickType == null) ? 0 : brickType.hashCode());
        result = prime * result + this.getPosition().hashCode();
        return result;
    }

    /**
     * Override equals. Two bricks can be equal even if they have different
     * remaining life.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BrickImpl)) {
            return false;
        }
        final BrickImpl other = (BrickImpl) obj;
        return brickType.equals(other.brickType) && this.getPosition().equals(other.getPosition());
    }

    /**
     * Brick constructor.
     * Creates a brick.
     * 
     * @param brickType
     *            a brick type specifide in the enum {@link BrickType}
     * @param position
     *            a position specified by Cartesian coordinates (x , y)
     * @param brickWidth
     *            the brick's width
     * @param brickHeight
     *            the brick height
     */
    public BrickImpl(final BrickType brickType, final Point2D position, final double brickWidth,
            final double brickHeight) {
        // Velocity setted to ZERO.
        super(position, Vector2D.ZERO, new MyBoundingBox(position.getX(), position.getY(), brickWidth, brickHeight));
        this.brickType = brickType;
        this.remainingLife = this.brickType.getStartingLife();
    }

    /**
     * {@inheritDoc}.
     */
    public int getRemainingLife() {
        return this.remainingLife;
    }

    /**
     * {@inheritDoc}.
     */
    public void hit() {
        this.remainingLife = this.brickType.decrementLife().apply(remainingLife);
    }

    /**
     * {@inheritDoc}.
     */
    public boolean isDead() {
        return this.remainingLife <= 0;
    }

    /**
     * {@inheritDoc}.
     */
    public int getBrickValue() {
        return this.brickType.getValue();
    }

    /**
     * {@inheritDoc}.
     */
    public BrickType getType() {
        return this.brickType;
    }

    @Override
    public String toString() {
        return "Type: " + this.brickType + " Life: " + this.remainingLife;
    }
}
