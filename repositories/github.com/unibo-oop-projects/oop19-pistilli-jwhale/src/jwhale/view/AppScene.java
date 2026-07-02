package jwhale.view;
/**
 * Scenes fxml path.
 */
public enum AppScene {
    /**
     * Main scene fxml.
     */
    MAIN_SCENE("MainScene"),
    /**
     * Env scene fxml.
     */
    ENV_WORK("EnvScene"),
    /**
     * Add endpoint scene.
     */
    ADD_ENDPOINT("AddEndPointScene");

    private static final String PATH = "scenes/";
    private static final String EXT = ".fxml";
    private final String fileName;

    AppScene(final String fileName) {
        this.fileName = fileName;
    }
    /**
     * Get relative fxml path.
     * @return
     *  relative fxml path.
     */
    public String getScenePath() {
        return PATH + fileName + EXT;
    }
}
