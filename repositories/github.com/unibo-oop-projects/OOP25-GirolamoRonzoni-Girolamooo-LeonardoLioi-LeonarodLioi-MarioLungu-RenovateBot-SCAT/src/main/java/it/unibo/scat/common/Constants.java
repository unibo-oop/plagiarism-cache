package it.unibo.scat.common;

/**
 * Utility class containing global application constants.
 */
public final class Constants {
    // PATHS
    public static final String LEADERBOARD_PATH = "data/leaderboard.txt";
    public static final String ENTITIES_PATH = "data/entities.txt";
    public static final String RESOURCE_PATH = "/data/leaderboard.txt";

    // WORLD
    public static final int BORDER_RIGHT = 60;
    public static final int BORDER_BOTTOM = 40;
    public static final int BORDER_LEFT = 1;
    public static final int BORDER_UP = 1;

    public static final int INVADER_BOTTOM_LIMIT = 31;

    // POINTS
    public static final int POINTS_INVADER1 = 10;
    public static final int POINTS_INVADER2 = 20;
    public static final int POINTS_INVADER3 = 30;
    public static final int POINTS_BONUS_INVADER = 100;

    // GAMELOOP
    public static final int GAME_STEP_MS = 60;
    public static final int SHOT_STEP_MS = 60;
    public static final int BONUSINVADER_STEP_MS = 500;

    // COOLDOWNS
    public static final int PLAYER_SHOOTING_COOLDOWN = 200;

    // ENTITIES HEALTH
    public static final int BUNKER_HEALTH = 20;
    public static final int PLAYER_HEALTH = 3;
    public static final int INVADERS_HEALTH = 1;
    public static final int SHOT_HEALTH = 1;

    // ENTITIES DIMENSION
    public static final int BUNKER_WIDTH = 5;
    public static final int BUNKER_HEIGHT = 3;
    public static final int PLAYER_WIDTH = 3;
    public static final int PLAYER_HEIGHT = 3;
    public static final int INVADER_WIDTH = 2;
    public static final int INVADER_HEIGHT = 2;
    public static final int BONUS_INVADER_WIDTH = 3;
    public static final int BONUS_INVADER_HEIGHT = 2;
    public static final int SHOT_WIDTH = 1;
    public static final int SHOT_HEIGHT = 2;

    // UTIL
    public static final int ZERO = 0;

    /**
     * Private constructor to avoid initialization.
     */
    private Constants() {
    }
}
