package it.unibo.javajump.view;

import java.awt.Dimension;
import java.awt.Rectangle;

import static it.unibo.javajump.utility.Constants.MAIN_VIEW_CENTER_DIV;
import static it.unibo.javajump.utility.Constants.MAIN_VIEW_DRAW_X_INIT;
import static it.unibo.javajump.utility.Constants.MAIN_VIEW_DRAW_Y_INIT;

/**
 * The class Scale utils, used for scaling the view elements accordingly.
 */
public final class ScaleUtils {
    /**
     * Compute a scaled rectangle.
     *
     * @param virtualWidth  the virtual width
     * @param virtualHeight the virtual height
     * @param panelSize     the panel size
     *
     * @return the scaled rectangle
     */
    public static Rectangle computeScaledRectangle(final int virtualWidth, final int virtualHeight, final Dimension panelSize) {
        final float targetAspect = (float) virtualWidth / virtualHeight;
        final float panelAspect = (float) panelSize.width / panelSize.height;
        final int drawWidth, drawHeight, drawX, drawY;

        if (panelAspect > targetAspect) {
            drawHeight = panelSize.height;
            drawWidth = Math.round(drawHeight * targetAspect);
            drawX = (panelSize.width - drawWidth) / MAIN_VIEW_CENTER_DIV;
            drawY = MAIN_VIEW_DRAW_Y_INIT;
        } else {
            drawWidth = panelSize.width;
            drawHeight = Math.round(drawWidth / targetAspect);
            drawX = MAIN_VIEW_DRAW_X_INIT;
            drawY = (panelSize.height - drawHeight) / MAIN_VIEW_CENTER_DIV;
        }
        return new Rectangle(drawX, drawY, drawWidth, drawHeight);
    }

    /**
     * Private constructor for utility class.
     *
     * @throws AssertionError the assertion error if the constructor is called
     */
    private ScaleUtils() {
        throw new AssertionError("This is a utility class, it should not be instantiated!");
    }
}
