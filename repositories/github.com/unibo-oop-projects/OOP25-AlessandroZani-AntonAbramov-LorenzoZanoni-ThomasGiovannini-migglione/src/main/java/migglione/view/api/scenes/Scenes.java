package migglione.view.api.scenes;

/**
 * Enum used to store the names of the different scenes used.
 */
public enum Scenes {
    MENU("MENU"),
    START_GAME("START GAME"),
    FIELD("FIELD"),
    TUTORIAL("TUTORIAL"),
    SCORES("SCORES"),
    GALLERY("GALLERY"),
    CREDITS("CREDITS");

    private final String sceneName;

    Scenes(final String sceneName) {
        this.sceneName = sceneName;
    }

    /**
     * Simple getter.
     * 
     * @return the name of the scene.
     */
    public String getScene() {
        return this.sceneName;
    }
}
