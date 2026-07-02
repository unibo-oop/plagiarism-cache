package model.effects.regolations;

import java.awt.Color;

/**
 * Implementation of Regolation. Serves to adjust the temperature.
 */
public class Temperature extends RegolationImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String effectName = "Temperature";
    private static final float COSTANTADAPTER = 9000f;
    private static final int MAXINTENSITY = 255;
    private static final int MININTENSITY = 0;
    private static final int MAXTEMPERATURE = 7000;

    /**
     * @see model.effects.regolations.Regolation#changePixel(java.awt.Color)
     */
    @Override
    public Color changePixel(final Color source) {
        // Initialize pixel component
        int red;
        int green;
        int blue;
        // Initialize color (color like a pixel)
        Color color = source;
        // take the red/green/blue component and modify it
        // the first calculations (OFFSET + ...) are used to obtain an acceptable index
        if (this.getIndex() >= 0) {
            red = (int) ((COSTANTADAPTER + MAXTEMPERATURE / 100f * this.getIndex()) / COSTANTADAPTER * color.getRed());
            blue = color.getBlue();
        } else {
            blue = (int) ((COSTANTADAPTER - MAXTEMPERATURE / 100f * this.getIndex()) / COSTANTADAPTER
                    * color.getBlue());
            red = color.getRed();
        }
        green = color.getGreen();
        // control of value
        red = red >= MAXINTENSITY ? MAXINTENSITY : red <= MININTENSITY ? MININTENSITY : red;
        green = green >= MAXINTENSITY ? MAXINTENSITY : green <= MININTENSITY ? MININTENSITY : green;
        blue = blue >= MAXINTENSITY ? MAXINTENSITY : blue <= MININTENSITY ? MININTENSITY : blue;
        color = new Color(red, green, blue, color.getAlpha());
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
