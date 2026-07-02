package model.ai.collision;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.zombie.Zombie;
import utils.PairUtils;

/**
 * Default implementation of {@link CollisionStrategy} that resolves collisions
 * between zombies by adjusting their positions to eliminate overlap.
 *
 * @param <Z> the type of Zombie
 */
public class DefaultCollisionResolver<Z extends Zombie> implements CollisionStrategy<Z> {

    /**
     * The amount by which to separate overlapping zombies.
     */
    private static final double OVERLAP = 1.0;

    /**
     * Resolves collisions between the given zombie and other zombies by moving
     * them apart along the vector connecting their positions.
     * <p>
     * If two zombies overlap (their bounding boxes collide), this method moves them
     * away from each other equally by half the overlap amount.
     * </p>
     *
     * @param zombie the zombie to resolve collisions for
     * @param others the list of other zombies to check collisions against
     */
    @Override
    public void resolveCollisions(final Z zombie, final List<Z> others) {
        for (Z other : others) {
            if (other != zombie) {
                var bb1 = zombie.getBBox();
                var bb2 = other.getBBox();

                if (bb1.isColliding(bb2)) {
                    Pair<Double, Double> diff = PairUtils.diff(zombie.getCurrentPos(), other.getCurrentPos());

                    if (PairUtils.norm2(diff) == 0) {
                        diff = Pair.of(Math.random() - 0.5, Math.random() - 0.5);
                    }

                    Pair<Double, Double> dir = PairUtils.normalize(diff);
                    Pair<Double, Double> correction = PairUtils.mulScale(dir, OVERLAP / 2.0);

                    Pair<Double, Double> newPosA = PairUtils.sum(zombie.getCurrentPos(), correction);
                    Pair<Double, Double> newPosB = PairUtils.diff(other.getCurrentPos(), correction);

                    zombie.setPosition(newPosA);
                    other.setPosition(newPosB);
                }
            }
        }
    }

}
