package user.enums;

/**
 * This enum represents a list of paths for the menu classes names that are
 * passed as argument.
 */
public enum MenuClassesNames {

    /**
     * This fields represents the various objects in the main menu room.
     */
    PLAY_BUTTON("menu.mainmenu.ObjButtonPlay"), HOWTOPLAY_BUTTON("menu.mainmenu.ObjButtonHowToPlay"), CREDITS_BUTTON("menu.mainmenu.ObjButtonCredits"), QUIT_BUTTON("menu.mainmenu.ObjButtonQuit"), TITLE("menu.mainmenu.ObjTitle"),

    /**
     * This fields represents the various potentiation button in the
     * potentiation menu room.
     */
    HEALTH_BUTTON("menu.potlevel.ObjIncHealthButton"), LIFE_BUTTON("menu.potlevel.ObjIncLifeButton"), DAMAGE_BUTTON("menu.potlevel.ObjIncDamageButton"), PPS_BUTTON("menu.potlevel.ObjIncProjectilePerSecondButton"), SPEED_BUTTON("menu.potlevel.ObjIncShipSpeedButton"),

    /**
     * This field represents the utilizable point object in the potentiation
     * menu room.
     */
    UTILIZABLE_POINT("menu.potlevel.ObjUtilizablePoint"),

    /**
     * This field represents the next level button object in the potentiation
     * menu room.
     */
    NEXT_LEVEL_BUTTON("menu.potlevel.ObjNextLevelButton"),

    /**
     * This fields represents the objects in the stages rooms.
     */
    OBJ_LIFE("menu.stagelevel.ObjLife"), OBJ_SCORE("menu.stagelevel.ObjScore"), OBJ_TIME("menu.stagelevel.ObjTime"),

    /**
     * This field represents the game over button object in the game over room.
     */
    GAME_OVER_BUTTON("menu.gameovermenu.ObjGameOverButton"),

    /**
     * This field represents the howtoplay button object in the howtoplay room.
     */
    HOWTOPLAY("menu.howtoplaymenu.ObjHowToPlay"),

    /**
     * This field represents the credits button object in the credits room.
     */
    CREDITS("menu.creditsmenu.ObjCredits"),

    /**
     * This field represents the music looper object.
     */
    MUSIC_LOOPER("menu.MusicLooper"),

    /**
     * This field represents the backgrounds object.
     */
    STAGE_BACKGROUND("menu.stagelevel.StageBackground"), MENU_BACKGROUND("menu.MenuBackground");

    private String value;

    MenuClassesNames(final String value) {
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
