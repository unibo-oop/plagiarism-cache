package view.graphics_util;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Interface for scaling 2D positions and dimensions according to set scale
 * factors.
 */
public interface Scaler {

    /**
     * Sets the target scaled dimensions.
     * 
     * @param height desired scaled height
     * @param width  desired scaled width
     */
    void setScaleDimensions(final int height, final int width);

    /**
     * Scales an X-coordinate position given as a pair of doubles.
     * 
     * @param posX pair representing original X coordinates or bounds
     * @return scaled X position as integer
     */
    int scaleX(final Pair<Double, Double> posX);

    /**
     * Scales a Y-coordinate position given as a pair of doubles.
     * 
     * @param posY pair representing original Y coordinates or bounds
     * @return scaled Y position as integer
     */
    int scaleY(final Pair<Double, Double> posY);

    /**
     * Gets the currently set scaled height.
     * 
     * @return scaled height in pixels
     */
    int getScaledHeight();

    /**
     * Gets the scaling ratio applied on the X-axis.
     * 
     * @return ratio as a double
     */
    double getRatioX();

    /**
     * Gets the scaling ratio applied on the Y-axis.
     * 
     * @return ratio as a double
     */
    double getRatioY();

}
