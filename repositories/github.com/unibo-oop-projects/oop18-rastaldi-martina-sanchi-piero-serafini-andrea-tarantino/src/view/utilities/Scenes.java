package view.utilities;

/**
 *
 * Chiara Tarantino.
 * Enum for the game scenes.
 *
 */
public enum Scenes {
    /**
     * To get StartMenu fxml file.
     */
    STARTMENU("StartMenu.fxml"),

    /**
     * To get ModalityMenu fxml file.
     */
    MODALITYMENU("ModalityMenu.fxml"),

    /**
     * To get SettingsMenu fxml file.
     */
    SETTINGSMENU("SettingsMenu.fxml"),

    /**
     * To get PlayersMenu fxml file.
     */
    PLAYERSMENU("PlayersMenu.fxml"),

    /**
     * To get Rulebook fxml file.
     */
    RULEBOOK("Rulebook.fxml"),

    /**
     * To get RulebookPage2 fxml file.
     */
    RULEBOOKPAGE2("RulebookPage2.fxml"),
    /**
     * To get Ranking fxml file.
     */
    RANKING("Ranking.fxml");

    private static final String SCENES_PATH = "/scenes/";
    private final String selectedScene;

    Scenes(final String sceneName) {
        this.selectedScene = sceneName;
    }

    /**
     *
     * @return the path to fxml file
     */
    public String getPath() {
        return Scenes.SCENES_PATH + this.selectedScene;
    }
}
