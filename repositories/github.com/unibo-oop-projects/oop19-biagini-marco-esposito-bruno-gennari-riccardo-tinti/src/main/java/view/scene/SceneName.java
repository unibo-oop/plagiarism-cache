package view.scene;

/**
 * This enum models the viewable Scenes of the app.
 */
public enum SceneName {
    // New scenes can be added here
    // Scene layoutName should be the fxml layout file's name, excluding the extension (ex. mainMenu.fxml -> mainMenu)

    /**
     * Main menu.
     */
    MAIN("mainMenu"),

    /**
     * Choose settings before starting a new match.
     */
    MATCH_SETTINGS("matchSettings"),

    /**
     * Create or delete player profiles.
     */
    PROFILE("profile"),

    /**
     * View player statistics.
     */
    STATISTICS("statistics"),

    /**
     * Position your ships.
     */
    SHIP_DEPLOYMENT("shipDeployment"),

    /**
     * Main view of the battlefield in a match.
     */
    MATCH_BATTLE("matchBattle");

    private final String layoutName;

    SceneName(final String layoutName) {
        this.layoutName = layoutName;
    }

    /**
     * @return The layout's name
     */
    public String getLayoutName() {
        return layoutName;
    }
}
