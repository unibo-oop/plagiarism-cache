package model.utility;

/**
 * Proportion Utility class.
 */
public final class ProportionUtility {

    private static final double PROPORTION_WALL = 15;
    private static final double PROPORTION_WALL_RANGE_SIDE = 30;
    private static final double PROPORTION_WALL_RANGE_TOPDOWN = 40;
    private static final double PROPORTION_BUTTON = 40;
    private static final double PROPORTION_DOOR_WIDTH = 15;
    private static final double PROPORTION_DOOR_HEIGHT = 90;
    private static final double PROPORTION_ITEMSHOP = 12;
    private static final double HEIGHT = 600;
    private static final double WIDTH = 1100;
    private static final double WALLVERTICALWIDTH = 15;
    private static final double WALLHORIZONTALWIDTH = 30;
    private static final double WALLVERTICALLHEIGHT = 30;
    private static final double WALLHORIZONTALHEIGHT = 15;
    private static final double POWER_UP_HEIGHT = 110;
    private static final double POWER_UP_WIDTH = 90;
    private static final double UNDER_SHOP_Y = 80;
    private static final double UNDER_SHOP_X = 100;

    private ProportionUtility() { }

    /**
     * @return power up height.
     */
    public static double getPowerUpHeight() {
        return ProportionUtility.POWER_UP_HEIGHT;
    }

    /**
     * @return under shop y (80).
     */
    public static double getUnderShopY() {
        return ProportionUtility.UNDER_SHOP_Y;
    }

    /**
     * @return under shop x (100).
     */
    public static double getUnderShopX() {
        return ProportionUtility.UNDER_SHOP_X;
    }

    /**
     * @return power up width.
     */
    public static double getPowerUpWidth() {
        return ProportionUtility.POWER_UP_WIDTH;
    }

    /**
     * @return wall vertical width (15).
     */
    public static double getWallVerticalWidth() {
        return ProportionUtility.WALLVERTICALWIDTH;
    }

    /**
     * @return wall vertical height (30).
     */
    public static double getWallVerticalHeight() {
        return ProportionUtility.WALLVERTICALLHEIGHT;
    }

    /**
     * @return wall horizontal width (30).
     */
    public static double getWallHorizontalWidth() {
        return ProportionUtility.WALLHORIZONTALWIDTH;
    }

    /**
     * @return wall horizontal height (15).
     */
    public static double getWallHorizontalHeight() {
        return ProportionUtility.WALLHORIZONTALHEIGHT;
    }

    /**
     * @return wall range side (30).
     */
    public static double getWallRangeSide() {
        return ProportionUtility.PROPORTION_WALL_RANGE_SIDE;
    }

    /**
     * @return wall range top down (40).
     */
    public static double getWallRangeTopdown() {
        return ProportionUtility.PROPORTION_WALL_RANGE_TOPDOWN;
    }

    /**
     * @return get radius button.
     */
    public static double getRadiusButton() {
        return ProportionUtility.HEIGHT / ProportionUtility.PROPORTION_BUTTON;
    }

    /**
     * @return get the height of the door (90).
     */
    public static double getHeightDoor() {
        return ProportionUtility.PROPORTION_DOOR_HEIGHT;
    }

    /**
     * @return get the width of the door (15).
     */
    public static double getWidthDoor() {
        return ProportionUtility.PROPORTION_DOOR_WIDTH;
    }

    /**
     * @return get item shop radius.
     */
    public static double getRadiusItemShop() {
        return ProportionUtility.HEIGHT / ProportionUtility.PROPORTION_ITEMSHOP;
    }

    /**
     * @return the width or height of the wall. The dimension is based on the height
     *         or the width of the wall (15).
     */
    public static double getWallStandard() {
        return ProportionUtility.PROPORTION_WALL;
    }

    /**
     * @return width of the room minus the dimension of the walls. So it return the
     *         actual dimension of the playable room (1070).
     */
    public static double getWidth() {
        return ProportionUtility.WIDTH - getWallStandard() * 2;
    }

    /**
     * @return height of the room minus the dimension of the walls. So it return the
     *         actual dimension of the playable room (570).
     */
    public static double getHeight() {
        return ProportionUtility.HEIGHT - getWallStandard() * 2;
    }
}
