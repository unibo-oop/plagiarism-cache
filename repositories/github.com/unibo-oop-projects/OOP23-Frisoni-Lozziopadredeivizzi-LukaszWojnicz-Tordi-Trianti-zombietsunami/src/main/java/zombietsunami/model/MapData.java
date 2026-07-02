
package zombietsunami.model;

/**
 * This an Utility class, used for store the informations about the screen
 * dimensions, world dimensions and FPS of the game. Its role is to give those
 * informations at the elements who needs them.
 */
public final class MapData {

    // SCREEN SETTINGS
    private static final int ORIGINAL_TITLE_SIZE = 16;
    private static final int SCALE = 3;
    private static final int TITLE_SIZE = ORIGINAL_TITLE_SIZE * SCALE;

    private static final int MAX_SCREEN_COL = 16;
    private static final int MAX_SCREEN_ROW = 9;
    private static final int SCREEN_WIDTH = TITLE_SIZE * MAX_SCREEN_COL;
    private static final int SCREEN_HIGHT = TITLE_SIZE * MAX_SCREEN_ROW;

    // FPS
    private static final int FPS = 60;

    // WORLD SETTINGS
    private static final int MAX_WORLD_COL = 136;
    private static final int MAX_WORLD_ROW = 15;
    private static final int WORLD_WIDTH = TITLE_SIZE * MAX_WORLD_COL;
    private static final int WORLD_HIGHT = TITLE_SIZE * MAX_WORLD_ROW;

    private MapData() {
    }

    /**
     * @return the screen's cols
     */
    public static int getMaxScCol() {
        return MAX_SCREEN_COL;
    }

    /**
     * @return the screen's rows
     */
    public static int getMaxScRow() {
        return MAX_SCREEN_ROW;
    }

    /**
     * @return the title's size
     */
    public static int getTitSize() {
        return TITLE_SIZE;
    }

    /**
     * @return the FPS' value
     */
    public static int getFPS() {
        return FPS;
    }

    /**
     * @return the screen width
     */
    public static int getScreenW() {
        return SCREEN_WIDTH;
    }

    /**
     * @return the screen height
     */
    public static int getScreenH() {
        return SCREEN_HIGHT;
    }

    /**
     * @return the map's (world) cols
     */
    public static int getMaxWorldCol() {
        return MAX_WORLD_COL;
    }

    /**
     * @return the map's (world) rows
     */
    public static int getMaxWorldRow() {
        return MAX_WORLD_ROW;
    }

    /**
     * @return the map (world) width
     */
    public static int getWorldWidth() {
        return WORLD_WIDTH;
    }

    /**
     * @return the map (world) height
     */
    public static int getWorldHeight() {
        return WORLD_HIGHT;
    }

}
