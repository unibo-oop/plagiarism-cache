package model.physics.physics_entities;

import model.entities.zombie.Zombie;
import utils.PairUtils;

/**
 * Basic implementation of the PhysicsZombieComponent interface.
 * Updates the position of a Zombie entity based on its current velocity and
 * elapsed time.
 */
public class PhysicsBaseZombie implements PhysicsZombieComponent {

    private static final double MILLISECONDS_TO_SECONDS = 0.001;

    /**
     * Updates the position of the given Zombie by integrating its velocity over the
     * elapsed time.
     *
     * @param zob the Zombie entity to update
     * @param dt  the elapsed time in milliseconds since the last update
     */
    @Override
    public void updateZombie(final Zombie zob, final int dt) {
        zob.setPosition(
                PairUtils.sum(
                        zob.getCurrentPos(),
                        PairUtils.mulScale(zob.getCurrentVel(), MILLISECONDS_TO_SECONDS * dt)));
    }
}
