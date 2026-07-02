package spacesurvival.utilities.dimension;

import spacesurvival.utilities.SystemVariables;

/**
 *ScaleOf class for representing all objects with correct dimension.
 */
public final class ScaleOf {

    /**
     * Scale of space ship.
     */
    public static final int SPACESHIP = (int) (40 * SystemVariables.SCALE_X);

    /**
     * Scale of game object.
     */
    public static final int GAME_OBJECT = (int) (30 * SystemVariables.SCALE_X);

    /**
     * Scale of boss.
     */
    public static final int BOSS = (int) (40 * SystemVariables.SCALE_X);

    /**
     * Scale of the bullet object.
     */
    public static final int BULLET = (int) (3 * SystemVariables.SCALE_X);

    /**
     * Scale of the icon skin.
     */
    public static final int ICON_SKIN = 200;

    /**
     * Scale of the icon full.
     */
    public static final int ICON_FULL = 30;

    /**
     * Scale of the icon big.
     */
    public static final int ICON_BIG = 40;

    /**
     *  Scale of the icon medium.
     */
    public static final int ICON_MEDIUM = 45;

    /**
     *  Scale of the icon small.
     */
    public static final int ICON_SMALL = 80;

    /**
     * Scale of the help's icon singular.
     */
    public static final int ICON_HELP_SINGULAR = 30;

    /**
     * Scale of the help's icon plural.
     */
    public static final int ICON_HELP_PLURAL = 90;

    /**
     * Life bar width of the ship.
     */
    public static final int WIDTH_LIFEBAR_SHIP = 180;

    /**
     * Life bar height of the ship.
     */
    public static final int HEIGHT_LIFEBAR_SHIP = 25;

    /**
     * Life bar width of the boss.
     */
    public static final int WIDTH_LIFEBAR_BOSS = 320;

    /**
     * Life bar height of the boss.
     */
    public static final int HEIGHT_LIFEBAR_BOSS = 30;

    /**
     * Loading bar width.
     */
    public static final int WIDTH_BAR_LOADING = 900;
    /**
     * Loading bar height.
     */
    public static final int HEIGHT_BAR_LOADING = 50;

    private ScaleOf() {
    }

}
