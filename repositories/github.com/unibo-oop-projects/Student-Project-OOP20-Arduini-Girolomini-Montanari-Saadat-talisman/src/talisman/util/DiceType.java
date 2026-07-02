package talisman.util;

/**
 * Used to specifty the type of dices used.
 * 
 * @author Alberto Arduini
 *
 */
public enum DiceType {
    /**
     * 
     */
    SIX(6, 1), DOUBLE_SIX(12, 2);

    private final int max;
    private final int min;

    DiceType(final int max, final int min) {
        this.max = max;
        this.min = min;
    }

    public int getMinValue() {
        return this.min;
    }

    public int getFaces() {
        return this.max;
    }
}
