package model.utilities;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * Contains global information for setup the view adaptable to the resolution.
 */
public final class ScreenUtilities {

    /**
     * @return current screen size
     */
    private static final Rectangle2D SCREEN_RES = Screen.getPrimary().getBounds();

    /**
     * Width of the gui (portion adapted to the resolution).
     */
    public static final double SCREEN_WIDTH = SCREEN_RES.getWidth() / 1.2; 

    /**
     * Height of the gui (portion adapted to the resolution).
     */
    public static final double SCREEN_HEIGHT = SCREEN_RES.getHeight() / 1.2;

    /**
     * Use to have static font size label for all view.
     */
    public static final int FONT_NORMAL_LABEL_SIZE = 42;

    /**
     * Use to have static font size little label for all view.
     */
    public static final int FONT_SUB_LABEL_SIZE = 24;

    /**
     * Use to have static font large sized2 label for all view.
     */
    public static final int FONT_LARGE_LABEL_SIZE = 70;

    /**
     * This number represent the divider number to center object in scene.
     */
    public static final int CENTER_DIVIDER = 2;

    /**
     * This number represent the animation transition duration.
     */
    public static final int ANIMATION_DURATION = 600;

    /**
     * The minimum WIDTH number that the stage can have.
     */
    public static final int MIN_RESIZE_WIDTH = 460;

    /**
     * The minimum HEIGHT number that the stage can have.
     */
    public static final int MIN_RESIZE_HEIGHT = 650;

    /**
     * Width of the game board (used in model board).
     */
    public static final double BOARD_WIDTH = 611;

    /**
     * Height of the game board (used in model board).
     */
    public static final double BOARD_HEIGHT = 611;

    /**
     * Width of the canvas in GameController gui (adapted to the height res for make a square).
     */
    public static final double CANVAS_WIDTH = SCREEN_HEIGHT / 1.3;

    /**
     * Height of the canvas in GameController gui (adapted to the height res for make a square).
     */
    public static final double CANVAS_HEIGHT = SCREEN_HEIGHT / 1.0;

    /**
     * Convert every entity of the game width (adapted to the current resolution).
     */
    public static final double REAL_X = CANVAS_WIDTH / BOARD_WIDTH;

    /**
     * Convert every entity of the game height (adapted to the current resolution).
     */
    public static final double REAL_Y = CANVAS_HEIGHT / BOARD_HEIGHT;

    /**
     * the number of bricks on the x-axis.
     */
    public static final double BRICK_NUMBER_X = 13;

    /**
     * the number of bricks on the y-axis.
     */
    public static final double BRICK_NUMBER_Y = 26;



    private ScreenUtilities() {

    }
}
