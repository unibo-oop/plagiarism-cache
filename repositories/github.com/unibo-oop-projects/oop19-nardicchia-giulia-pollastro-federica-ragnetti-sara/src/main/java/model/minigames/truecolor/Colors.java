package model.minigames.truecolor;

/**
 * Enumeration for possible color.
 *
 */
public enum Colors {

    /**
     * Black color for text button.
     */
    BLACK("black"),

    /**
     * Blue color for text button.
     */
    BLUE("blue"),

    /**
     * Yellow color for text button.
     */
    YELLOW("yellow"),

    /**
     * Red color for text button.
     */
    RED("red"),

    /**
     * Green color for text button.
     */
    GREEN("green"),

    /**
     * Pink color for text button.
     */
    PINK("pink"),

    /**
     * Brown color for text button.
     */
    BROWN("brown"),

    /**
     * Orange color for text button.
     */
    ORANGE("orange");

    private String color;

    Colors(final String color) {
        this.color = color;
    }

    /**
     * 
     * @return the name of the color
     */
    public String getName() {
        return this.color;
    }
}
