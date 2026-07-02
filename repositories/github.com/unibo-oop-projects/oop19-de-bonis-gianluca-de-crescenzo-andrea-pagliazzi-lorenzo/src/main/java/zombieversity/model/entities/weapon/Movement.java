package zombieversity.model.entities.weapon;

import javafx.geometry.Point2D;

/**
 * Represents a simple movement of an attack.
 */
public interface Movement {

    /**
     * @return
     *          The position of the shape at the given moment.
     */
    Point2D getActualPosition();

    /**
     * @return
     *          The angle of the trajectory at the given moment.
     */
    double getAngle();

    /**
     * Used to update the inner logic of the movement.
     */
    void update();

    /**
     * @return
     *          The scalar velocity of the movement.
     */
    double getVelocity();

    /**
     * @return
     *          True if the movement has ended its trajectory, False otherwise.
     */
    boolean hasEnded();
}
