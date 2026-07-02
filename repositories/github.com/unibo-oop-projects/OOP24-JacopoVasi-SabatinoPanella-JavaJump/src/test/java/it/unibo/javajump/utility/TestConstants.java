package it.unibo.javajump.utility;

/**
 * The type Test constants.
 */
public final class TestConstants {

    /**
     * The constant KEY_EVENT_MODIFIER.
     */
    public static final int KEY_EVENT_MODIFIER = 0;

    /**
     * The constant DELTA_TIME.
     */
    public static final float DELTA_TIME = 0.01f;
    /**
     * The constant DIV_TO_CENTER.
     */
    public static final int DIV_TO_CENTER = 2;
    /**
     * The constant SCREEN_WIDTH.
     */
    public static final int SCREEN_WIDTH = 800;
    /**
     * The constant SCREEN_HEIGHT.
     */
    public static final int SCREEN_HEIGHT = 600;
    /**
     * The constant COUNTER_START.
     */
    public static final int COUNTER_START = 0;
    /**
     * The constant STARTING_SCORE.
     */
    public static final int STARTING_SCORE = 0;
    /**
     * The constant SCORE_POINTS.
     */
    public static final int SCORE_POINTS = 50;
    /**
     * The constant MAX_COUNT_PHYSICS.
     */
    public static final int MAX_COUNT_PHYSICS = 6;
    /**
     * The constant MAX_COUNT_PACMAN.
     */
    public static final int MAX_COUNT_PACMAN = 100;
    /**
     * The constant MAX_COUNT_JUMPING.
     */
    public static final int MAX_COUNT_JUMPING = 200;
    /**
     * The constant MAX_COUNT_PLATFORM.
     */
    public static final int MAX_COUNT_PLATFORM = 200;
    /**
     * The constant X_LEFT_SIDE_SCREEN.
     */
    public static final int X_LEFT_SIDE_SCREEN = 0;
    /**
     * The constant HEIGHT_OFF_MULTIPLIER.
     */
    public static final int HEIGHT_OFF_MULTIPLIER = 2;
    /**
     * The constant PLATFORM_OFFSET.
     */
    public static final int PLATFORM_OFFSET = 100;
    /**
     * The constant CAMERA_INCREASING_OFFSET.
     */
    public static final int CAMERA_INCREASING_OFFSET = 150;
    /**
     * The constant CAMERA_DECREASING_OFFSET.
     */
    public static final int CAMERA_DECREASING_OFFSET = 100;

    /**
     * Private constructor for Constant utility class.
     *
     * @throws AssertionError the error if instantiated
     */
    private TestConstants() {
        throw new AssertionError("This is a utility class, it should not be instantiated!");
    }
}
