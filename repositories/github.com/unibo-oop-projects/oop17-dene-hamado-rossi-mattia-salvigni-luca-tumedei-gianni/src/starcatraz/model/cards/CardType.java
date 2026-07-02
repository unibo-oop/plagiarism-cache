package starcatraz.model.cards;

/**
 * Possible types of a Starcatraz Card.
 */
public enum CardType {
    ROBOT("Robot", 0),
    ROCKET("Rocket", 1),
    ROCKET_PART_A("Rocket Part A", 2),
    ROCKET_PART_B("Rocket Part B", 3),
    CHIP("Chip", 4);

    private String name;
    private int value;

    /**
     * Constructor for CardType.
     * @param name
     * @param value
     */
    CardType(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * @return the Type value
     */
    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
