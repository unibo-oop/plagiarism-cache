package it.unibo.modularcheckers.util;

import java.net.URL;

/**
 * Contains the files name, used in Serialization to recover configuration
 * files.
 */
public final class ConfPathHelper {

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    private static final String BASE_DIR = "gameData";
    private static final String CHECKERS_SERIALIZED = "checkersBoard";
    private static final String COLORS_SERIALIZED = "colors";
    private static final String FILE_EXTENSION = ".ser";

    private ConfPathHelper() {

    }

    /**
     * Returns the URL for the Serialized Colors resource.
     *
     * @return URL pointing the resource.
     */
    public static URL getColorsPath() {
        return ConfPathHelper.class.getResource(FILE_SEPARATOR + BASE_DIR + FILE_SEPARATOR
                + COLORS_SERIALIZED + FILE_EXTENSION);
    }

    /**
     * Returns the URL for the Serialized Checkers base chessboard resource.
     *
     * @return URL pointing the resource.
     */
    public static URL getCheckersPath() {
        return ConfPathHelper.class.getResource(FILE_SEPARATOR + BASE_DIR + FILE_SEPARATOR
                + CHECKERS_SERIALIZED + FILE_EXTENSION);
    }

}
