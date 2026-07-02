package model.utilities;

/**
 * Contains global information for setup the game.
 */
public final class GameUtilities {

    /**
     * System Separator.
     */
    private static final String SEP = System.getProperty("file.separator");

    /**
     * System User Home Path.
     */
    private static final String RES_PATH = System.getProperty("user.home");

    /**
     * Path for the leaderboard file.
     */
    public static final String LEADERBOARD_PATH = RES_PATH
                                                   + SEP
                                                   + ".BrickBreakerEvo" 
                                                   + SEP
                                                   + "Leaderboards"
                                                   + SEP
                                                   + "ranking.json"; 
    /**
     * Path for the settings file.
     */
    public static final String SETTINGS_PATH =  RES_PATH
                                                + SEP
                                                + ".BrickBreakerEvo" 
                                                + SEP
                                                + "Settings"
                                                + SEP
                                                + "settings.json";

    /**
     * Path for save level setting.
     */
    public static final String SETTINGS_LEVEL_PATH =  RES_PATH
                                                + SEP
                                                + ".BrickBreakerEvo" 
                                                + SEP
                                                + "Settings"
                                                + SEP
                                                + "settinglevel.json";

    /**
     * Used when player don't set the alias.
     */
    public static final String DEFAULT_PLAYER_NAME = "GUEST";

    /**
     * This number represent the max length for alias name.
     */
    public static final int MAX_ALIAS_LENGHT = 12;

    private GameUtilities() {

    }


}
