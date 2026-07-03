package game.buffs;

/**
 * The power of a medium level buff.
 */
public final class BasicBuff implements BuffingStrategy {

    @Override
    public int multiplyEffect(final int effect) {
        return effect * 2;
    }
}
