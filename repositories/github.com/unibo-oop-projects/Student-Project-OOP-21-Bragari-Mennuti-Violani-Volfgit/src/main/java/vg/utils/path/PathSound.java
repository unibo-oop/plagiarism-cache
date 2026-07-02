package vg.utils.path;

/**
 * Utility class for PathSound.
 */
public final class PathSound {
    private static final String PATH_RADICE = "sound/";
    /**
     * Provides the path of the sound of the background.
     */
    public static final String BACKGROUND_START = PATH_RADICE + "invasion.wav";
    /**
     * Provides the path of the sound of the mystery box (taken).
     */
    public static final String PICK_UP_BOX = PATH_RADICE + "box.wav";
    /**
     * Provides the path of the sound of the new board (taken).
     */
    public static final String CLOSE_BOARD = PATH_RADICE + "changing.wav";
    /**
     * Provides the path of the sound of the game over.
     */
    public static final String GAMEOVER = PATH_RADICE + "gameover.wav";
    private PathSound() {
    }
}
