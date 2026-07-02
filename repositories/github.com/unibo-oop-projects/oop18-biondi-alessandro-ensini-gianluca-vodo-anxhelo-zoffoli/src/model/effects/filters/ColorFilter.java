package model.effects.filters;

import java.awt.Color;

/**
 * The Color filter allows to change an image RGB value with the given
 * intensity.
 */
public class ColorFilter extends FilterImpl {

    private static final long serialVersionUID = 1L;
    private static final int MAX_PIXEL_VALUE = 255;
    private static final int MIN_PIXEL_VALUE = 0;
    private final int redIntensity;
    private final int greenIntensity;
    private final int blueIntensity;
    private String name = "ColorFilter";

    /**
     * Create a new ColorFilter with the given intensity.
     * 
     * @param red   intensity
     * @param green intensity
     * @param blue  intensity
     */
    public ColorFilter(final int red, final int green, final int blue) {
        super();
        redIntensity = red;
        greenIntensity = green;
        blueIntensity = blue;
    }

    /**
     * @see model.effects.filters.Filter#changePixel(java.awt.Color)
     */
    @Override
    public Color changePixel(final Color source) {
        Color col = source;
        final int red = fixRange(col.getRed() + redIntensity);
        final int green = fixRange(col.getGreen() + greenIntensity);
        final int blue = fixRange(col.getBlue() + blueIntensity);

        col = new Color(red, green, blue, col.getAlpha());
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

    /**
     * Fix the range of the given value adapting it to the min pixel value and the
     * max pixel value.
     * 
     * @param value to fix
     * @return fixed value
     */
    private int fixRange(final int value) {
        int v = value > MAX_PIXEL_VALUE ? MAX_PIXEL_VALUE : value;
        v = v < MIN_PIXEL_VALUE ? MIN_PIXEL_VALUE : v;
        return v;
    }
}
