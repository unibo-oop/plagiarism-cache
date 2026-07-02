package starcatraz.model.cards;

/**
 * Possible colors of a Starcatraz Card.
 */
public enum CardColor {
    NO_COLOR("", 0),
    RED("Red", 100),
    BLUE("Blue", 200),
    YELLOW("Yellow", 300),
    GREY("Grey", 400);

    private String name;
    private int value;

    /**
     * Constructor for CardColor.
     * @param name
     * @param value
     */
    CardColor(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * @return the Color value
     */
    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
