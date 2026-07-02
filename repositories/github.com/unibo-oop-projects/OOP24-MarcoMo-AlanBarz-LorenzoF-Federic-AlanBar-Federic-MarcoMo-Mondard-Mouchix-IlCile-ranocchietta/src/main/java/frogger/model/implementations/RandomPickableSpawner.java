package frogger.model.implementations;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import frogger.common.Constants;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PickableObject;

/**
 * Class that extends AbstractRandomEntitySpawner to specify the behaviour spawning PickableObjects entity.
 */
public class RandomPickableSpawner extends AbstractRandomEntitySpawner<PickableObject> {

    private final Class<? extends PickableObject> type;
    private final Set<Position> alreadyPresent;

    /**
     * Recall the super constructor and initializa type.
     * @param ran random injection, useful for testing
     * @param type the type of PickableObject to create (PowerUp or Coin)
     * @param alreadyPresent the positions of pickable objects that are already in the level (empty if there aren't)
     */
    @SuppressFBWarnings(
        value = "EI2",
        justification = "Random is intentionally injected to allow deterministic behavior during testing"
    )
    public RandomPickableSpawner(final Random ran, final Class<? extends PickableObject> type,
    final Set<Position> alreadyPresent) {
        super(ran);
        this.type = type;
        this.alreadyPresent = new HashSet<>(alreadyPresent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isValidPosition(final Position pos, final Set<Position> used) {
        used.addAll(this.alreadyPresent);
        return !used.contains(pos) && pos.y() != Constants.MIN_Y && pos.y() != Constants.MAX_Y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Position generatePosition() {
        return new Position(randomX(), randomY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PickableObject createEntity(final Position pos) {
        final Pair dim = new Pair(Constants.PICKABLE_OBJECT_WIDTH, Constants.PICKABLE_OBJECT_HEIGHT);
        return PickableObjectFactory.createPickableObject(type, pos, dim);
    }

}
