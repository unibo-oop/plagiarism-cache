package controller.users;

import java.io.File;

/**
 * Contains constants used to find files saved on the user home directory.
 * It is responsible of checking if the install directory exist and when necessary creates it.
 * 
 */
public final class InstallManager {

    /**
     * Represents the specific separator type according to OS used.
     */
    public static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * Contains the route path of the user.
     */
    public static final String SYS_PATH = System.getProperty("user.home");

    /**
     * Represents the application directory name to create.
     */
    public static final String DIR_NAME = "battleship";


    /**
     * Contains the path corresponding to the app's main directory.
     */
    public static final String DIR_PATH = SYS_PATH 
            + SEPARATOR 
            + DIR_NAME;

    /**
     * Contains the path corresponding to users list file.
     */
    public static final String FILE_PATH = DIR_PATH 
            + SEPARATOR;

    private InstallManager() {
        // can not create a InstallManager object.
    }

    /**
     * Sets up the application by creating the app directory.
     */
    public static void setupApplication() {

        final File usersDir = new File(DIR_PATH);

        if (!usersDir.exists()) {
            try {
                usersDir.mkdir();
            } catch (Exception e) {
                System.err.println("Error in directory creation: dir not created");
                e.printStackTrace();
            }
        }
    }

}
