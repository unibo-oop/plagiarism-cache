package model.effects.filters;

import java.awt.Color;

/**
 * The Negative filter allows to change an image to negative.
 */
public class Negative extends FilterImpl {

    private static final long serialVersionUID = 1L;
    private static final int MAX_PIXEL_VALUE = 255;

    private String name = "Negative";

    /**
     * @see model.effects.filters.Filter#changePixel(java.awt.Color)
     */
    @Override
    public Color changePixel(final Color source) {
        Color col = source;
        col = new Color(MAX_PIXEL_VALUE - col.getRed(), MAX_PIXEL_VALUE - col.getGreen(),
                MAX_PIXEL_VALUE - col.getBlue(), col.getAlpha());
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
