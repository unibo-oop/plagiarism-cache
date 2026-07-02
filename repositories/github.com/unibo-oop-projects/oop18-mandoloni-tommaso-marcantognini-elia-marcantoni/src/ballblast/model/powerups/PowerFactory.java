package ballblast.model.powerups;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.physics.CollisionManager;

/**
 * Factory to create {@link Power} following the Factory Method pattern.
 */
public interface PowerFactory {

    /**
     * Creates a new {@link Power}.
     * 
     * @param velocity         The initial velocity.
     * @param position         The initial position.
     * @param collisionManager The collision manager.
     * @return The new {@link Power}.
     */
    Power createPower(Vector2D velocity, Coordinate position, CollisionManager collisionManager);

}
