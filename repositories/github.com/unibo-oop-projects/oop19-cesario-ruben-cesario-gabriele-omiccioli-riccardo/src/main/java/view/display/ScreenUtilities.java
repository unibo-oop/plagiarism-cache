package view.display;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import application.Main;
import javafx.scene.image.ImageView;

/**
 * Manages the screen resolution and provides methods for calculating relative positions.
 */
public final class ScreenUtilities {

    // 4K resolution width and height in pixels
    private static final double WIDTH_4K   = 3840;
    private static final double HEIGHT_4K  = 2160;

    // QHD resolution width and height in pixels
    private static final double WIDTH_QHD  = 2560;
    private static final double HEIGHT_QHD = 1440;

    // FHD resolution width and height in pixels
    private static final double WIDTH_FHD  = 1920;
    private static final double HEIGHT_FHD = 1080;

    // HDP resolution width and height in pixels
    private static final double WIDTH_HDP  = 1600;
    private static final double HEIGHT_HDP =  900;

    // HD resolution width and height in pixels
    private static final double WIDTH_HD   = 1280;
    private static final double HEIGHT_HD  =  720;

    // Aspect ratio of the resolutions used in the game
    private static final double ASPECT_RATIO = 16 / 9;

    // Scale factors compared to 4k resolution
    private static final double SCALE_FACTOR_4K  = 1; 
    private static final double SCALE_FACTOR_QHD = HEIGHT_QHD / HEIGHT_4K; // ~= 0.66667
    private static final double SCALE_FACTOR_FHD = HEIGHT_FHD / HEIGHT_4K; //  = 0.5
    private static final double SCALE_FACTOR_HDP = HEIGHT_HDP / HEIGHT_4K; // ~= 0.41667
    private static final double SCALE_FACTOR_HD  = HEIGHT_HD  / HEIGHT_4K; // ~= 0.33334

    // Variables used to store the screen resolution
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int SCREEN_WIDTH  = (int) SCREEN_SIZE.getWidth();
    private static final int SCREEN_HEIGHT = (int) SCREEN_SIZE.getHeight();

    // Variables used to store the monitor resolution
    private static GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private static final int MONITOR_WIDTH = graphicsDevice.getDisplayMode().getWidth();
    private static final int MONITOR_HEIGHT = graphicsDevice.getDisplayMode().getHeight();

    // Scaling factor between the real monitor resolution and the current resolution set in the OS
    private static final int SCALING = (int) (ScreenUtilities.getMonitorHeight() / ScreenUtilities.getScreenHeight() * 100);

    // Variables used to store the current game resolution
    private static double currentWidth;
    private static double currentHeight;

    // Variable used to store the scale factor of the current game resolution
    private static double currentScaleFactor;

    // Variable used to check if the game is in fullscreen mode
    private static Boolean fullscreen = false;

    private ScreenUtilities() {
    }

    /**
     * Check the screen resolution and set the resolution to be lower than the screen resolution.
     * In case of HDP resolution, unrecognized resolution or resolution not in 16/9 aspect ratio, 
     * set the resolution to HD.
     */
    public static void initializeScreenSize() {
        if (SCREEN_WIDTH == WIDTH_4K && SCREEN_HEIGHT == HEIGHT_4K) {
            setQHDResolution();
        } else if (SCREEN_WIDTH == WIDTH_QHD && SCREEN_HEIGHT == HEIGHT_QHD) {
            setFHDResolution();
        } else if (SCREEN_WIDTH == WIDTH_FHD && SCREEN_HEIGHT == HEIGHT_FHD) {
            setHDPResolution();
        } else {
            setHDResolution();
        }
    }

    /**
     * Set the current game resolution to the maximum resolution allowed.
     */
    public static void setMaxResolution() {
        if (SCREEN_WIDTH >= WIDTH_4K && SCREEN_HEIGHT >= HEIGHT_4K) {
            set4KResolution();
        } else if (SCREEN_WIDTH >= WIDTH_QHD && SCREEN_HEIGHT >= HEIGHT_QHD) {
            setQHDResolution();
        } else if (SCREEN_WIDTH >= WIDTH_FHD && SCREEN_HEIGHT >= HEIGHT_FHD) {
            setFHDResolution();
        } else if (SCREEN_WIDTH >= WIDTH_HDP && SCREEN_HEIGHT >= HEIGHT_HDP) {
            setHDPResolution();
        } else {
            setHDResolution();
        }
    }

    /**
     * Set the current game resolution to the next bigger resolution allowed.
     */
    public static void setNextResolution() {
        if (currentHeight == HEIGHT_QHD) {
            set4KResolution();
        } else if (currentHeight == HEIGHT_FHD) {
            setQHDResolution();
        } else if (currentHeight == HEIGHT_HDP) {
            setFHDResolution();
        } else if (currentHeight == HEIGHT_HD) {
            setHDPResolution();
        }
    }

    /**
     * Set the current game resolution to the next smaller resolution allowed.
     */
    public static void setPreviousResolution() {
        if (currentHeight == HEIGHT_HDP) {
            setHDResolution();
        } else if (currentHeight == HEIGHT_FHD) {
            setHDPResolution();
        } else if (currentHeight == HEIGHT_QHD) {
            setFHDResolution();
        } else if (currentHeight == HEIGHT_4K) {
            setQHDResolution();
        }
    }

    /**
     * Resizes and center the stage.
     */
    public static void resizeStage() {
        Main.getStage().setWidth(ScreenUtilities.getCurrentWidth());
        Main.getStage().setHeight(ScreenUtilities.getCurrentHeight());
        Main.getStage().centerOnScreen();
    }

    /**
     * @return Return if the game is in fullscreen
     */
    public static Boolean isFullscreen() {
        return fullscreen;
    }

    /**
     * Sets the value of the variable fullscreen.
     * @param value The value to be set
     */
    public static void setFullscreen(final Boolean value) {
        fullscreen = value;
    }

    /**
     * @return The current resolution width in pixels
     */
    public static double getCurrentWidth() {
        return currentWidth;
    }

    /**
     * @return The current resolution height in pixels
     */
    public static double getCurrentHeight() {
        return currentHeight;
    }

    /**
     * @return The monitor width in pixels
     */
    public static double getScreenWidth() {
        return SCREEN_WIDTH;
    }

    /**
     * @return The monitor height in pixels
     */
    public static double getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    /**
     * @return The monitor width in pixels
     */
    public static double getMonitorWidth() {
        return MONITOR_WIDTH;
    }

    /**
     * @return The monitor height in pixels
     */
    public static double getMonitorHeight() {
        return MONITOR_HEIGHT;
    }

    /**
     * @return The OS scaling factor (in percentage)
     */
    public static int getScaling() {
        return SCALING;
    }

    /**
     * @return The aspect ratio of the game
     */
    public static double getAspectRatio() {
        return ASPECT_RATIO;
    }

    /**
     * @return The current resolution scale factor relative to 4K resolution
     */
    public static double getCurrentScaleFactor() {
        return currentScaleFactor;
    }

    /**
     * @param position The X position in percentage on the stage
     * @param image The image whose position needs to be calculated
     * @return The X position in pixels of the percentage position on the stage considering the offset given by the width of the image
     */
    public static double getPercXPos(final double position, final ImageView image) {
        return position * currentWidth / 100 - image.getFitWidth() / 2;
    }

    /**
     * @param position The X position in percentage on the stage
     * @param offset The width in pixels of the object
     * @return The X position in pixels of the percentage position on the stage considering the offset given by the width of the object
     */
    public static double getPercXPos(final double position, final double offset) {
        return position * currentWidth / 100 - offset / 2;
    }

    /**
     * @param position The Y position in percentage on the stage
     * @param image The image whose position needs to be calculated
     * @return The Y position in pixels of the percentage position on the stage considering the offset given by the height of the image
     */
    public static double getPercYPos(final double position, final ImageView image) {
        return position * currentHeight / 100 - image.getFitHeight() / 2;
    }

    /**
     * @param position The Y position in percentage on the stage
     * @param offset The height in pixels of the object
     * @return The Y position in pixels of the percentage position on the stage considering the offset given by the height of the object
     */
    public static double getPercYPos(final double position, final double offset) {
        return position * currentHeight / 100 - offset / 2;
    }

    /**
     * @return 4K height pixels
     */
    public static double get4KHeight() {
        return HEIGHT_4K;
    }

    /**
     * @return QHD height pixels
     */
    public static double getQHDHeight() {
        return HEIGHT_QHD;
    }

    /**
     * @return FHD height pixels
     */
    public static double getFHDHeight() {
        return HEIGHT_FHD;
    }

    /**
     * @return HDP height pixels
     */
    public static double getHDPHeight() {
        return HEIGHT_HDP;
    }

    /**
     * @return HD height pixels
     */
    public static double getHDHeight() {
        return HEIGHT_HD;
    }

    /**
     * Sets the scale factor.
     * @param value The value to be set
     */
    public static void setScaleFactor(final double value) {
        currentScaleFactor = value;
    }

    /**
     * Set the game current resolution and calculate scale factor.
     * @param width The width to be set in pixels
     * @param height The height to be set in pixels
     */
    public static void setResolution(final double width, final double height) {
        currentWidth = width;
        currentHeight = height;
        currentScaleFactor = height / HEIGHT_4K;
    }

    /**
     * Set the game resolution and scale factor.
     * @param width The width to be set in pixels
     * @param height The height to be set in pixels
     * @param scaleFactor The scale factor to be set
     */
    public static void setResolution(final double width, final double height, final double scaleFactor) {
        currentWidth = width;
        currentHeight = height;
        currentScaleFactor = scaleFactor;
        resizeStage();
    }

    /**
     * Set the game resolution to 4K.
     */
    public static void set4KResolution() {
        setResolution(WIDTH_4K, HEIGHT_4K, SCALE_FACTOR_4K);
    }

    /**
     * Set the game resolution to QHD.
     */
    public static void setQHDResolution() {
        setResolution(WIDTH_QHD, HEIGHT_QHD, SCALE_FACTOR_QHD);
    }

    /**
     * Set the game resolution to FHD.
     */
    public static void setFHDResolution() {
        setResolution(WIDTH_FHD, HEIGHT_FHD, SCALE_FACTOR_FHD);
    }

    /**
     * Set the game resolution to HDP.
     */
    public static void setHDPResolution() {
        setResolution(WIDTH_HDP, HEIGHT_HDP, SCALE_FACTOR_HDP);
    }

    /**
     * Set the game resolution to HD.
     */
    public static void setHDResolution() {
        setResolution(WIDTH_HD, HEIGHT_HD, SCALE_FACTOR_HD);
    }

}
