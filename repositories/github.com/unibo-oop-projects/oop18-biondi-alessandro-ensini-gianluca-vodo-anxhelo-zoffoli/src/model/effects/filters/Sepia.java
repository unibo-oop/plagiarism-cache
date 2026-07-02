package model.effects.filters;

import java.awt.Color;

/**
 * The Sepia filter allows to change an image to gray sepia.
 */
public class Sepia extends FilterImpl {

    private static final long serialVersionUID = 1L;
    private static final int MAX_PIXEL_VALUE = 255;
    private static final double RED_IN_RED = 0.393;
    private static final double RED_IN_GREEN = 0.349;
    private static final double RED_IN_BLUE = 0.272;
    private static final double GREEN_IN_RED = 0.769;
    private static final double GREEN_IN_GREEN = 0.686;
    private static final double GREEN_IN_BLUE = 0.534;
    private static final double BLUE_IN_RED = 0.189;
    private static final double BLUE_IN_GREEN = 0.168;
    private static final double BLUE_IN_BLUE = 0.131;

    private String name = "Sepia";

    /**
     * @see model.effects.filters.Filter#changePixel(java.awt.Color)
     */
    @Override
    public Color changePixel(final Color source) {
        double r, g, b;
        Color col = source;
        r = RED_IN_RED * col.getRed() + GREEN_IN_RED * col.getGreen() + BLUE_IN_RED * col.getBlue();
        r = r > MAX_PIXEL_VALUE ? MAX_PIXEL_VALUE : r;
        g = RED_IN_GREEN * col.getRed() + GREEN_IN_GREEN * col.getGreen() + BLUE_IN_GREEN * col.getBlue();
        g = g > MAX_PIXEL_VALUE ? MAX_PIXEL_VALUE : g;
        b = RED_IN_BLUE * col.getRed() + GREEN_IN_BLUE * col.getGreen() + BLUE_IN_BLUE * col.getBlue();
        b = b > MAX_PIXEL_VALUE ? MAX_PIXEL_VALUE : b;
        col = new Color((int) r, (int) g, (int) b, col.getAlpha());
        return col;
    }

    /**
     * @see model.effects.Effect#getEffectName()
     */
    @Override
    public String getEffectName() {
        return name;
    }

    /**
     * @see model.effects.Effect#setEffectName(java.lang.String)
     */
    @Override
    public void setEffectName(final String name) {
        this.name = name;
    }
}
