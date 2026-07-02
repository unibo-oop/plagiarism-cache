package pvz.model.plants.api;

import pvz.model.entities.api.Entity;
import pvz.utilities.PlantType;

/**
 * Interface representing the base functionality of a plant in the game.
 * Extends the generic {@link Entity} interface.
 */
public interface Plant extends Entity {

    /**
     * Gets the current life (health points) of the plant.
     *
     * @return The remaining life of the plant.
     */
    int getLife();

    /**
     * Decreases the plant's life by the specified amount of damage.
     *
     * @param damage The damage to apply.
     */
    void decreaseLife(int damage);

    /**
     * Maps the plant instance to a specific {@link PlantType}.
     *
     * @return The plant type corresponding to the implementation.
     */
    PlantType mapToEntityType();
}
