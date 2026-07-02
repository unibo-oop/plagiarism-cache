package frogger.model.interfaces;

import java.util.Set;

import frogger.common.Direction;
import frogger.common.Position;
import frogger.model.implementations.Car;
import frogger.model.implementations.Eagle;
import frogger.model.implementations.Trunk;

/**
 * Factory interface for creating different types of {@link EntitySpawner} objects.
 * This interface centralizes the creation of spawner instances for various game entities
 * allowing for a single point of construction, the goal is to avoid code repetition and improve 
 * maintainability.
 */
public interface SpawnerFactory {
    /**
     * return a spawner for Car entity.
     * @param laneIndex the index of the lane
     * @param speed the speed
     * @param direction the direction
     * @return the spawner object
     */
    EntitySpawner<Car> carSpawner(int laneIndex, float speed, Direction direction);

    /**
     * return a spawner for Trunk entity.
     * @param laneIndex the index of the lane
     * @param speed the speed
     * @param direction the direction
     * @return the spawner object
     */
    EntitySpawner<Trunk> trunkSpawner(int laneIndex, float speed, Direction direction);

    /**
     * return a spawner for Eagle entity.
     * @return the spawner object
     */
    EntitySpawner<Eagle> eagleSpawner();

    /**
     * return a spawner for PowerUp entity.
     * @param alreadyPresent the positions of pickable object already present in the level
     * @return the spawner object
     */
    EntitySpawner<PickableObject> powerUpSpawner(Set<Position> alreadyPresent);

    /**
     * return a spawner for Coin entity.
     * @param alreadyPresent the positions of pickable object already present in the level
     * @return the spawner object
     */
    EntitySpawner<PickableObject> coinSpawner(Set<Position> alreadyPresent);
}
