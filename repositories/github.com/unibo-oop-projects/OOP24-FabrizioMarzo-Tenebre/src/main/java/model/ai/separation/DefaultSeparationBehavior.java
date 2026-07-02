package model.ai.separation;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.zombie.Zombie;
import utils.PairUtils;

/**
 * Default implementation of the SeparationStrategy for zombies.
 * <p>
 * This behavior computes a force that pushes the zombie away from
 * nearby zombies to avoid clustering and collisions.
 * </p>
 *
 * @param <Z> the type of Zombie
 */
public class DefaultSeparationBehavior<Z extends Zombie> implements SeparationStrategy<Z> {

    private static final double MAX_FORCE = 0.5;
    private static final int VALUE = 2; // // Minimum distance multiplier

    /**
     * Computes the separation force for the given zombie by considering the
     * positions
     * of other zombies nearby.
     * <p>
     * The force vector is calculated by summing normalized vectors pointing away
     * from zombies within a minimum separation distance.
     * The resulting force is averaged and capped by MAX_FORCE.
     * </p>
     *
     * @param zombie the zombie for which to compute the separation force
     * @param others the list of other zombies to consider
     * @return a {@code Pair<Double, Double>} representing the separation force vector
     */
    @Override
    public Pair<Double, Double> computeForce(Z zombie, List<Z> others) {
        Pair<Double, Double> separationForce = Pair.of(0.0, 0.0);
        int countNPCnear = 0;
        double minSeparationDist = zombie.getWidth() * VALUE;

        for (Z other : others) {
            if (other != zombie) {
                Pair<Double, Double> diff = PairUtils.diff(zombie.getCurrentPos(), other.getCurrentPos());
                double dist = PairUtils.norm2(diff);

                if (dist < minSeparationDist && dist > 0) {
                    Pair<Double, Double> normalizedDiff = PairUtils.normalize(diff);
                    Pair<Double, Double> force = PairUtils.mulScale(normalizedDiff, MAX_FORCE);
                    separationForce = PairUtils.sum(separationForce, force);
                    countNPCnear++;
                }
            }
        }

        if (countNPCnear > 0) {
            separationForce = PairUtils.mulScale(separationForce, 1.0 / countNPCnear);
        }
        return separationForce;
    }

}
