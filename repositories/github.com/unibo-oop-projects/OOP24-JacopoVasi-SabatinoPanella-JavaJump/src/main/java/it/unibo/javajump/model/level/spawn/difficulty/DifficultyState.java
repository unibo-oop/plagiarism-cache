package it.unibo.javajump.model.level.spawn.difficulty;

/**
 * The enum that represents the Difficulty states, with their relative generation chances.
 */
public enum DifficultyState {
    /**
     * Easy difficulty state: more normal platforms, to accommodate nwe players.
     */
    EASY(0.03f, 0.10f, 0.015f),
    /**
     * Medium difficulty state: introduces more difficult platform types.
     */
    MEDIUM(0.07f, 0.20f, 0.05f),
    /**
     * Hard difficulty state: the normal platforms will be spawned less.
     */
    HARD(0.15f, 0.35f, 0.085f),
    /**
     * Very hard difficulty state: the normal platforms will be spawned even less.
     */
    VERY_HARD(0.30f, 0.40f, 0.1f),
    /**
     * Hell difficulty state: the normal platforms will not be present in generations.
     */
    HELL(0.40f, 0.47f, 0.13f);

    private final float breakableChance;
    private final float movingChance;
    private final float bounceChance;

    DifficultyState(final float breakableChance, final float movingChance, final float bounceChance) {
        this.breakableChance = breakableChance;
        this.movingChance = movingChance;
        this.bounceChance = bounceChance;
    }

    /**
     * Gets the breakable chance.
     *
     * @return the breakable chance
     */
    public float getBreakableChance() {
        return breakableChance;
    }

    /**
     * Gets the moving chance.
     *
     * @return the moving chance
     */
    public float getMovingChance() {
        return movingChance;
    }

    /**
     * Gets the bounce chance.
     *
     * @return the bounce chance
     */
    public float getBounceChance() {
        return bounceChance;
    }
}
