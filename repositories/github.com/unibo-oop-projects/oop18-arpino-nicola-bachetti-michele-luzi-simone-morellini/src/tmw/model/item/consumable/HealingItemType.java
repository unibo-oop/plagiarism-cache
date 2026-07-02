package tmw.model.item.consumable;

/**
 * List of the different type of consumable with their specific characteristics.
 */
public enum HealingItemType {

    /**
     * adds 25% of life to the character and gives 10 points.
     */
    LACTOSE_FREE_MILK("Lactose-free milk", "Restores 25% of the life", 0.25, 10, 0.03),

    /**
     * adds 50% of life to the character and gives 20 points.
     */
    SKIMMED_MILK("Skimmed milk", "Restores 50% of the life", 0.50, 20, 0.03),

    /**
     * adds 100% of life to the character and gives 30 points.
     */
    WHOLE_MILK("Whole milk", "Restores 100% of the life", 1.0, 30, 0.03);

    private String name;
    private String description;
    private double multiplier;
    private int points;
    private double proportion;

    /**
     * @param name        Name of the item
     * @param description Description of the item
     * @param multiplier  Multiplier of the item
     * @param points      Points that the item gives
     * @param proportion  Proportion of the item
     */
    HealingItemType(final String name, final String description, final double multiplier, final int points,
            final double proportion) {
        this.name = name;
        this.description = description;
        this.multiplier = multiplier;
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

