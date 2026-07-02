package rogue.model.creature;

/**
 * This class represents the standard strategy with which increase the max hp value.
 */
public final class StandardMaxHpIncreaseStrategy implements MaxHpIncreaseStrategy {

    private static final int DELTA = 12;

    @Override
    public int getMaxHp(final int level) {
        return level * DELTA;
    }

}
