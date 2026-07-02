package it.unibo.michelito.controller.playercommand.api;

import it.unibo.michelito.model.player.api.Player;

/**
 * Interface for commands that can be executed by the {@link Player}.
 */
public interface PlayerCommand {
    /**
     * Method that executes the command.
     * @param player the {@link Player} who has to execute the command.
     */
    void execute(Player player);
}
