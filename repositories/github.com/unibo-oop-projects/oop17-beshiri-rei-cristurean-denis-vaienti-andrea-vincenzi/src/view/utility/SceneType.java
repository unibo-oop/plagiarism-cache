package view.utility;

/**
 * Scenes of the game.
 * 
 */
public enum SceneType {

    /**
     * Start scene.
     */
    MENU("MainMenuView.fxml"),

    /**
     * Option scene.
     */
    OPTIONS("OptionsMenuView.fxml"),

    /**
     * Credits scene.
     */
    CREDITS("CreditsView.fxml"),

    /**
     * Help scene.
     */
    HELP("HelpView.fxml"),

    /**
     * Game main scene.
     */
    GAME("GameCanvasView.fxml"),

    /**
     * Pause scene.
     */
    PAUSE("PauseView.fxml"),

    /**
     * Leader-board scene.
     */
    LEADERBOARD("LeaderboardView.fxml"),

    /**
     * New game scene.
     */
    NEWGAME("NewGameView.fxml"),

    /**
     * Win scene.
     */
    WIN("WinView.fxml"),

    /**
     * Lost scene.
     */
    LOST("LostView.fxml");

    private String file;

    /**
     * Enumeration constructor.
     * 
     * @param file
     *            file name for fxml file that need to be loaded.
     */
    SceneType(final String file) {
        this.file = file;
    }

    /**
     * Get File name.
     * 
     * @return file name.
     */
    public String getFile() {
        return file;
    }
}
