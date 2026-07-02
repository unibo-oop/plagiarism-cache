package it.unibo.commons;

import java.awt.Toolkit;

/**
 * This class provides constants used throughout the application.
 */
public final class Constants {

    /** The size of a tile in pixels. */
    public static final int TILE_SIZE = 20;

    /** The width of the screen. */
    public static final int SW = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.70);

    /** The height of the screen. */
    public static final int SH = (SW / 4) * 3;

    /** The source map file path. */
    public static final String SOURCE_MAP = "factory1.tmx";

    /** The proportion by which the application is scaled based on the screen width. */
    public static final double SCALE_PROPORTION = (double) Constants.SW / 800;

    /**
     * Private constructor to prevent instantiation.
     * Utility classes should not be instantiated.
     */
    private Constants() {
    }

}
