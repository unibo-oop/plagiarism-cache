package it.unibo.michelito.controller.playercommand.impl;

import it.unibo.michelito.controller.playercommand.api.PlayerCommand;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.bomb.api.Bomb;

/**
 * Implementation of a {@link PlayerCommand} that notify the {@link Player} to place a {@link Bomb}.
 */
public class PlaceCommand implements PlayerCommand {

    /**
     * Executes the command, notifying the {@link PlayerCommand} to place a {@link Bomb}.
     *
     * @param player the {@link Player} executing the command
     */
    @Override
    public void execute(final Player player) {
        player.notifyToPlace();
    }
}

