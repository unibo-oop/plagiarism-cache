package model.physics.physics_entities;

import model.entities.survivor.Survivor;

/**
 * Interface for components responsible for updating the physics state of a
 * Survivor entity.
 */
public interface PhysicsSurvivorComponent {

    /**
     * Updates the position of the given Survivor
     * based on the elapsed time since the last update.
     *
     * @param sur       The Survivor entity to update.
     * @param dt The elapsed time in milliseconds since the last
     *                        update.
     */
    public void updateSurvivor(final Survivor sur, final int dt);
}
