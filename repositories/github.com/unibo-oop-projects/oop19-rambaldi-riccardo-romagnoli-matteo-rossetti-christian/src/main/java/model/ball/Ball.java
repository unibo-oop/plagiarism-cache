package model.ball;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;

/**
 * Interface to model a ball.
 */
public interface Ball {

    /**
     * Update the status of the {@link Ball}.
     */
    void update();

    /**
     * Move the ball to a specific position.
     * @param position new position of the ball
     */
    void move(Pair<Double, Double> position);

    /**
     * 
     * @return the position of the ball
     */
    Pair<Double, Double> getPosition();

    /**
     * 
     * @return the radius of the ball
     */
    Double getRadius();

    /**
     * @return the {@link Body} of the ball
     */
    Body getPhysicalBody();

    /**
     * Set if the ball is loaded in the {@link BallLauncher}.
     * @param r
     */
    void setReadyToLaunch(boolean r);

    /**
     * @return true if the ball is ready to be launched by the {@link BallLauncher}, false otherwise
     */
    boolean isReadyToLaunch();

    /**
     * Launch the ball.
     * @param impulse the force to apply to the Ball 
     */
    void launch(Vector2 impulse);

    /**
     * @return true is the ball is outside the game field, false otherwise
     */
    boolean isOut();

    /**
     * @return true if the ball is stuck and the game cannot continue, false otherwise
     */
    boolean isStuck();
}
