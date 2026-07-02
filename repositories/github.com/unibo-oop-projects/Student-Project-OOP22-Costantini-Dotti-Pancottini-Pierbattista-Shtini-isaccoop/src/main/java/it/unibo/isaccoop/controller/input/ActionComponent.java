package it.unibo.isaccoop.controller.input;

import it.unibo.isaccoop.core.GameLoop;

/**
 * Interface for the update of the ActionController.
 */
public interface ActionComponent {

    /**
     * Execute the actions selected in the action controller.
     * @param ctrl of the level.
     * @param gameLoop reference to GameLoop.
     */
    void update(ActionController ctrl, GameLoop gameLoop);
}
