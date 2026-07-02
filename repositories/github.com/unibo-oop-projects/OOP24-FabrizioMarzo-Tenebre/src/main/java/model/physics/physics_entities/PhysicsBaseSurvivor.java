package model.physics.physics_entities;

import model.entities.survivor.Survivor;
import utils.PairUtils;

/**
 * Basic implementation of {@link PhysicsSurvivorComponent} that updates the
 * Survivor's position
 * based on its current velocity and elapsed time.
 */
public class PhysicsBaseSurvivor implements PhysicsSurvivorComponent {

    private static final double MILLISECONDS_TO_SECONDS = 0.001;

    /**
     * Updates the Survivor's position by moving it according to its current
     * velocity
     * scaled by the elapsed time in milliseconds.
     *
     * @param sur the Survivor to update
     * @param dt  the elapsed time in milliseconds since the last update
     */
    @Override
    public void updateSurvivor(final Survivor sur, final int dt) {
        sur.setPosition(
                PairUtils.sum(
                        sur.getCurrentPos(),
                        PairUtils.mulScale(sur.getCurrentVel(), MILLISECONDS_TO_SECONDS * dt)));
    }
}
