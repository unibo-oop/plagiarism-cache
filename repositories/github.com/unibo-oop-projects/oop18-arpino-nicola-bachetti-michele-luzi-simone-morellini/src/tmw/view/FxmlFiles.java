package tmw.view;

/**
 * List of fxml files used in the game.
 */
public enum FxmlFiles {

    /**
     * menu scene.
     */
    MENU("MenuView.fxml"),

    /**
     * option scene.
     */
    OPTION("OptionView.fxml"),

    /**
     * select level scene.
     */
    SELECT_LEVEL("SelectLevelView.fxml"),

    /**
     * hud scene.
     */
    HUD("HudView.fxml"),

    /**
     * end level scene.
     */
    END_LEVEL("EndLevelView.fxml"),

    /**
     * gameOver scene.
     */
    GAME_OVER("GameOverView.fxml"),

    /**
     * credit scene.
     */
    CREDIT("CreditView.fxml");

    /**
     * it doesn't matter which operating system the application is launched on
     * (Windows, Linux, MacOS), the FXMLLoader requires char "/".
     */
    private static final String FXML_PATH = "/view/";

    private final String name;

    /**
     * @param name The name of the file
     */
    FxmlFiles(final String name) {
        this.name = name;
    }

    /**
     * @return the path of the file fxml, which includes the package and the file
     *         name
     */
    public String getFxmlPath() {
        return FXML_PATH + this.name;
    }
}

