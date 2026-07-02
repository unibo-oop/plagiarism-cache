package bubbleshooter.model.bubble;

/**
 * Enumeration that contains all the type of {@link Bubble} in the game.
 */
public enum BubbleType {

    /**
     * The {@link GridBubble} that is in the game's grid.
     */
    GRID_BUBBLE,

    /**
     * The {@link ShootingBubble} that is ready to be shot.
     */
    SHOOTING_BUBBLE,

    /**
     * The {@link SwitchBubble} that you can switch.
     */
    SWITCH_BUBBLE;
}
