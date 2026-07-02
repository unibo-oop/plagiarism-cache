package utilitiesimpl;

import java.awt.Toolkit;

public final class ViewSettings {
    /**
     * Height screen.
     */
    public static final double DIMENSION_HEIGTH_SCREEN = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    /**
     * Width screen.
     */
    public static final double DIMENSION_WIDTH_SCREEN = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    /**
     * Proportion screen of view.
     */
    public static final double PROPORTION = 1.15;
    /**
     * Height view.
     */
    public static final double DIMENSION_HEIGTH_VIEW = DIMENSION_HEIGTH_SCREEN / PROPORTION;
    /**
     * Width view.
     */
    public static final double DIMENSION_WIDTH_VIEW = DIMENSION_WIDTH_SCREEN / PROPORTION;

    private ViewSettings() { };

}
