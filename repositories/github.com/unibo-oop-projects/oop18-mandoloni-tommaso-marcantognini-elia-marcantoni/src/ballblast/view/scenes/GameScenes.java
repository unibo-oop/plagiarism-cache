package ballblast.view.scenes;

/**
 * 
 * Enumeration that contains the game scenes.
 */
public enum GameScenes {
    /**
     * The game scene.
     */
    GAME("/view/scenes/Game.fxml"),
    /**
     * The game mode selection menu scene.
     */
    GAME_MODE("/view/scenes/GameSelection.fxml"),
    /**
     * The main menu scene.
     */
    MENU("/view/scenes/Menu.fxml"),
    /**
     * The settings scene.
     */
    SETTINGS("/view/scenes/Settings.fxml"),
    /**
     * The leader board scene.
     */
    LEADERBOARD("/view/scenes/Leaderboard.fxml"),
    /**
     * The manual scene.
     */
    MANUAL("/view/scenes/Manual.fxml"),
    /**
     * The game over scene.
     */
    GAMEOVER("/view/scenes/Gameover.fxml"),
    /**
     * The login scene.
     */
    LOGIN("/view/scenes/Login.fxml"),
    /**
     * The initial scene.
     */
    MAIN("/view/scenes/Main.fxml");

    private final String selectedScene;

    /**
     * 
     * @param scene the scene.
     */
    GameScenes(final String scene) {
        this.selectedScene = scene;
    }

    /**
     * 
     * @return the Path where the scene is stored.
     */
    public String getPath() {
        return this.selectedScene;
    }
}
