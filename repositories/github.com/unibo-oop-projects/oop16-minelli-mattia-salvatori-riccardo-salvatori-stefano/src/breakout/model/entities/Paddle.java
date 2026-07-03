package breakout.model.entities;

import java.util.List;

import breakout.model.physics.MyBoundingBox;
import breakout.model.physics.Vector2D;
import javafx.geometry.Point2D;

/**
 * A simple class to define the paddle used by the player.
 *
 */
public class Paddle extends AbstractGameObject {

    private final double movementSpeed;

    /**
     * Constructor for the paddle object. 
     * Mainly for test purposes.
     * 
     * @param x
     *            the x position
     * @param y
     *            the y position
     * @param speed
     *            the speed
     * @param width
     *            the width of the paddle
     * @param height
     *            the height of the ball
     * 
     */
    public Paddle(final double x, final double y, final int speed, final double width, final double height) {
        super(new Point2D(x, y), Vector2D.ZERO, new MyBoundingBox(x, y, width, height));
        this.movementSpeed = speed;
    } 

    /**
     * Constructor that create the game object paddle. The paddle is always
     * perpendicular to the x-axis
     * 
     * @param pos
     *            the paddle's position
     * @param speed
     *            the paddle's speed
     * @param width
     *            the paddle's width
     * @param height
     *            the paddle's height
     */
    public Paddle(final Point2D pos, final double speed, final double width, final double height) {
        super(pos, Vector2D.ZERO, new MyBoundingBox(pos.getX(), pos.getY(), width, height));
        this.movementSpeed = speed;
    }

    /**
     * Move the bar left along the x-axis.
     */
    public void moveLeft() {
        this.setVelocity(-this.movementSpeed, 0);
    }

    /**
     * Move the bar right along the x-axis.
     */
    public void moveRight() {
        this.setVelocity(this.movementSpeed, 0);
    }

    /**
     * Update the paddle. If the paddle collides with a wall ,it is
     * replaced in the last known position with no collisions with that wall.
     * Then paddle stops after each update.
     * 
     * @param time
     *            the elapsed time
     * @param wallList
     *            the list of walls
     */
    public void update(final double time, final List<Wall> wallList) {
        super.update(time);

        // check for collision with walls. If a collsion is detected the paddle
        // is restored to a position with no collisions
        for (final Wall w : wallList) {
            if (this.collidedWith(w)) {
                if (this.isMovingRight()) {
                    this.setPosition(w.getPosition().getX() - this.getWidth() + 1, this.getPosition().getY());
                } else {
                    this.setPosition(w.getPosition().getX() + w.getWidth() + 1, this.getPosition().getY());
                }
            }
        }

        // Stop the paddle
        this.setVelocity(Point2D.ZERO.getX(), Point2D.ZERO.getY());
    }

    /**
     * @return true of the paddle is moving right.<br/>
     *         false otherwise
     */
    private boolean isMovingRight() {
        return (this.getVelocity().getX() >= 0);
    }

}
