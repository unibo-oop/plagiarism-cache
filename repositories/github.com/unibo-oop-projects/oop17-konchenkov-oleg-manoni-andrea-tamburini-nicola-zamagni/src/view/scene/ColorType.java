package view.scene;

import javafx.scene.paint.Color;

/**
 * ColorType.
 *
 * @author Andrea Manoni
 *
 */
public enum ColorType {

    /**
     * ColorType.
     */
    RED("Red", Color.RED),
    /**
     * ColorType.
     */
    BLUE("Blue", Color.BLUE),
    /**
     * ColorType.
     */
    GREEN("Green", Color.GREEN),
    /**
     * ColorType.
     */
    OLIVE("Olive", Color.OLIVE),
    /**
     * ColorType.
     */
    MAGENTA("Magenta", Color.MAGENTA),
    /**
     * ColorType.
     */
    CYAN("Cyan", Color.CYAN),
    /**
     * ColorType.
     */
    YELLOW("Yellow", Color.YELLOW),
    /**
     * ColorType.
     */
    PINK("Pink", Color.rgb(255, 128, 128)),
    /**
     * ColorType.
     */
    TEAL("Teal", Color.TEAL),
    /**
     * ColorType.
     */
    VIOLET("Violet", Color.rgb(128, 128, 255));

    private String colorName;
    private Color color;

    ColorType(final String colorName, final Color color) {
        this.colorName = colorName;
        this.color = color;
    }

    @Override
    public String toString() {
        return colorName;
    }

    /**
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

}
