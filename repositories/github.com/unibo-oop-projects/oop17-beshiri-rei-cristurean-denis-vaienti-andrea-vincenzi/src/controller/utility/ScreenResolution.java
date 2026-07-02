package controller.utility;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * This Class will give the screen resolution.
 * Every PC have its personal resolution.
 */
public final class ScreenResolution {

    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    private static final double WIDTH = SCREEN_SIZE.getWidth();
    private static final double HEIGHT = SCREEN_SIZE.getHeight();

    private ScreenResolution() { }

    /**
     * @return the width resolution.
     */
    public static int getWidthSize() {
        return (int) WIDTH;
    }

    /**
     * @return the height resolution.
     */
    public static int getHeigtSize() {
        return (int) HEIGHT;
    }
}
