package user.enums;

/**
 * This Enum contains the possible ranks for the powerups of the ship's
 * statistics.
 */
public enum PowerupRanks {

    /**
     * This fields represents the value associated to the various rank.
     */
    RANK_0(0), RANK_1(1), RANK_2(2), RANK_3(3), RANK_4(4), RANK_5(5);

    private int value;

    PowerupRanks(final int value) {
        this.value = value;
    }

    /**
     * This method returns the desired rank.
     * 
     * @return the powerup rank requested
     */
    public int getValue() {
        return this.value;
    }

}
