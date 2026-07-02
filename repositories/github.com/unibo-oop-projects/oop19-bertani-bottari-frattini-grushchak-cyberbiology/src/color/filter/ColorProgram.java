package color.filter;

import java.awt.Color;

/**
 * Enum contains the colors of entities belonging to the world that do not require filters.
 *
 */
public enum ColorProgram {
    /**
     * World background color, LIGHT GREY.
     */
    BACKGROUND_COLOR(225, 225, 225),
    /**
     * Color of dead cells, LESS LIGHT GREY OF THE BALL.
     */
    CELL_DEATH_COLOR(192, 192, 192),
    /**
     * Color of the rocks, GREY.
     */
    STONE_COLOR(95, 95, 95);

    private Color c;

    ColorProgram(final int r, final int g, final int b) {
        c = new Color(r, g, b);
    }

    public Color getColor() {
        return c;
    }

}
