package user.enums;

/**
 * This enum represents a list of paths for the objects sprites that are passed
 * as argument.
 */
public enum MenuSprites {

    /**
     * This fields represents backgrounds object.
     */
    MENU_BACKGROUND("game/scenario/star"), STAGE_BACKGROUND("game/scenario/starField"),

    /**
     * This fields represents the various sprites in the main menu room.
     */
    INDICATOR("buttons/buttonIndicator"), CREDITS("buttons/buttonCredits"), HOWTOPLAY("buttons/buttonHowToPlay"), PLAY("buttons/buttonPlay"), QUIT("buttons/buttonQuit"), TITLE("buttons/title"),

    /**
     * This fields represents the various buttons sprites in the potentiation
     * menu room.
     */
    DAMAGE("buttons/buttonDamage"), HEALTH("buttons/buttonHealth"), LIFE("buttons/buttonLife"), PROJECTILE_PER_SECOND("buttons/buttonProjectilePerSecond"), SHIP_SPEED("buttons/buttonSpeed"), NEXT_LEVEL("buttons/buttonNext"),
    /**
     * This fields represents the various labels sprites in the potentiation
     * menu room.
     */
    POINTS("buttons/label/points"), LABEL_DAMAGE("buttons/label/damage"), LABEL_HEALTH("buttons/label/health"), LABEL_LIFE("buttons/label/oneup"), LABEL_PPS("buttons/label/atkspeed"), LABEL_SPEED("buttons/label/speed"),

    /**
     * This field represents the bar sprite in the potentiation menu room.
     */
    BAR("buttons/rankBar"),

    /**
     * This field represents the game over button in the game over menu.
     */
    GAME_OVER("buttons/buttonGameOver"),

    /**
     * This field represents the howtoplay button in the howtoplay menu.
     */
    HOWTOPLAY_MENU("buttons/buttonHowToPlayMenu"),

    /**
     * This field represents the credits in the credits menu.
     */
    CREDITS_MENU("buttons/buttonCreditsMenu");

    private String value;

    MenuSprites(final String value) {
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
