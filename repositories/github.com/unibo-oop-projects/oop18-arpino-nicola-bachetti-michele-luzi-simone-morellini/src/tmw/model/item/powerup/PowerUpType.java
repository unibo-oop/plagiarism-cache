package tmw.model.item.powerup;

/**
 * List of the different type of power-up with their specific characteristics.
 */
public enum PowerUpType {

    /**
     * increases the frequency of bullets of 50% for 30 seconds and gives 50 points.
     */
    WHITE_SUGAR("White sugar", "Increases the frequency of bullets", 1.5, 30, 50, 0.03),

    /**
     * increases character's speed of 50% for 30 seconds and gives 50 points.
     */
    SUGAR_CANE("Sugar cane", "Increases the speed of the character", 1.5, 30, 50, 0.03);

    private String name;
    private String description;
    private double multiplier;
    private int duration;
    private int points;
    private double proportion;

    /**
     * @param name        name of the item
     * @param description description of the item
     * @param multiplier  multiplier of the item
     * @param duration    duration of the item
     * @param points      points that the item gives
     * @param proportion  Proportion of the item
     */
    PowerUpType(final String name, final String description, final double multiplier, final int duration,
            final int points, final double proportion) {
        this.name = name;
        this.description = description;
        this.multiplier = multiplier;
        this.duration = duration;
        this.points = points;
        this.proportion = proportion;
    }

    /**
     * @return the name of the item
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the description of the item
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return the multiplier of the item
     */
    public double getMultiplier() {
        return this.multiplier;
    }

    /**
     * @return the duration of the item
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * @return the points that the item gives
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * @return the proportion of the item
     */
    public double getProportion() {
        return this.proportion;
    }
}

