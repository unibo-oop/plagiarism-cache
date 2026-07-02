package utilities;

/**
 * Enum representing the colors used during the game, can be also used in the view returning the standard paths.
 * Andrea Serafini.
 *
 */

public enum Colors {
    /**
     * Blue color.
     */
    Blue(java.awt.Color.BLUE, javafx.scene.paint.Color.BLUE),
    /**
     * Red color.
     */
    Red(java.awt.Color.RED, javafx.scene.paint.Color.RED),
    /**
     * White color.
     */
    White(java.awt.Color.WHITE, javafx.scene.paint.Color.WHITE),
    /**
     * Yellow color.
     */
    Yellow(java.awt.Color.YELLOW, javafx.scene.paint.Color.YELLOW),
    /**
     * Cyan color.
     */
    Cyan(java.awt.Color.CYAN, javafx.scene.paint.Color.CYAN),
    /**
     * Green color.
     */
    Green(java.awt.Color.GREEN, javafx.scene.paint.Color.GREEN),
    /**
     * Black color.
     */
    Black(java.awt.Color.BLACK, javafx.scene.paint.Color.BLACK),
    /**
     * Gray color.
     */
    Gray(java.awt.Color.GRAY, javafx.scene.paint.Color.GRAY),
    /**
     * None color.
     */
    None(null, javafx.scene.paint.Color.TRANSPARENT);

    private final java.awt.Color swingColor;
    private final javafx.scene.paint.Color fxColor;

    Colors(final java.awt.Color swingColor, final javafx.scene.paint.Color fxColor) {
        this.swingColor = swingColor;
        this.fxColor = fxColor;
    }

    /**
     *
     * @return fxColor path
     */
    public javafx.scene.paint.Color getFxPath() {
        return this.fxColor;
    }

    /**
     *
     * @return swingColor path
     */
    public java.awt.Color getSwingPath() {
        return this.swingColor;
    }
}
