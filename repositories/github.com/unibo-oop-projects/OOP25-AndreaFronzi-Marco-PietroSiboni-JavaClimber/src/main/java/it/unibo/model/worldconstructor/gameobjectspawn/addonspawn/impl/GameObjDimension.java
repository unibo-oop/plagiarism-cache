package it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl;

/**
 * This class contains the dimensions of the game objects.
 */
public final class GameObjDimension {

    public static final double ALIEN_WIDTH = 100;
    public static final double ALIEN_HEIGHT = 100;

    public static final double COIN_WIDTH = 20;
    public static final double COIN_HEIGHT = 20;

    public static final double ENEMY_WIDTH = 30;
    public static final double ENEMY_HEIGHT = 30;

    public static final double ELYCAP_WIDTH = 30;
    public static final double ELYCAP_HEIGHT = 30;

    public static final double NULL_ALIEN_SPEED = 0;
    public static final double LEFT_ALIEN_SPEED_X = -400.0;
    public static final double RIGHT_ALIEN_SPEED_X = 400.0;
    public static final double JUMP_ALIEN_SPEED_Y = -1000.0;

    public static final double GRAVITY = 1300.0;
    public static final double EASY_PLATFORM_WIDTH = 100;
    public static final double EASY_PLATFORM_HEIGHT = 20;

    public static final double MEDIUM_PLATFORM_WIDTH = 100;
    public static final double MEDIUM_PLATFORM_HEIGHT = 20;

    public static final double HARD_PLATFORM_WIDTH = 30;
    public static final double HARD_PLATFORM_HEIGHT = 10;

    private GameObjDimension() {
    }
}
