package model.utility;

/**
 * Enum for direction.
 * <p>
 * Memorize sign according to space.
 */
public enum Direction {
    /**
     * Possible direction, and sign is used for calculate speed.
     * @see Tank#update(model.input.Input)
     */
    UP(-1), DOWN(+1), LEFT(-1), RIGHT(+1);
    /**
     * Sign is used to calculate speed in {@linkplain Tank}.
     */
    private int sign;
    /**
     * Constructor.
     * 
     * @param sign
     *            Allow to speed up operation to calculate speed
     * @see TankImpl#update(model.input.InputImpl);
     */
    Direction(final int sign) {
        this.sign = sign;
    }
    /**
     * Getter for sign.
     * @return sign
     */
    public int getSign() {
        return this.sign;
    }
}