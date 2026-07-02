package model.ai.separation;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.zombie.Zombie;

/**
 * Strategy interface for computing separation forces between zombies.
 * <p>
 * This is typically used to implement behaviors where a zombie
 * adjusts its velocity to avoid crowding or colliding with other zombies.
 * </p>
 *
 * @param <Z> the type of Zombie
 */
public interface SeparationStrategy<Z extends Zombie> {

    /**
     * Computes the separation force vector for a given zombie based on
     * the positions of other nearby zombies.
     * <p>
     * The returned force vector typically points away from nearby zombies
     * to help maintain a comfortable distance and avoid clustering.
     * </p>
     *
     * @param zombie the zombie for which to compute the separation force
     * @param others the list of other zombies to consider for separation
     * @return a {@code Pair<Double, Double>} representing the force vector (x and y components)
     */
    Pair<Double, Double> computeForce(final Z zombie, final List<Z> others);

}
