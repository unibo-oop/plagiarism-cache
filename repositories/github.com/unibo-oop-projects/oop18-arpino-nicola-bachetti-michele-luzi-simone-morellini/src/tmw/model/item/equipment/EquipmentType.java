package tmw.model.item.equipment;

/**
 * List of the different type of equipment with their specific characteristics.
 */
public enum EquipmentType {

    /**
     * increase the damage by a factor 1.5 for 30 seconds and gives 30 points.
     */
    COFFEE("Coffee", "Increase the damage by a factor 1.5", 1.5, 30, 30, 0.03),

    /**
     * increase the damage by a factor 2 for 30 seconds and gives 50 points.
     */
    CHOCOLATE("Chocolate", "Increase the damage by a factor 2", 2.0, 30, 50, 0.03);

    private String name;
    private String description;
    private double multiplier;
    private int duration;
    private int points;
    private double proportion;

    /**
     * @param name        Name of the item
     * @param description Description of the item
     * @param multiplier  Multiplier of the item
     * @param duration    Duration of the item
     * @param points      Points that the item gives
     * @param proportion  Proportion of the item
     */
    EquipmentType(final String name, final String description, final double multiplier, final int duration,
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

