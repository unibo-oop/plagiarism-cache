package controller;

/**
 * Static class for utils.
 */
public final class ControllerUtils {
    /**
     * This is file name of rules.
     */
    private static final String RULESNAME = "Rules.txt";
    /**
     * This is file name of the saves.
     */
    private static final String HIGHSCORE = "DSASaveRes";

    private ControllerUtils() {

    }

    /**
     * This method return the resource address of txt rules .
     * 
     * @return the txt rules address
     */
    public static String getRulesName() {
        return "/" + RULESNAME;

    }

    /**
     * This method return the resource address of txt highscore .
     * 
     * @return the txt rules address
     */
    public static String getHighScore() {
        return "/" + HIGHSCORE;

    }
}