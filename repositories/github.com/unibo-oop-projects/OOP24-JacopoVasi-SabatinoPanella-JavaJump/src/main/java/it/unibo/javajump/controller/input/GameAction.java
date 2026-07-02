package it.unibo.javajump.controller.input;

/**
 * Enum that represents the abstract possible game actions, that will be handled by the model.
 */
public enum GameAction {
    /**
     * Move left game action.
     */
    MOVE_LEFT,
    /**
     * Move right game action.
     */
    MOVE_RIGHT,
    /**
     * Stop horizontal game action.
     */
    STOP_HORIZONTAL,
    /**
     * Pause game action.
     */
    PAUSE_GAME,
    /**
     * Confirm selection game action.
     */
    CONFIRM_SELECTION,
    /**
     * Move menu up game action.
     */
    MOVE_MENU_UP,
    /**
     * Move menu down game action.
     */
    MOVE_MENU_DOWN,
}
