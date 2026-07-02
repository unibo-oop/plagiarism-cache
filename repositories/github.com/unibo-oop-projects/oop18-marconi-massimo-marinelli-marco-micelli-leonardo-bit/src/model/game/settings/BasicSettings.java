package model.game.settings;

/**
 * This Class models a BasicSettings object. 
 * The duty of this object is to store the basic game settings informations.
 */
public final class BasicSettings {

    private static final String GAME_TITLE = "BIT DUNGEON";
    private static final String GAME_VERSION = "1.0";
    private static final int WINDOW_HEIGHT = 64 * 12;
    private static final int WINDOW_WIDTH = 64 * 20;

    /**
     * 
     * @return The game title
     */
    public String getGameTitle() {
        return GAME_TITLE;
    }

    /**
     * 
     * @return The game version
     */
    public String getGameVersion() {
        return GAME_VERSION;
    }

    /**
     * 
     * @return The window height
     */
    public Integer getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    /**
     * 
     * @return The window width
     */
    public Integer getWindowWidth() {
        return WINDOW_WIDTH;
    }
}
