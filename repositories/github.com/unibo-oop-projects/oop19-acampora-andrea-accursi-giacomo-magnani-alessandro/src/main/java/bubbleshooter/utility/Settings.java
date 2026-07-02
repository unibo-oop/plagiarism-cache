package bubbleshooter.utility;

import java.awt.Toolkit;

/**
 * Class used to manage the different screen resolutions in {@link view}.
 *
 */
public final class Settings {

    private static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final double GUI_HEIGHT = SCREEN_HEIGHT / 1.60;
    private static final double GUI_WIDTH = SCREEN_WIDTH / 2.72;

    /** 
     * private constructor used to avoid initializations of this class.
     */
    private Settings() {
    }

    /**
     * 
     * @return the width of the screen.
     */
    public static double getScreenWidth() {
        return SCREEN_WIDTH;
    }

    /**
     * 
     * @return the height of the screen.
     */
    public static double getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    /**
     * 
     * @return the height of the GUI.
     */
    public static double getGuiHeight() {
        return GUI_HEIGHT;
    }

    /**
     * 
     * @return the width of the GUI.
     */
    public static double getGuiWidth() {
        return GUI_WIDTH;
    }
}
