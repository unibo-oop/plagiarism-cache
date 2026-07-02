package model.ai.behavior;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.ai.collision.CollisionStrategy;
import model.ai.movement.MovementStrategy;
import model.ai.separation.SeparationStrategy;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import utils.PairUtils;

/**
 * Implementation of AINPCBehavior for zombie NPCs, combining movement towards a
 * target,
 * separation from other zombies, and collision resolution.
 *
 * @param <S> the Survivor type (target)
 * @param <Z> the Zombie type (NPC)
 */
public class AIZombieBehavior<S extends Survivor, Z extends Zombie> implements AINPCBehavior<S, Z> {

    private static final double SEPARATION_WEIGHT = 2.0;

    private final MovementStrategy<Z, S> movStrategy;
    private final SeparationStrategy<Z> sepStrategy;
    private final CollisionStrategy<Z> colStrategy;

    /**
     * Constructs a new AIZombieBehavior with specified movement, separation, and
     * collision strategies.
     *
     * @param movStrategy the movement strategy to compute velocity towards the
     *                    target
     * @param sepStrategy the separation strategy to avoid crowding with other
     *                    zombies
     * @param colStrategy the collision strategy to resolve physical overlaps with
     *                    other zombies
     */
    public AIZombieBehavior(MovementStrategy<Z, S> movStrategy, SeparationStrategy<Z> sepStrategy,
            CollisionStrategy<Z> colStrategy) {
        this.movStrategy = movStrategy;
        this.sepStrategy = sepStrategy;
        this.colStrategy = colStrategy;
    }

    /**
     * Updates the AI-controlled zombie NPC by computing its velocity as a
     * combination of
     * movement towards the survivor target, separation from other zombies, and
     * resolving collisions.
     *
     * @param target the survivor target the zombie is reacting to
     * @param npc    the zombie NPC to update
     * @param npcs   the list of all zombies for coordination and collision handling
     */
    @Override
    public void updateAINPC(final S target, final Z npc, final List<Z> npcs) {
        for (Z z : npcs) {
            z.setVelocity(this.computeTotalEffect(z, target, npcs));
            colStrategy.resolveCollisions(npc, npcs);
        }
    }

    /**
     * Combines movement velocity towards the target with separation force and
     * clamps the result
     * to the maximum allowed speed.
     *
     * @param npc    the zombie NPC whose velocity is computed
     * @param target the survivor target
     * @param npcs   list of all zombies for separation
     * @return the combined velocity vector
     */
    private Pair<Double, Double> computeTotalEffect(final Z npc, final S target, final List<Z> npcs) {
        Pair<Double, Double> targetVel = movStrategy.computeVelocity(npc, target);
        Pair<Double, Double> separationForce = sepStrategy.computeForce(npc, npcs);

        Pair<Double, Double> combinedVel = PairUtils.sum(
                targetVel,
                PairUtils.mulScale(separationForce, SEPARATION_WEIGHT));

        double maxSpeed = PairUtils.norm2(npc.getBaseZombieVel());
        double speed = PairUtils.norm2(combinedVel);

        if (speed > maxSpeed) {
            combinedVel = PairUtils.mulScale(PairUtils.normalize(combinedVel), maxSpeed);
        }

        return combinedVel;
    }
}
