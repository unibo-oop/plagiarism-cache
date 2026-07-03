package user.enums;

/**
 * This enum represents a list of numbers that are passed as argument.
 */
public enum RecurrentNumbers {

    /**
     * constant representing menu room width.
     */
    RESOLUTION_X(800),

    /**
     * constant representing menu room height.
     */
    RESOLUTION_Y(600),

    /**
     * constant representing level room width.
     */
    LEVEL_RESOLUTION_X(4000),

    /**
     * constant representing level room height.
     */
    LEVEL_RESOLUTION_Y(4000),

    /**
     * constant representing the left side of the title sprite (in terms of
     * pixel).
     */
    MAIN_MENU_TITLE_X(400),

    /**
     * constant representing the upper side of the title sprite (in terms of
     * pixel).
     */
    MAIN_MENU_TITLE_Y(150),

    /**
     * constant representing the left side of the main menu buttons (in terms of
     * pixel).
     */
    MAIN_MENU_BUTTON_X(50),

    /**
     * constant representing the upper side of the first main menu button (in
     * terms of pixel).
     */
    MAIN_MENU_FIRST_BUTTON_Y(300),

    /**
     * constant representing the upper side of the second main menu button (in
     * terms of pixel).
     */
    MAIN_MENU_SECOND_BUTTON_Y(370),

    /**
     * constant representing the upper side of the third main menu button (in
     * terms of pixel).
     */
    MAIN_MENU_THIRD_BUTTON_Y(440),

    /**
     * constant representing the upper side of the fourth main menu button (in
     * terms of pixel).
     */
    MAIN_MENU_FOURTH_BUTTON_Y(510),

    /**
     * constant representing the left side of the left potentiation menu buttons
     * (in terms of pixel).
     */
    POT_ROOM_LEFT_BUTTON_X(50),

    /**
     * constant representing the left side of the right potentiation menu
     * buttons (in terms of pixel).
     */
    POT_ROOM_RIGHT_BUTTON_X(400),

    /**
     * constant representing the upper side of the upper potentiation menu
     * buttons (in terms of pixel).
     */
    POT_ROOM_UPPER_BUTTON_Y(50),

    /**
     * constant representing the upper side of the medium potentiation menu
     * buttons (in terms of pixel).
     */
    POT_ROOM_MEDIUM_BUTTON_Y(200),

    /**
     * constant representing the upper side of the lower potentiation menu
     * buttons (in terms of pixel).
     */
    POT_ROOM_LOWER_BUTTON_Y(350),

    /**
     * constant representing the left side of the left potentiation menu bars
     * (in terms of pixel).
     */
    POT_ROOM_LEFT_BAR_X(150),

    /**
     * constant representing the left side of the right potentiation menu bars
     * (in terms of pixel).
     */
    POT_ROOM_RIGHT_BAR_X(500),

    /**
     * constant representing the upper side of the upper potentiation menu bars
     * (in terms of pixel).
     */
    POT_ROOM_UPPER_BAR_Y(100),

    /**
     * constant representing the upper side of the medium potentiation menu bars
     * (in terms of pixel).
     */
    POT_ROOM_MEDIUM_BAR_Y(250),

    /**
     * constant representing the upper side of the lower potentiation menu bars
     * (in terms of pixel).
     */
    POT_ROOM_LOWER_BAR_Y(400),

    /**
     * constant representing the left side of the left potentiation menu labels
     * (in terms of pixel).
     */
    POT_ROOM_LEFT_LABEL_X(150),

    /**
     * constant representing the left side of the right potentiation menu labels
     * (in terms of pixel).
     */
    POT_ROOM_RIGHT_LABEL_X(500),

    /**
     * constant representing the upper side of the upper potentiation menu
     * labels (in terms of pixel).
     */
    POT_ROOM_UPPER_LABEL_Y(50),

    /**
     * constant representing the upper side of the medium potentiation menu
     * labels (in terms of pixel).
     */
    POT_ROOM_MEDIUM_LABEL_Y(200),

    /**
     * constant representing the upper side of the lower potentiation menu
     * labels (in terms of pixel).
     */
    POT_ROOM_LOWER_LABEL_Y(350),

    /**
     * constant representing the left side of the next level sprite (in terms of
     * pixel).
     */
    POT_ROOM_NEXT_LEVEL_BUTTON_X(550),

    /**
     * constant representing the left side of the next level sprite (in terms of
     * pixel).
     */
    POT_ROOM_NEXT_LEVEL_BUTTON_Y(500),

    /**
     * constant representing the upper side of stage buttons (in terms of
     * pixel).
     */
    STAGE_LEVEL_BUTTON_Y(10),

    /**
     * constant representing the left side of left stage button (in terms of
     * pixel).
     */
    STAGE_LEVEL_LEFT_BUTTON_X(10),

    /**
     * constant representing the left side of central stage button (in terms of
     * pixel).
     */
    STAGE_LEVEL_CENTRAL_BUTTON_X(318),

    /**
     * constant representing the left side of right stage button (in terms of
     * pixel).
     */
    STAGE_LEVEL_RIGHT_BUTTON_X(675),

    /**
     * constant representing the x position where the player spawns (in terms of
     * pixel).
     */
    PLAYER_SPAWN_X(2000),

    /**
     * constant representing the y position where the player spawns (in terms of
     * pixel).
     */
    PLAYER_SPAWN_Y(2000),

    /**
     * constant representing the left side of the game over sprite (in terms of
     * pixel).
     */
    GAME_OVER_BUTTON_X(400),

    /**
     * constant representing the upper side of the game over sprite (in terms of
     * pixel).
     */
    GAME_OVER_BUTTON_Y(300),

    /**
     * constant representing the left side of the howtoplay sprite (in terms of
     * pixel).
     */
    HOWTOPLAY_BUTTON_X(10),

    /**
     * constant representing the upper side of the howtoplay sprite (in terms of
     * pixel).
     */
    HOWTOPLAY_BUTTON_Y(50),

    /**
     * constant representing the left side of the credits sprite (in terms of
     * pixel).
     */
    CREDITS_BUTTON_X(0),

    /**
     * constant representing the upper side of the credits sprite (in terms of
     * pixel).
     */
    CREDITS_BUTTON_Y(0),

    /**
     * constant representing the horizontal point of view of the room.
     */
    POINT_OF_VIEW_X(400),

    /**
     * constant representing the vertical point of view of the room.
     */
    POINT_OF_VIEW_Y(300);

    private int value;

    RecurrentNumbers(final int value) {
        this.value = value;
    }

    /**
     * This method returns the class name of the desired object.
     * 
     * @return the class name of the requested object
     */
    public int getValue() {
        return this.value;
    }
}
