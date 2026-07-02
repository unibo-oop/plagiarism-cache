package model.entities;
/**
 * This Enum lists the major GameEntity types in the game. 
 * They're used in order to control collisions and events.
 */
public enum GameEntityTypes {
    /**
     * The player.
     */
    PLAYER, 

    /**
     * Any item.
     */
    ITEM, 

    /**
     * Any enemy.
     */
    ENEMY,

    /**
     * A special boss enemy.
     */
    BOSS,

    /**
     * A door.
     */
    DOOR,

    /**
     * Stairs up.
     */
    STAIRS_UP,

    /**
     * Stairs down.
     */
    STAIRS_DOWN,

    /**
     * Red potion.
     */
    RED_POTION,

    /**
     * Blue potion.
     */
    BLUE_POTION
}
