package utilities;

/**
 * 
 */
public enum EnumString {

    /**
     *
     */
    FONT("Verdana"),

    /**
     *
     */
    RESOURCE_FOLDER("src/main/resources/"),

    /**
     *
     */
    IMAGE_FOLDER(RESOURCE_FOLDER.getValue() + "images/"),

    /**
     *
     */
    SOUND_FOLDER(RESOURCE_FOLDER.getValue() + "sounds/"),

    /**
     *
     */
    BULLET_PATH(IMAGE_FOLDER.getValue() + "bullet.png"),

    /**
     *
     */
    BACKGROUND_PATH(IMAGE_FOLDER.getValue() + "skybox13.jpg"),

    /**
     *
     */
    PLAYER_PATH(IMAGE_FOLDER.getValue() + "playerShip.png"),

    /**
     *
     */
    ENEMY_PATH(IMAGE_FOLDER.getValue() + "enemy.png");

    private final String value;

    /**
     * Constructor.
     *
     * @param value string form of the path to file
     */
    EnumString(final String value) {
        this.value = value;
    }

    /**
     * @return value.
     */
    public String getValue() {
        return this.value;
    }

}
