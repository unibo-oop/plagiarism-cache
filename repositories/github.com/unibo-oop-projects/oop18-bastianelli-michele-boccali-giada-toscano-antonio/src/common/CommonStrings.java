package common;

/**
 * Common static strings.
 */
public final class CommonStrings {

    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 800;
    public static final double SCALING_FACTOR = 1.0;

    public static final char FIELD_SEPARATOR = ';';
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String LOGIN_FILE = System.getProperty("user.dir") + FILE_SEPARATOR + "PlayersData.txt";
    public static final String MUSIC_PATH = "resources" + FILE_SEPARATOR + "music" + FILE_SEPARATOR;

    public static final String KEY = "JumpManiaProject";

    private CommonStrings() {
    }

}
