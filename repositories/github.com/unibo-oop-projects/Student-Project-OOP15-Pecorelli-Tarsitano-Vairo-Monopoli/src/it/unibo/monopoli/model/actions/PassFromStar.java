package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Start;

/**
 * This class represent one of the {@link Action}s of the game. This one is for
 * what to do when you pass from the {@link Start}'s {@link Box}.
 *
 */
public class PassFromStar implements Action {

    @Override
    public void play(final Player player) {
        if (!player.isInPrison()) {
            new ToBePaid(Start.getMuchToPick()).play(player);
        }
    }

}
