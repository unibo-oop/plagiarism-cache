package bzzbomber.controller.collision;

import java.util.Optional;
import java.util.Set;

import bzzbomber.model.entities.Door;
import bzzbomber.model.entities.HealthPoint;
import bzzbomber.model.entities.Insects;

/**
 * Models the specific hero's collision.
 * 
 */
public interface BomberCollision extends Collision {

    /**
     * Control the collision with the open door.
     * 
     * @param doorOpened
     *            the open door.
     * @return true if there's a collision, false otherwise.
     */
    boolean openDoorCollision(Optional<Door> doorOpened);

    /**
     * Control the collision with the insects.
     * 
     * @param insects
     *            The set of insects.
     * @return true if there's a collision, false otherwise.
     */
    boolean insectsCollision(Set<Insects> insects);

    /**
     * Control the collision with the heart.
     * 
     * @param health
     *            The set of healthpoint.
     * @return true if there's a collision, false otherwise.
     */
    boolean healthCollision(Set<HealthPoint> health);

}
