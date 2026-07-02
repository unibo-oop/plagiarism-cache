package utility;

import java.io.File;

import factory.EnumFactory;
import level.Levels;

/**
 * Utility class of default parameters of the application.
 */
public final class Utils {

    private static final String FS = File.separator;
    private static final Levels DEFAULT_SPEED = Levels.LEVEL_1;
    private static final EnumFactory DEFAULT_CONTROLLER = EnumFactory.MENU;

    private Utils() {
    }

    /**
     * @return an enumeration that identifies the default start level of the game.
     */
    public static Levels getDefaultSpeed() {
        return Utils.DEFAULT_SPEED;
    }

    /**
     * @return an enumeration that identifies the default controller of the
     *         application.
     */
    public static EnumFactory getDefaultController() {
        return DEFAULT_CONTROLLER;
    }

    /**
     * @param folder the folder that will be create inside the 'res' folder.
     */
    public static void initFolderInRes(final String folder) {
        final File correctPath = new File("." + FS + "res" + FS + folder);
        correctPath.mkdirs();
    }
}
