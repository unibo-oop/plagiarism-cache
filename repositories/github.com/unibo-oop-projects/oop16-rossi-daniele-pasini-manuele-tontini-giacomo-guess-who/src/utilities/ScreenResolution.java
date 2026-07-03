package utilities;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

/**
 * Used to retrive the screen witdth/height.
 *
 */
public final class ScreenResolution {
    private static final Rectangle SCREEN = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

    private ScreenResolution() {

    }

    /**
     * @return the screen width
     */
    public static int getScreenWidth() {
        return (int) SCREEN.getWidth();
    }

    /**
     * @return the screen height
     */
    public static int getScreenHeight() {
        return (int) SCREEN.getHeight();
    }

}
