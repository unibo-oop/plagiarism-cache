package user.enums;

/**
 * This enum represents a list of room names that are passed as argument.
 */
public enum RoomsNames {

    /**
     * constant representing the name for the main menu room.
     */
    MAIN_MENU("roomMainMenu"),

    /**
     * constant representing the name for the potentiation menu room.
     */
    POT("roomPot"),

    /**
     * constant representing the name for the how to play room.
     */
    HOWTOPLAY("roomHowToPlay"),

    /**
     * constant representing the name for the credits room.
     */
    CREDITS("roomCredits"),

    /**
     * constant representing the name for the game over room.
     */
    GAME_OVER("roomGameOver"),

    /**
     * constant representing the name for the level 1 room.
     */
    LEVEL1("roomLevel1"),

    /**
     * constant representing the name for the level 2 room.
     */
    LEVEL2("roomLevel2"),

    /**
     * constant representing the name for the level 3 room.
     */
    LEVEL3("roomLevel3");

    private String value;

    RoomsNames(final String value) {
        this.value = value;
    }

    /**
     * This method returns the class name of the desired object.
     * 
     * @return the class name of the requested object
     */
    public String getValue() {
        return this.value;
    }
}
