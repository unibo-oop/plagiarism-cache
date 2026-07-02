package tmw.common;

/**
 * Enumeration that handle all type of input relative to the player.
 *
 */
public enum CharacterStates {

    /**
     * Value for moving up.
     */
    MOVE_UP,

    /**
     * Value for moving down.
     */
    MOVE_DOWN,

    /**
     * Value for moving to the left.
     */
    MOVE_LEFT,

    /**
     * Value for moving to the right.
     */
    MOVE_RIGHT,

    /**
     * Value for shooting up.
     */
    SHOOT_UP,

    /**
     * Value for shooting down.
     */
    SHOOT_DOWN,

    /**
     * Value for shooting to the left.
     */
    SHOOT_LEFT,

    /**
     * Value for shooting to the right.
     */
    SHOOT_RIGHT,

    /**
     * Value to determine that the player requested the item in the first position
     * of the inventory.
     */
    ITEM1,

    /**
     * Value to determine that the player requested the item in the second position
     * of the inventory.
     */
    ITEM2,

    /**
     * Value to determine that the player requested the item in the third position
     * of the inventory.
     */
    ITEM3,

    /**
     * Value to determine that the player requested the item in the fourth position
     * of the inventory.
     */
    ITEM4,

    /**
     * Value to determine that the player requested the item in the fifth position
     * of the inventory.
     */
    ITEM5,

    /**
     * Value that represent a general movement.
     */
    MOVE,

    /**
     * Value that represent a general shoot.
     */
    SHOOT,

    /**
     * Value that represent a general request from the inventory.
     */
    INVENTORY,

    /**
     * Value that represent an empty command list.
     */
    EMPTY_COMMAND;

}
