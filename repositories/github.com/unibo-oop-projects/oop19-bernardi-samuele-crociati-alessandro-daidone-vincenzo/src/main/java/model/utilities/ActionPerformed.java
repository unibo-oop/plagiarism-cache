package model.utilities;

public enum ActionPerformed {
    /**
     * A hero has been added to team one.
     */
    ADD_TO_TEAM_ONE,
    /**
     * A hero has been added to team two.
     */
    ADD_TO_TEAM_TWO,
    /**
     * A hero has been removed from team one.
     */
    REMOVE_FROM_TEAM_ONE,
    /**
     * A hero has been removed from team two.
     */
    REMOVE_FROM_TEAM_TWO,
    /**
     * An entity has been selected.
     */
    SELECT_ENTITY,
    /**
     * An entity has been unselected.
     */
    UNSELECT_ENTITY,
    /**
     * An entity has been moved.
     */
    MOVE_ENTITY,
    /**
     * An entity has been attacked.
     */
    ATTACK_ENTITY,
    /**
     * Player 1 won the game.
     */
    PLAYER1_WON,
    /**
     * Player 2 won the game.
     */
    PLAYER2_WON,
    /**
     * Nothing happened.
     */
    NONE
}
