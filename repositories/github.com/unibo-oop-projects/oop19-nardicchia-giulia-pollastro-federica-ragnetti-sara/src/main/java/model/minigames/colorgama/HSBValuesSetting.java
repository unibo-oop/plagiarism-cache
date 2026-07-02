package model.minigames.colorgama;

/**
 * This interface represents the settings of the hsb color values.
 *
 */
public interface HSBValuesSetting {

    /**
     * Get of the random origin of the color value.
     * 
     * @return origin
     *           the origin of each random value
     */
    Double getRandomColorOrigin();

    /**
     * Get of the random bound of the color value.
     * 
     * @return bound
     *           the bound of each random value
     */
    Double getRandomColorBound();

    /**
     * Get the range of the hue component.
     * 
     * @return range
     *          the range of the hue
     */
    Integer getHueRange();

    /**
     * Get the range of the saturation or brightness component.
     * 
     * @return range
     *          the range of saturation or brightness
     */
    Double getValueRange();
}
