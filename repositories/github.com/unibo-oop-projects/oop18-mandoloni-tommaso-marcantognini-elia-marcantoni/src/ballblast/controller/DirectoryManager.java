package ballblast.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import ballblast.controller.files.XMLFileManager;

/**
 * Class that contains constants used to find files saved on the user home
 * directory. It is responsible of checking if the install directory exist and
 * if necessary creates it.
 */
public final class DirectoryManager {

    /**
     * The file system separator.
     */
    public static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * Static field that contains the route path of the application.
     */
    public static final String APP_PATH = System.getProperty("user.home") + SEPARATOR + ".ballblast";

    /**
     * The path to the scoreboard directory.
     */
    public static final String SCOREBOARD_DIR = APP_PATH + SEPARATOR + "scoreboard";

    /**
     * The path to the users directory.
     */
    public static final String USERS_DIR = APP_PATH + SEPARATOR + "users";

    /**
     * Static field that contains the file of the users list.
     */
    public static final String USERS_LIST_FILE = USERS_DIR + SEPARATOR + "users_list.xml";

    /**
     * Static field that contains the file with the leaderboard of the users in the
     * survival mode.
     */
    public static final String SURVIVAL_FILE = SCOREBOARD_DIR + SEPARATOR + "survival.xml";

    private DirectoryManager() {
        // Cannot create a DirectroyManager object.
    }

    /**
     * Static method that get user file.
     * 
     * @param userName name of the user.
     * @return the file with the UserData.
     */
    public static String getUserFile(final String userName) {
        return USERS_DIR + SEPARATOR + userName + ".xml";
    }

    /**
     * Sets up the application creating app directories and utility files.
     */
    public static void setupApplication() {
        try {
            Files.createDirectories(Paths.get(SCOREBOARD_DIR));
            Files.createDirectories(Paths.get(USERS_DIR));
            if (!Files.exists(Paths.get(USERS_LIST_FILE))) {
                try {
                    XMLFileManager.createEmptyFile(USERS_LIST_FILE, "credentials");
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (TransformerException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
