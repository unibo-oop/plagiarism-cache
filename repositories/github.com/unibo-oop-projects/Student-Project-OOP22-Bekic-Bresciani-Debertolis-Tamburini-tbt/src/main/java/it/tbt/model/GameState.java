package it.tbt.model;

/**
 * The {@code GameState} enumeration represents the different states of the game.
 * Each state corresponds to a specific gameplay scenario or interface.
 */
public enum GameState {
    /**
     * Explore state, where the player moves around the environment.
     */
    EXPLORE,

    /**
     * Fight state, where the player engages in combat.
     */
    FIGHT,

    /**
     * Main menu state, where the player can choose from different options.
     */
    MENU,

    /**
     * Pause state, where the game is paused and the player can choose from different options.
     */
    PAUSE,

    /**
     * Shop state, where the player can access a shop interface.
     */
    SHOP,

    /**
     * Inventory state, where the player can manage their inventory.
     */
    INVENTORY,

    /**
     * Ending state, representing the end of the game.
     */
    ENDING
}
