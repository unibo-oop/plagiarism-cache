package com.geoquiz.utility;

/**
 * Class who get right path to save GeoQuiz game.
 */
public final class LocalFolder {

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    private LocalFolder() {
    }

    /**
     * @return right path to save GeoQuizGame.
     */
    public static String getLocalFolderPath() {
        return System.getProperty("user.home") + FILE_SEPARATOR + ".GeoQuiz";
    }

    /**
     * @return separator for right path.
     */
    public static String getFileSeparator() {
        return FILE_SEPARATOR;
    }
}
