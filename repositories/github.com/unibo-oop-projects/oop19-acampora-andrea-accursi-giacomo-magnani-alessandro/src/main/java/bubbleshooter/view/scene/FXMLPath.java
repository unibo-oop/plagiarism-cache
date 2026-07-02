package bubbleshooter.view.scene;

/**
 * Enumeration which contains all the controllers of the scenes.
 *
 */
public enum FXMLPath {

    /**
     * The main scene.
     */
    MAIN("/view/scenes/main.fxml"),

    /**
     * The game scene.
     */
    GAME("/view/scenes/game.fxml"),

    /**
     * The high score scene.
     */
    HIGHSCORE("/view/scenes/highscores.fxml"),

    /**
     * The game over scene.
     */
    GAMEOVER("/view/scenes/gameover.fxml"),

    /**
     * The pause scene.
     */
    PAUSE("/view/scenes/pause.fxml");

    private final String scene;

    /**
     * @param scene The path of the scene.
     */
    FXMLPath(final String scene) {
        this.scene = scene;
    }

    /**
     * @return the path of the scene.
     */
    public String getPath() {
        return this.scene;
    }
}
