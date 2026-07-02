package model.ai.movement;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import model.entities.zombie.ZombieState;
import utils.PairUtils;

/**
 * Default implementation of {@link MovementStrategy} that computes
 * the velocity vector for a Zombie to move towards a Survivor target.
 * <p>
 * The strategy sets the zombie's state based on the main movement
 * direction (horizontal or vertical) and stops movement if the zombie
 * is suffering damage or has reached the target.
 * </p>
 *
 * @param <Z> the type of Zombie
 * @param <S> the type of Survivor
 */
public class DefaultMovementStrategy<Z extends Zombie, S extends Survivor> implements MovementStrategy<Z, S> {

    private static final Pair<Double, Double> ZERO_VELOCITY = Pair.of(0.0, 0.0);

    /**
     * Computes the velocity vector for the given zombie to move towards the target
     * survivor.
     * <p>
     * If the zombie is in the "suffer damage" state, the velocity will be zero.
     * If the zombie is at the exact position of the target, the state is set to
     * idle
     * and velocity zero is returned.
     * Otherwise, the velocity points towards the target with magnitude equal to the
     * zombie's base speed.
     * The zombie state is updated based on the dominant axis of movement.
     * </p>
     *
     * @param zombie the Zombie whose velocity is being computed
     * @param target the Survivor target that the Zombie is moving towards
     * @return a {@code Pair<Double, Double>} representing the velocity vector (x and y components)
     */
    @Override
    public Pair<Double, Double> computeVelocity(final Zombie zombie, final Survivor target) {

        if (zombie.getState() == ZombieState.ZOMBIE_SUFFER_DAMAGE) {
            return ZERO_VELOCITY;
        }

        Pair<Double, Double> direction = PairUtils.diff(target.getCurrentPos(), zombie.getCurrentPos());
        double distance = PairUtils.norm2(direction);

        if (distance == 0) {
            zombie.setState(ZombieState.ZOMBIE_IDLE);
            return ZERO_VELOCITY;
        }

        Pair<Double, Double> normalizedDirection = PairUtils.normalize(direction);

        double dx = direction.getLeft();
        double dy = direction.getRight();

        if (Math.abs(dx) > Math.abs(dy)) {
            zombie.setState(dx < 0 ? ZombieState.ZOMBIE_MOVE_RIGHT : ZombieState.ZOMBIE_MOVE_LEFT);
        } else {
            zombie.setState(dy < 0 ? ZombieState.ZOMBIE_MOVE_DOWN : ZombieState.ZOMBIE_MOVE_UP);
        }

        double speed = PairUtils.norm2(zombie.getBaseZombieVel());
        return PairUtils.mulScale(normalizedDirection, speed);
    }

}
