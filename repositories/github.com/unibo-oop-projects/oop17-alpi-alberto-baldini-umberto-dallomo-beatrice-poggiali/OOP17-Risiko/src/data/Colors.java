package data;

import java.awt.Color;

/**
 *
 * Colors valure, names and getters.
 */
public enum Colors {

    /**
     * white used as null value, it states that the land hasn't an owner yet.
     */
    WHITE(Color.WHITE, "NULL"),

    /**
     * Yellow color.
     */
    YELLOW(Color.YELLOW, "Yellow"),

    /**
     * Red color.
     */
    RED(Color.RED, "Red"),

    /**
     * Green color.
     */
    GREEN(Color.GREEN, "Green"),

    /**
     * Blue color.
     */
    BLUE(Color.CYAN, "Cyan"),

    /**
     * Purple color.
     */
    PURPLE(Color.PINK, "Pink"),

    /**
     * Black color.
     */
    BLACK(Color.GRAY, "Gray");

    private final Color value;
    private final String name;
    /**
     * number of colors.
     */
    public static final int NUMCOLORS = 7;

    Colors(final Color value, final String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * @return Paint value of a determined Color.
     */
    public Color getColor() {
        return this.value;
    }

    /**
     * @return a string containing the color name.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name();

    }
}

