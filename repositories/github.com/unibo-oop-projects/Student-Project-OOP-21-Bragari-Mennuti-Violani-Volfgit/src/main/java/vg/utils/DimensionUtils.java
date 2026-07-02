package vg.utils;

import javafx.geometry.Dimension2D;
import javafx.stage.Screen;
import vg.model.MapImpl;

/**
 *  Static class with resolutions in pixels.
 */
public final class DimensionUtils {
    /**
     *  The width of the screen.
     */
    public static final int DEFAULT_WIDTH = 1400;
    /**
     *  The height of the screen.
     */
    public static final int DEFAULT_HEIGHT = 950;
    /**
     * width 4k.
     */
    private static final double WIDTH_4K   = 3840;
    /**
     * height 4k.
     */
    private static final double HEIGHT_4K  = 2160;
    /**
     * width full HD.
     */
    private static final double WIDTH_FHD  = 1920;
    /**
     * height full HD.
     */
    private static final double HEIGHT_FHD = 1080;
    /**
     * width HD.
     */
    private static final double WIDTH_HD   = 1280;
    /**
     * height HD.
     */
    private static final double HEIGHT_HD  =  720;
    /**
     * Native scaling is 4k so the factor is 1.
     */
    private static final double SCALE_BASE = 1;
    /**
     * Scaling factor for FHD (0.5).
     */
    private static final double SCALE_FHD = HEIGHT_FHD / HEIGHT_4K;
    /**
     * Scaling factor for HD (0.333).
     */
    private static final double SCALE_HD  = HEIGHT_HD  / HEIGHT_4K;
    /**
     * The width of the primary screen.
     */
    private static final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
    /**
     * The height of the primary screen.
     */
    private static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
    /**
     * The width that is currently used.
     */
    private static double currentWidth;
    /**
     * The height that is currently used.
     */
    private static double currentHeight;
    /**
     * The current scaling factor that is applied.
     */
    private static double currentScale;
    /**
     * The boolean that indicates if the application mode is fullscreen or not.
     */
    private static Boolean fullscreen = true;
    /**
     * Sets the current fields to the ones compatible from the screen.
     */
    public static void init() {
        if (SCREEN_WIDTH == WIDTH_4K && SCREEN_HEIGHT == HEIGHT_4K) {
            currentHeight = HEIGHT_4K;
            currentWidth = WIDTH_4K;
        } else if (SCREEN_WIDTH == WIDTH_FHD && SCREEN_HEIGHT == HEIGHT_FHD) {
            currentHeight = HEIGHT_FHD;
            currentWidth = WIDTH_FHD;
        } else {
            currentHeight = HEIGHT_HD;
            currentWidth = WIDTH_HD;
        }
    }
    /*
     * Getters and setters.
     */

    public static int getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    public static int getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }

    public static double getCurrentWidth() {
        return currentWidth;
    }

    public static void setCurrentWidth(final double currentWidth) {
        DimensionUtils.currentWidth = currentWidth;
    }

    public static double getCurrentHeight() {
        return currentHeight;
    }

    public static void setCurrentHeight(final double currentHeight) {
        DimensionUtils.currentHeight = currentHeight;
    }

    public static double getCurrentScale() {
        return currentScale;
    }

    public static void setCurrentScale(final double currentScale) {
        DimensionUtils.currentScale = currentScale;
    }

    public static Boolean getFullscreen() {
        return fullscreen;
    }

    public static void setFullscreen(final Boolean fullscreen) {
        DimensionUtils.fullscreen = fullscreen;
    }

    private DimensionUtils() {
    }
    public static Dimension2D getImagePosition(final V2D modelPosition) {
        return new Dimension2D(getCurrentWidth() * modelPosition.getX() / MapImpl.MAXBORDERX,
                getCurrentHeight() * modelPosition.getY() / MapImpl.MAXBORDERY);
    }
    public static Dimension2D getImageSize(final int modelRadius) {
        return new Dimension2D(getCurrentWidth() * modelRadius / MapImpl.MAXBORDERX,
                getCurrentHeight() * modelRadius / MapImpl.MAXBORDERY);
    }
}
