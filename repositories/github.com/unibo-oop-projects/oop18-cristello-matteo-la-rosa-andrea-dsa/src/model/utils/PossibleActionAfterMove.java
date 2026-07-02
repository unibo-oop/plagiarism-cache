package model.utils;

/**
 * This enumerator is made to distinguish for different kind of possible actions player after move action.
 * 
 */
public enum PossibleActionAfterMove {
    /**
     * The action of pick up a treasure.
     */
    PICK_UP,
    /**
     * The action of releasing a treasure.
     */
    RELEASE,
    /**
     * The action of passing turn to next player.
     */
    PASS_TURN;
}
