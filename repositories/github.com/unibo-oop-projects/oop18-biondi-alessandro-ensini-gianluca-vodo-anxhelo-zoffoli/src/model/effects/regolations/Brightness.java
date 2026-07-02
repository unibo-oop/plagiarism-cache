package model.effects.regolations;

import java.awt.Color;

/**
 * Implementation of Regolation. Serves to adjust the brightness.
 */
public class Brightness extends RegolationImpl {

    /**
     * Field of object.
     */
    private static final long serialVersionUID = 1L;
    private String effectName = "Brightness";
    private static final int MAXINTENSITY = 1;
    private static final int MININTENSITY = 0;

    /**
     * @see model.effects.regolations.Regolation#changePixel(java.awt.Color)
     */
    @Override
    public Color changePixel(final Color source) {
        // Initialize variables
        final int tonalityFlag = 0;
        final int saturationFlag = 1;
        final int brightnessFlag = 2;
        float[] hsb;
        // Initialize color (color like a pixel)
        Color color = source;
        // use format hsb (hue,saturation,brightness)
        hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        // change in intensity
        hsb[brightnessFlag] = hsb[brightnessFlag] + hsb[brightnessFlag] * this.getIndex() / 100f;
        // control of value
        hsb[brightnessFlag] = hsb[brightnessFlag] > MAXINTENSITY ? MAXINTENSITY
                : hsb[brightnessFlag] < MININTENSITY ? MININTENSITY : hsb[brightnessFlag];
        final Color xolor = new Color(Color.HSBtoRGB(hsb[tonalityFlag], hsb[saturationFlag], hsb[brightnessFlag]));
        color = new Color(xolor.getRed(), xolor.getGreen(), xolor.getBlue(), color.getAlpha());
        // obtain this pixel
        return color;
    }

    /**
     * @see model.effects.Effect#getEffectName()
     */
    @Override
    public String getEffectName() {
        // return effect name
        return this.effectName;
    }

    /**
     * @see model.effects.Effect#setEffectName(java.lang.String)
     */
    @Override
    public void setEffectName(final String name) {
        // set effect name
        this.effectName = name;
    }

}
