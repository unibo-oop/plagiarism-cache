package it.unibo.oop.utilities;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * System informations about the computer locations and the screen properties.
 */
public final class Settings {

    /**
     * Home directory.
     */
    public static final String HOME_FOLDER = System.getProperty("user.home");

    /**
     * File separator based on the Operating System.
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * Screen sized based on the hardware.
     */
    public static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Shortcut to Width
     */
    public static final int SCREEN_WIDTH = (int) SCREEN_DIMENSION.getWidth();

    /**
     * Shortcut to Height
     */
    public static final int SCREEN_HEIGHT = (int) SCREEN_DIMENSION.getHeight();

    /**
     * Menu fixed dimension.
     */
    public static final Dimension MENU_DIMENSION = new Dimension((int) (SCREEN_WIDTH / 2), (int) (SCREEN_HEIGHT / 1.5));
    /**
     * Folder for the saves and highscores
     */
    public static final String MY_FOLDER = HOME_FOLDER + FILE_SEPARATOR + ".johnny2d" + FILE_SEPARATOR;

    /**
     * Highscores folder
     */
    public static final String HIGHSCORE_FOLDER = MY_FOLDER + FILE_SEPARATOR;
    public static final String HIGHSCORE_FILE = "highscores.bin";

    private Settings() {
    };
}
