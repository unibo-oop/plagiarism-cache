package view.utilities;

/**
 * 
 * Enumeration that contains the Game Scenes.
 * 
 *
 */
public enum Screens {
    /**
     * The Game scene.
     */
    GAME("Game.fxml"),
    /**
     * The Pause scene.
     */
    PAUSE("Pause.fxml"),
    /**
     * The game mode selection scene.
     */
    SELECTMODE("ModeSelection.fxml"),
    /**
     * The Main Menu scene.
     */
    MENU("Menu.fxml"),
    /**
     * The Settings scene.
     */
    SETTINGS("Settings.fxml"),
    /**
     * The Manual scene.
     */
    MANUAL("Manual.fxml"),
    /**
     * The Achievements scene.
     */
    ACHIEVEMENTS("Achievements.fxml"),
    /**
     * The High scores scene.
     */
    HIGH_SCORES("HighScores.fxml"),
    /**
     * The Game Over scene.
     */
    GAME_OVER("GameOver.fxml");

    private static final String SCENES_PATH = "/view/scenes/";
    private final String selectedSceneName;

    Screens(final String sceneName) {
        this.selectedSceneName = sceneName;
    }

    /**
     * Provides the scenes path.
     * 
     * @return the selected scene path as string.
     */
    public String getPath() {
        return Screens.SCENES_PATH + this.selectedSceneName;
    }
}
