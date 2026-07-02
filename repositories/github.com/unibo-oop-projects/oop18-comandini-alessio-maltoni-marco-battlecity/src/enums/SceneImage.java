package enums;

/**
 * This enumeration identifies a scene image stored on the disk and its path.
 */
public enum SceneImage {

    /**
     * The image of a black arrow with up direction.
     */
    ARROW_BLACK("arrow_black.png"),
    /**
     * The image of a white arrow with up direction.
     */
    ARROW_WHITE("arrow_white.png"),
    /**
     * The image of the enemy icon to be spawned.
     */
    ENEMY_ICON("enemy_icon.png"),
    /**
     * The image of the flag that indicates the current stage.
     */
    FLAG_STAGE("flag_stage.png"),
    /**
     * The image of the text: "GAME OVER".
     */
    GAME_OVER("game_over.png"),
    /**
     * The image the lives left.
     */
    LIVES_ICON("lives_icon.png"),
    /**
     * The image of the Main Title: "BATTLE CITY".
     */
    MAIN_TITLE("main_title.png"),
    /**
     * The image of the main player tank. It is yellow and UP-directed.
     */
    PLAYER_ICON("player_icon.png"),
    /**
     * The image of the main player tank moved. It is yellow and UP-directed.
     */
    PLAYER_ICON_2("player_icon_2.png"),
    /**
     * The image of the white bar for total.
     */
    WHITE_BAR("white_bar.png");

    // The path of the scene image on disk.
    private String path;
    // The directory which the scene image is stored.
    private static final String DIR = "scene_images";

    /*
     * Constructor of the enumeration.
     * 
     * @param sceneImageName the name which the scene image is stored on the disk
     *                       with extension.
     */
    SceneImage(final String sceneImageName) {
        path = "/" + DIR + "/" + sceneImageName;
    }

    /**
     * Getter method of the path of the scene image.
     * 
     * @return the path of the scene image on disk.
     */
    public String getPath() {
        return path;
    }
}
