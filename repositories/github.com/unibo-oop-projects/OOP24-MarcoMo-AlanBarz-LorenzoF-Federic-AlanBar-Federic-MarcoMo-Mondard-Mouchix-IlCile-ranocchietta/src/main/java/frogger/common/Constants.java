package frogger.common;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Utility class containing game-wide constants for dimensions, gameplay, and UI.
 * <p>
 * This class is not meant to be instantiated or extended.
 * </p>
 */
public final class Constants {
    /** The screen dimension as detected from the system. */
    public static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    /** The screen height. */
    public static final int SH = (int) SCREEN.getHeight();
    /** The scale factor for the game window. */
    public static final double SCALE = 0.8;

    /** Maximum number of iterations when spawning random entities. */
    public static final int MAX_ITERATIONS_NUMBER = 200;
    /** Minimum number of obstacles per lane. */
    public static final int MIN_OBSTACLES_NUMBER = 2;
    /** Maximum number of obstacles per lane. */
    public static final int MAX_OBSTACLES_NUMBER = 3;
    /** Minimum number of eagles. */
    public static final int MIN_EAGLES_NUMBER = 5;
    /** Maximum number of eagles. */
    public static final int MAX_EAGLES_NUMBER = 6;

    /** Minimum car width (in grid units). */
    public static final int MIN_CAR_WIDTH = 1;
    /** Maximum car width (in grid units). */
    public static final int MAX_CAR_WIDTH = 2;

    /** Minimum trunk width (in grid units). */
    public static final int MIN_TRUNK_WIDTH = 1;
    /** Maximum trunk width (in grid units). */
    public static final int MAX_TRUNK_WIDTH = 2;

    /** Eagle width (in grid units). */
    public static final int EAGLE_WIDTH = 1;
    /** Eagle height (in grid units). */
    public static final int EAGLE_HEIGHT = 1;

    /** Minimum speed for moving objects. */
    public static final float MIN_SPEED = 0.008f;
    /** Maximum speed for moving objects. */
    public static final float MAX_SPEED = 0.025f;

    /** Minimum number of power-ups. */
    public static final int MIN_POWER_UP_NUMBER = 1;
    /** Maximum number of power-ups. */
    public static final int MAX_POWER_UP_NUMBER = 3;
    /** Threshold for extra life power-up (in percentage). */
    public static final int EXTRA_LIFE_THRESHOLD = 60;
    /** Duration for freeze power-up (in seconds). */
    public static final int FREEZE_DURATION = 3;
    /** Duration for double score power-up (in seconds). */
    public static final int DOUBLE_SCORE_DURATION = 5;
    /** Minimum number of coin. */
    public static final int MIN_COIN_NUMBER = 4;
    /** Maximum number of coin. */
    public static final int MAX_COIN_NUMBER = 7;
    /** Minimum value for a coin. */
    public static final int COIN_LOW_VALUE = 1;
    /** Maximum value for a coin. */
    public static final int COIN_HIGH_VALUE = 5;
    /** Power-up width (in grid units). */
    public static final int PICKABLE_OBJECT_WIDTH = 1;
    /** Power-up height (in grid units). */
    public static final int PICKABLE_OBJECT_HEIGHT = 1;


    /** Height of a generic object (in grid units). */
    public static final int OBJECT_HEIGHT = 1;

    /** Height of a lane (in grid units). */
    public static final int LANE_HEIGHT = 1;

    /** Number of road lanes. */
    public static final int ROAD_LANES = 5;
    /** Number of river lanes. */
    public static final int RIVER_LANES = 5;
    /** Number of ground lanes. */
    public static final int GROUND_LANES = 3;

    /** Maximum X coordinate in the grid. */
    public static final int MAX_X = 6;
    /** Minimum X coordinate in the grid. */
    public static final int MIN_X = -7;
    /** Maximum Y coordinate in the grid. */
    public static final int MAX_Y = RIVER_LANES + 1;
    /** Minimum Y coordinate in the grid. */
    public static final int MIN_Y = -(ROAD_LANES + 1);

    /** Total number of rows in the game grid. */
    public static final int N_ROW = ROAD_LANES + RIVER_LANES + GROUND_LANES;
    /** Total number of columns in the game grid. */
    public static final int N_COLUMN = Math.abs(MAX_X) + Math.abs(MIN_X) + 1;

    /** Height of the game frame in pixels. */
    public static final int FRAME_HEIGHT = (int) (SH * SCALE);
    /** Height of a single block in pixels. */
    public static final int BLOCK_HEIGHT = FRAME_HEIGHT / N_ROW;
    /** Width of the game frame in pixels. */
    public static final int FRAME_WIDTH = BLOCK_HEIGHT * N_COLUMN;
    /** Width of a single block in pixels. */
    public static final int BLOCK_WIDTH = FRAME_WIDTH / N_COLUMN;

    /** Player width (in grid units). */
    public static final int PLAYER_WIDTH = 1;
    /** Player height (in grid units). */
    public static final int PLAYER_HEIGHT = 1;

    /** Points awarded per lane crossed. */
    public static final int POINT_PER_LANE = 10;
    /** Points awarded for completing a level. */
    public static final int POINT_LEVEL_COMPLETED = 100;

    /** Default button width in pixels (temporary). */
    public static final int BUTTON_WIDTH_DEFAULT = 166;
    /** Default button height in pixels (temporary). */
    public static final int BUTTON_HEIGHT_DEFAULT = 61;
    /** Button width in pixels (temporary, may be scaled). */
    public static final int BUTTON_WIDTH = BUTTON_WIDTH_DEFAULT * 1;
    /** Button height in pixels (temporary, may be scaled). */
    public static final int BUTTON_HEIGHT = BUTTON_HEIGHT_DEFAULT * 1;
    /** Distance between buttons in pixels. */
    public static final int BUTTONS_DISTANCE = 10;

    /**Setting the pause menu in grid units. */
    public static final double PAUSE_MENU_X_GRID_UNITS = -3.5;
    /**Setting the pause menu in grid units. */
    public static final double PAUSE_MENU_Y_GRID_UNITS = 3;

    /** Button width in pixels for shop and menu. */
    public static final int BUTTON_WIDTH_IN_PIXEL = 100;
    /** Button height in pixels for shop and menu. */
    public static final int BUTTON_HEIGHT_IN_PIXEL = 30;

    /**
     * Private constructor to prevent instantiation.
     */
    private Constants() {
        throw new UnsupportedOperationException("Utility class");
    }
}
