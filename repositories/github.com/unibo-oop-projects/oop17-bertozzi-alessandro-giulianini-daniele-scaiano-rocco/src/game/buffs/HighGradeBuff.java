package game.buffs;

/**
 * The power of a high level buff.
 */
public final class HighGradeBuff implements BuffingStrategy {

    private static final double MULTIPLIER = 5;

    @Override
    public int multiplyEffect(final int effect) {
        return (int) (effect * MULTIPLIER);
    }
}
