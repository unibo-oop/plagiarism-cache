package model.level.types;

import java.util.List;

import model.armory.munition.Munition;
import model.bounding_box.BoundingBox;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;

/**
 * Interface representing an abstract level in the domain model.
 * <p>
 * A level contains and manages domain entities such as the survivor, zombies,
 * and projectiles. It provides methods for updating its internal state over
 * time
 * and querying structural or logical properties (e.g., dimensions, completion
 * state).
 * </p>
 */
public interface Level {

    /**
     * Updates the internal state of the level based on the time elapsed.
     * <p>
     * This includes advancing positions, handling interactions, and updating entity
     * states.
     * </p>
     *
     * @param dt the time delta in milliseconds
     */
    void updateLevelState(final int dt);

    /**
     * Returns the width of the level in model units.
     *
     * @return the width
     */
    double getLevelWidth();

    /**
     * Returns the height of the level in model units.
     *
     * @return the height
     */
    double getLevelHeight();

    /**
     * Returns the bounding box representing the spatial boundaries of the level.
     *
     * @return the {@link BoundingBox} for the level
     */
    BoundingBox getLevelBBox();

    /**
     * Returns the survivor entity currently associated with the level.
     *
     * @return the {@link Survivor} instance
     */
    Survivor getSurvivorOnLevel();

    /**
     * Assigns a survivor to this level.
     *
     * @param sur the {@link Survivor} to set
     */
    void setSurvivorOnLevel(final Survivor sur);

    /**
     * Returns the list of zombies currently present within the level.
     *
     * @return a list of {@link Zombie} entities
     */
    List<Zombie> getZombieOnLevel();

    /**
     * Returns the list of active projectiles (munitions) in the level.
     *
     * @return a list of {@link Munition} instances
     */
    List<Munition> getProjectilesOnLevel();

    /**
     * Returns whether this level has been marked as completed.
     *
     * @return {@code true} if completed, {@code false} otherwise
     */
    boolean isLevelCompleted();

    /**
     * Sets the completion state of the level.
     *
     * @param completed {@code true} to mark the level as completed; {@code false}
     *                  otherwise
     */
    void setLevelCompleted(final boolean completed);

}
