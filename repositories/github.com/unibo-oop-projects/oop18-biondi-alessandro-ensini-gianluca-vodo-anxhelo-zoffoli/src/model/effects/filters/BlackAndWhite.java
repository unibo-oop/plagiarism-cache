package model.effects.filters;

import java.awt.Color;

/**
 * The BlackAndWHite filter allows to change an image to only black and white
 * pixel.
 */
public class BlackAndWhite extends FilterImpl {

    private static final long serialVersionUID = 1L;
    private static final int MAX_PIXEL_VALUE = 255;
    private static final int MID_PIXEL_VALUE = 128;

    private String name = "BlackAndWhite";

    /**
     * @see model.effects.filters.Filter#changePixel(java.awt.Color)
     */
    @Override
    public Color changePixel(final Color source) {
        Color col = source;
        final int avg = (col.getRed() + col.getGreen() + col.getBlue()) / 3;
        final int v = avg < MID_PIXEL_VALUE ? 0 : MAX_PIXEL_VALUE;
        col = new Color(v, v, v, col.getAlpha());
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
