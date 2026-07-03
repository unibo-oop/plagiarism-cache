package it.unibo.coinquify.file;

import java.io.File;
import java.util.stream.Stream;

/**
 * FilePathsConstants file paths.
 */
public final class FilePathsConstants {
    private static final String SEPARATOR = File.separator;
    /**
     * Directory path.
     */
    public static final String DIR_PATH = System.getProperty("user.home") + SEPARATOR + ".coinquify";
    /**
     * Room mate path.
     */
    public static final String ROOM_MATE = DIR_PATH + SEPARATOR + "roomMates.txt";
    /**
     * Events path.
     */
    public static final String EVENTS = DIR_PATH + SEPARATOR + "events.txt";
    /**
     * Phone book path.
     */
    public static final String PHONE_BOOK = DIR_PATH + SEPARATOR + "phoneBook.txt";
    /**
     * Shopping list path.
     */
    public static final String SHOPPING_LIST = DIR_PATH + SEPARATOR + "shoppingList.txt";
    /**
     * PostIt It path.
     */
    public static final String NOTICE_BOARD_POSTIT = DIR_PATH + SEPARATOR + "postIt.txt";
    /**
     * Rules path.
     */
    public static final String NOTICE_BOARD_RULES = DIR_PATH + SEPARATOR + "rules.txt";

    private FilePathsConstants() {
    }

    /**
     * @return files path as stream.
     */
    public static Stream<String> getFiles() {

        return Stream.of(ROOM_MATE, EVENTS, PHONE_BOOK, SHOPPING_LIST);

    }
}
