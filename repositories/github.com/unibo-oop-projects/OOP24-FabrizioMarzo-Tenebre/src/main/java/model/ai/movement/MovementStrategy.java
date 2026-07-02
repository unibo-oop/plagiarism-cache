package model.ai.movement;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;

/**
 * Interface defining a strategy for computing the velocity of a Zombie
 * based on the current state of the Zombie and its target Survivor.
 *
 * @param <Z> the type of Zombie
 * @param <S> the type of Survivor
 */
public interface MovementStrategy<Z extends Zombie, S extends Survivor> {

    /**
     * Computes the velocity vector for the given zombie to move towards (or with
     * respect to) the target survivor.
     *
     * @param zombie the Zombie whose velocity is being computed
     * @param target the Survivor target that the Zombie is interacting with
     * @return a {@code Pair<Double, Double>} representing the velocity vector (x and y components)
     */
    Pair<Double, Double> computeVelocity(final Z zombie, final S target);

}
