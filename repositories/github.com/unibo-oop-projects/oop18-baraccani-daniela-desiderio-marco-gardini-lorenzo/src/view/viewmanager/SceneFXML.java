package view.viewmanager;

/**
 * Game's scenes.
 */
public enum SceneFXML {

    /**
     * MENU path.
     */
    MENU("menu.fxml"),

    /**
     * CHARACTER path.
     */
    CHARACTER("character.fxml"),

    /**
     * SETTINGS path.
     */
    SETTINGS("settings.fxml"),

    /**
     * SCOREBOARD path.
     */
    SCOREBOARD("scoreboard.fxml"),

    /**
     * CREDITS path.
     */
    CREDITS("credits.fxml"),

    /**
     * STORY path.
     */
    STORY("story.fxml"),

    /**
     * GAME path.
     */
    GAME("game.fxml"),

    /**
     * PAUSE path.
     */
    PAUSE("pause.fxml"),

    /**
     * VICTORY path.
     */
    VICTORY("victory.fxml"),

    /**
     * GAMEOVER path.
     */
    GAMEOVER("gameOver.fxml");

    private static final String FOLDER = "scenes/";
    private final String filePath;

    SceneFXML(final String path) {
        this.filePath = path;
    }

    /**
     * @return the file path of the specific scene.
     */
    public String getFilePath() {
        return FOLDER + this.filePath;
    }

}
