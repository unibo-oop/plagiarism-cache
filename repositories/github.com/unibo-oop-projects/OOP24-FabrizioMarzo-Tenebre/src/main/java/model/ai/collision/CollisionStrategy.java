package model.ai.collision;

import java.util.List;

import model.entities.zombie.Zombie;

/**
 * Strategy interface for resolving collisions between zombies.
 *
 * @param <Z> the type of Zombie
 */
public interface CollisionStrategy<Z extends Zombie> {

    /**
     * Resolves collisions between the given zombie and a list of other zombies.
     * <p>
     * This method should handle how the zombie reacts or adjusts its
     * position/velocity
     * when it collides with others.
     * </p>
     *
     * @param zombie the zombie for which collisions should be resolved
     * @param others the list of other zombies to check collisions against
     */
    void resolveCollisions(final Z zombie, final List<Z> others);
}
