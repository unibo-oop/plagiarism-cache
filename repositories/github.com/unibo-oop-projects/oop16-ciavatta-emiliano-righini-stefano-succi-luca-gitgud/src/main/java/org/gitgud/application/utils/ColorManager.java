package org.gitgud.application.utils;

import javafx.scene.paint.Color;

/**
 * A simple color manager.
 */
public interface ColorManager {

    /**
     * Release a previous extracted color from the palette.
     *
     * @param color
     *            the color to release
     * @return true if the color is re-added to the palette
     */
    boolean releaseColor(Color color);

    /**
     * Request a color from the color palette.
     *
     * @return a new distinct color from the palette
     */
    Color requestColor();

}
