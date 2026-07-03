package game.buffs;

/**
 * The power of a low level buff.
 */
public final class LowGradeBuff implements BuffingStrategy {

    @Override
    public int multiplyEffect(final int effect) {
        return effect;
    }
}
