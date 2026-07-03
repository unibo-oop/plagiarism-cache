package breakout.model.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import breakout.model.BallBouncer;
import breakout.model.physics.BounceHandler;
import breakout.model.physics.GameObject;
import breakout.model.physics.MyBoundingBox;
import breakout.model.physics.Vector2D;
import javafx.geometry.Point2D;

/**
 * Defines the game object of the ball.
 */
public class Ball extends AbstractGameObject {

    /**
     * For better movement management.
     */
    private List<GameObject> lastCollidedWith = new ArrayList<>();

    private BounceHandler<Ball> bouncer = BallBouncer.CLASSIC_BOUNCE;

    /**
     * Constructor for the ball object. 
     * Mainly for test purposes.
     * 
     * @param x
     *            the x position
     * @param y
     *            the y position
     * @param speed
     *            the speed
     * @param angle
     *            the angle of the velocity vector
     * @param radius
     *            the radius of the ball
     * 
     */
    public Ball(final double x, final double y, final double speed, final double angle, final double radius) {
        super(new Point2D(x, y), Vector2D.valueOfPolar(speed, angle), new MyBoundingBox(x, y, radius * 2, radius * 2));
    }

    /**
     * Constructor for the ball object.
     * 
     * @param position
     *            the position where to create the ball
     * @param velocity
     *            the velocity vector of the ball
     * @param radius
     *            the balls's radius
     */
    public Ball(final Point2D position, final Vector2D velocity, final double radius) {
        super(position, velocity, new MyBoundingBox(position.getX(), position.getY(), radius * 2, radius * 2));
    }

    /**
     * The function to be called when the ball hits any object. Compute the
     * collision and update ball's position and velocity. If there's more then
     * one object in the input list, the bounce is computetd with the one that
     * has the greatest intersecting area with the ball
     * 
     * @param collided
     *            the list of the objects colliding with the ball.
     */
    public void bounce(final List<? extends GameObject> collided) {
        if (Collections.disjoint(this.lastCollidedWith, collided)) {
            // Choose the object to bounce on.(The one with the greatest intersecting area)
            final GameObject toBounceOn = collided.stream()
                                                  .max((c1, c2) -> Double.compare(
                                                          c1.getBounds().intersectingArea(this.getBounds()),
                                                          c2.getBounds().intersectingArea(this.getBounds())))
                                                  .get();
            this.bouncer.computeBounce(this, toBounceOn);
            this.lastCollidedWith = new ArrayList<>(collided);
        }
    }

    /**
     * Tells if the ball is on fire.
     * 
     * @return true if this ball's boune type is
     *         {@link BallBouncer.FIRE_BOUNCE}.<br/>
     *         false otherwise
     */
    public boolean isOnFire() {
        return this.bouncer.equals(BallBouncer.FIRE_BOUNCE);
    }

    /**
     * Set this ball with a particular bounce property defined in
     * {@link BallBouncer}.
     * 
     * @param bouncer
     *            the type of bounce to be set
     */
    public void setBounce(final BallBouncer bouncer) {
        this.bouncer = bouncer;
    }

    /**
     * Getter for the radius of the ball.
     * 
     * @return the radius of the ball
     */
    public double getRadius() {
        return this.getWidth() / 2;
    }

    @Override
    public String toString() {
        return super.toString() + (isOnFire() ? "IS ON FIRE" : "IS NORMAL");

    }

}