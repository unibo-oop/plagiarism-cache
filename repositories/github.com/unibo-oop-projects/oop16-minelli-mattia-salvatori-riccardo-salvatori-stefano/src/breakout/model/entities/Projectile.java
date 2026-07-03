package breakout.model.entities;

import breakout.model.physics.MyBoundingBox;
import breakout.model.physics.Vector2D;
import javafx.geometry.Point2D;

/**
 * Defines a projectile fired by the paddle.
 */
public final class Projectile extends AbstractGameObject {

    /**
     * Constructor to create a projectile.
     * 
     * @param pos
     *            the position
     * @param vel
     *            the velocity
     * @param width
     *            the width 
     * @param height
     *            the height 
     */
    public Projectile(final Point2D pos, final Vector2D vel, final double width, final double height) {
        super(pos, vel, new MyBoundingBox(pos.getX(), pos.getY(), width, height));
    }

    /**
     * @return true if the projectile is out of the game scene.<br/>
     *         false otherwise
     */
    public boolean isOutOfBounds() {
        return this.getPosition().getX() < 0 || this.getPosition().getY() < 0;
    }

}
