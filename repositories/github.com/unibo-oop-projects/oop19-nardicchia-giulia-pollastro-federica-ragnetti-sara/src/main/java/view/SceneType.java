package view;

/**
 * Enumeration for the possible scene.
 *
 */
public enum SceneType {

    /**
     * The login and sign up scene.
     * 
     */
    LOGIN("Login"),

    /**
     * The main menu scene.
     * 
     */
    MAIN_MENU("MainMenu"),

    /**
     * The scene to select the training area.
     * 
     */
    TRAINING_AREA("TrainingAreaSelector"),

    /**
     * The scene for visualize user progress.
     * 
     */
    STATISTICS("Statistics"),

    /**
     * The scene to select the minigame.
     * 
     */
    GAME_SELECTOR("MiniGameSelector"),

    /**
     * The scene to learn how to play.
     * 
     */
    HOW_TO_PLAY("HowToPlay"),

    /**
     * The result scene of the minigame.
     * 
     */
    END_GAME("EndGame");

    private static final String PATH = "/layouts/";
    private static final String PATH_END = ".fxml";
    private String fileName;

    SceneType(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Getter for the file path.
     * @return the path of the xml file of the current scene
     */
    public String getFilePath() {
        return PATH + fileName + PATH_END;
    }

}
