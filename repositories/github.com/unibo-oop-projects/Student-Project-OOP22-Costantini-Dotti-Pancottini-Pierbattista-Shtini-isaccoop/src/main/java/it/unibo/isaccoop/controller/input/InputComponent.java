package it.unibo.isaccoop.controller.input;

import it.unibo.isaccoop.model.player.Player;

/**
 * InputComponent interface to update target state if a certain event is triggered.
 */
public interface InputComponent {

    /**
     * Method to update the player state according to the InputController.
     * @param player
     */
    void update(Player player);
}
