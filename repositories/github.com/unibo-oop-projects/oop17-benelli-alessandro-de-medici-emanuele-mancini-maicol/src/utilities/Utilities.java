package utilities;

import java.awt.Toolkit;

/**
 * Class that stores useful values.
 *
 */
public final class Utilities {

    /**
     * Scaling factor for window's size.
     */
    public static final double SCALE_FACTOR = 0.8;

    /**
     * Window's scaled width.
     */
    public static final double W = Toolkit.getDefaultToolkit().getScreenSize().getHeight() * SCALE_FACTOR;

    /**
     * Window's scaled height.
     */
    public static final double H = Toolkit.getDefaultToolkit().getScreenSize().getHeight() * SCALE_FACTOR;

    /**
     * Value of music volume.
     */
    public static final double MUSIC_VOLUME = 0.05;

    /**
     * Value of how much effects' volume is higher than the music one.
     */
    public static final double EFFECT_VOLUME_DELTA = 0.05;

    /**
     * Value of how much the player moves.
     */
    public static final int PLAYER_MOVEMENT_DELTA = 1;

    /**
     * Value of player's animation's duration when it moves on x axis.
     */
    public static final int PLAYER_ANIMATION_DURATION_X = 400;

    /**
     * Value of player's animation's duration when it moves on y axis.
     */
    public static final int PLAYER_ANIMATION_DURATION_Y = 600;

    /**
     * Value of the first level.
     */
    public static final int FIRST_LEVEL = 1;

    /**
     * Value of total levels.
     */
    public static final int TOTAL_LEVELS = 5;

    private Utilities() {
    }
}
