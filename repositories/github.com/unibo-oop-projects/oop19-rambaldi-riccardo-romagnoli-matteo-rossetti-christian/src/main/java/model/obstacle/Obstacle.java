package model.obstacle;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.dynamics.Body;

public interface Obstacle {

    /**
     * @return the {@link ObstacleBehavior} of this {@link Obstacle} based on its color. The {@link ObstacleBehavior} is an enumeration.
     */
    ObstacleBehavior getBehavior();

    /**
     * @return the shape of the {@link Obstacle}.
     */
    ObstacleType getType();

    /**
     * @return a {@link Pair} of the position's {@link Obstacle}.
     */
    Pair<Double, Double> getPosition();

    /**
     * Set the field hit to true if the {@link Obstacle} was hit. This field is set false by default.
     * @return this hit field.
     */
    boolean hit();

    /**
     * @return the measure of the {@link Obstacle} (radius or width and height).
     */
    List<Double> getMeasures();

    /**
     * @return the {@link Obstacle}'s body.
     */
    Body getPhysicalBody();

    /**
     * Set the behavior of the {@link Obstacle}.
     * @param color of the {@link Obstacle}.
     */
    void setObstacleBehavior(ObstacleBehavior color);
}
