package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.ItalianNeutralArea;

/**
 * This class represent one of the {@link Action}s of the game. This one is for
 * steal money of other {@link Player}s that hid them in the
 * {@link ItalianNeutralArea}.
 *
 */
public class ToStealMoney implements Action {

    private final ItalianNeutralArea neutralArea;

    /**
     * Constructs a new instance of this {@link Action}.
     * 
     * @param neutralArea
     *            - is the {@link ItalianNeutralArea} whence you'll steal money
     */
    public ToStealMoney(final ItalianNeutralArea neutralArea) {
        this.neutralArea = neutralArea;
    }

    @Override
    public void play(final Player player) {
        player.setMoney(player.getMoney() + this.neutralArea.getDirtyMoney());
        this.neutralArea.resetMoney();
    }

}
