package unibo.exiled.controller;

/**
 * The main controller for the game.
 */
public interface GameController {

    /**
     * Gets the character controller.
     *
     * @return The character controller.
     */
    CharacterController getCharacterController();

    /**
     * Gets the items controller.
     *
     * @return The items' controller.
     */
    ItemsController getItemsController();

    /**
     * Gets the map controller.
     *
     * @return The map controller.
     */
    MapController getMapController();

    /**
     * Gets the combat controller.
     *
     * @return The combat controller.
     */
    CombatController getCombatController();

    /**
     * Checks if the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    boolean isOver();

    /**
     * Checks if the game is win the game.
     *
     * @return True if the game is win, false otherwise.
     */
    boolean isWon();
}
