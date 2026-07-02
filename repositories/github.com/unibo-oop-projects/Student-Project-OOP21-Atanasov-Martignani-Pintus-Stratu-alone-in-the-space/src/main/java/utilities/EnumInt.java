package utilities;

/**
 * 
 */
public enum EnumInt {
    /**
     *
     */
    ZERO(0),

    /**
     *
     */
    ONE(1),

    /**
     *
     */
    TWO(2),

    /**
     *
     */
    THREE(3),

    /**
     *
     */
    FOUR(4),

    /**
     *
     */
    FIVE(5),

    /**
     *
     */
    SEVEN(6),

    /**
     *
     */
    TEN(10),

    /**
     *
     */
    TWENTYFIVE(25),

    /**
     *
     */
    THIRTY(30),

    /**
     *
     */
    WIDTH(1280),

    /**
     *
     */
    HEIGHT(720),

    /**
     *
     */
    DAMAGE_COLLISION(5),

    /**
     *
     */
    LIFE_POINTS(100),

    /**
     *
     */
    FONT_SIZE(18),

    /**
     *
     */
    MAX_POINTS(999),

    /**
     *
     */
    EXP_REQUIRED(10),

    /**
     *
     */
    POWER_UP_DURATION(15),

    /**
     *
     */
    POWER_UP_SCORE(15),

    /**
     *
     */
    VIEW_ORDER(-51),

    /**
     *
     */
    SLACK(100);

    private final int value;

    EnumInt(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
