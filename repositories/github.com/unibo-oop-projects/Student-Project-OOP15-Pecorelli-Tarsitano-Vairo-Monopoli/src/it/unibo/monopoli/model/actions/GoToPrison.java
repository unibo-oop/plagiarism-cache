package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Pawn;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;

/**
 * This class represent one of the {@link Action}s of the game. This one is for
 * sending to prison a {@link Player}.
 *
 */
public class GoToPrison implements Action {

    private final Box prison;

    /**
     * Constructs a new instance of {@link GoToPrison}'s {@link Action}. The
     * {@link Box} in input specifies the prison's {@link Box} where the
     * {@link Player}'s {@link Pawn} have to go.
     * 
     * @param prison
     *            - the prison's {@link Box}
     */
    public GoToPrison(final Box prison) {
        this.prison = prison;
    }

    @Override
    public void play(final Player player) {
        player.setPrison(true);
        MoveUpTo.moveUpToBox(prison).play(player);
    }

}
