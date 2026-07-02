package model.effects.filters;

import java.awt.Color;

/**
 * The GrayScale filter allows to change an image to gray scale.
 */
public class GrayScale extends FilterImpl {

    private static final long serialVersionUID = 1L;
    private String name = "GrayScale";

    /**
     * @see model.effects.filters.Filter#changePixel(java.awt.Color)
     */
    @Override
    public Color changePixel(final Color source) {
        Color col = source;
        final int avg = (col.getRed() + col.getGreen() + col.getBlue()) / 3;
        col = new Color(avg, avg, avg, col.getAlpha());
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
