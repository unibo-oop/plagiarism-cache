package model.physics.physics_entities;

import model.entities.zombie.Zombie;

/**
 * Interface defining the physics behavior for updating a Zombie entity.
 */
public interface PhysicsZombieComponent {

    /**
     * Updates the state of the given Zombie based on the elapsed time.
     *
     * @param zob the Zombie entity to update
     * @param dt  the elapsed time in milliseconds since the last update
     */
    public void updateZombie(final Zombie zob, final int dt);
}
