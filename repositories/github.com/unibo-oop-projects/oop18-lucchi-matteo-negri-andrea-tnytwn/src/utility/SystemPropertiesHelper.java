package utility;

import java.awt.Toolkit;

/**
 * Class used to get some information about the system where tha application run.
 */
public final class SystemPropertiesHelper {

    /**
     * The width resolution.
     */
    public static final int WIDTH_RESOLUTION = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    /**
     * The height resolution.
     */
    public static final int HEIGHT_RESOLUTION = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    /**
     * The preferred width resolution.
     */
    public static final int PREFERRED_WIDTH_RESOLUTION = (WIDTH_RESOLUTION / 3) * 2;

    /**
     * The preferred width resolution.
     */
    public static final int PREFERRED_HEIGHT_RESOLUTION = (HEIGHT_RESOLUTION / 4) * 3;

    /**
     * The default spacing between buttons.
     */
    public static final int DEFAULT_SPACING = PREFERRED_HEIGHT_RESOLUTION / 16;

    private SystemPropertiesHelper() {
    }
}
