package frogger.model.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import frogger.common.Constants;
import frogger.common.Position;
import frogger.model.interfaces.EntitySpawner;

/**
 * {@inheritDoc}.
 * @param <X> the type of entity to spawn
 */
public abstract class AbstractRandomEntitySpawner<X> implements EntitySpawner<X> {

    private final Random ran;

    /**
     * The purpose of this constructor is to be able to pass the random object from outside
     * the class, it's useful to make deterministic output while testing.
     * @param ran the random object that will be used in this class.
     */
    @SuppressFBWarnings(
        value = "EI2",
        justification = "Random is intentionally injected to allow deterministic behavior during testing"
    )
    public AbstractRandomEntitySpawner(final Random ran) {
        this.ran = ran;
    }

    /**
     * {@inheritDoc}
     * <p>
     * If a valid position cannot be found after a certain number of attempts, the method
     * will stop trying and return an empty list.
     */
    @Override
    public List<X> spawn(final int min, final int max) {
        final List<X> result = new ArrayList<>();
        final Set<Position> usedPositions = new HashSet<>();
        final int count = ran.nextInt(max - min + 1) + min;
        int it = 0;

        while (result.size() < count) {
            if (it >= Constants.MAX_ITERATIONS_NUMBER) {
                return List.of();
            }

            final Position pos = generatePosition();
            if (isValidPosition(pos, usedPositions)) {
                final X entity = createEntity(pos);
                result.add(entity);
                addPos(pos, usedPositions);
            }
            it++;
        }

        return result;
    }

    /**
     * Utility method to generate a random value beetwen the max and min y.
     * @return the random value
     */
    public final int randomY() {
        return ran.nextInt(Constants.MAX_Y - Constants.MIN_Y + 1) + Constants.MIN_Y;
    }

    /**
     * Utility method to generate a random value beetwen the max and min x.
     * @return the random value
     */
    public final int randomX() {
        return ran.nextInt(Constants.MAX_X - Constants.MIN_X + 1) + Constants.MIN_X;
    }

    /**
     * Check if the position given is already present in the list of used positions.
     * @param pos the position to be checked
     * @param used the list of already given positions
     * @return true if is valid, false otherwise
     */
    protected abstract boolean isValidPosition(Position pos, Set<Position> used);

    /**
     * Create a random position.
     * @return the position
     */
    protected abstract Position generatePosition();

    /**
     * Create the entity to add to the list.
     * @param pos the position of the entity to create
     * @return the entity
     */
    protected abstract X createEntity(Position pos);

    /**
     * Add a position to the list of already used ones.
     * @param pos the position to add
     * @param usedPositions the list of positions already occupied
     */
    protected void addPos(final Position pos, final Set<Position> usedPositions) {
        usedPositions.add(pos);
    }
}
