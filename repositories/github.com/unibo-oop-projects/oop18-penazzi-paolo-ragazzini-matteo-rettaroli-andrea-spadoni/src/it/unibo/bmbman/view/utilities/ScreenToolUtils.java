package it.unibo.bmbman.view.utilities;

import java.awt.Toolkit;
/**
 * Screen resolution utility class.
 */
public final class ScreenToolUtils {
    private static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final double WIDTH_REF = 2000;
    private static final double HEIGHT_REF = 1200;
    private static final double WQHDSCALE = 2;
    private static final double FHDSCALE = 1;
    /**
     * Set the Scale field due to the screen resolution.
     */
    public static final int SCALE = (int) getScreenScale();

    private ScreenToolUtils() {
    }
    /**
     * Method that returns the right scale for all components according to screen resolution.
     * @return SCALE the right scale
     */
    public static double getScreenScale() {
        if (SCREEN_WIDTH > WIDTH_REF && SCREEN_HEIGHT > HEIGHT_REF) {
            return WQHDSCALE;
        } else {
            return FHDSCALE;
        }
    }
    /**
     * Method that returns a String representing the screen resolution.
     * @return a String representing the resolution of the scale
     */
    public static String getScreenRes() {
        if (SCREEN_WIDTH > WIDTH_REF && SCREEN_HEIGHT > HEIGHT_REF) {
            return "WQHD";
        } else {
            return "FHD";
        }
    }
}
