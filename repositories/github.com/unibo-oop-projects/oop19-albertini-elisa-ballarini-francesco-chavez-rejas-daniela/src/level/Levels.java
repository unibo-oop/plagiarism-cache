package level;

/**
 * This class models the levels of the play. All the levels have a parameter that
 * represent the speed associated to the level.
 */
public enum Levels {
    /**
     * Enumeration of levels. Because at certain speeds the play would
     * be too difficult, the first 5 levels have a speed equals
     * to the previous - 100 milliseconds, on the contrary, the last five have
     * speeds equals to the previous - 50 milliseconds.
     */
    LEVEL_1(1000), LEVEL_2(900), LEVEL_3(800), LEVEL_4(700), LEVEL_5(600), LEVEL_6(550), LEVEL_7(500), LEVEL_8(450), LEVEL_9(400), LEVEL_10(350);

    private final int speed;
    private static Levels[] levels = values();

    Levels(final int speed) {
        this.speed = speed;
    }

    /**
     * @return the speed associated to the level
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Calculate the next level.
     * 
     * @return the next level
     */
    public Levels getNextLevel() {
        // the index of the next level
        final int next = this.ordinal() + 1;
        if (next < levels.length) {
            // return that level
            return levels[this.ordinal() + 1];
        } else {
            // after the last level it would return always the last level
            return levels[levels.length - 1];
        }
    }

}
