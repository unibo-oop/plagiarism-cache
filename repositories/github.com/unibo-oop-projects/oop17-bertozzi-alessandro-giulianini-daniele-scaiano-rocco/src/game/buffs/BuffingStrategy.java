package game.buffs;

/**
 * A strategy used to determine the power of the buff.
 */
public interface BuffingStrategy {
    /**
     * @param effect the effect to multiply
     * @return the multiplied effect
     */
    int multiplyEffect(int effect);
}
