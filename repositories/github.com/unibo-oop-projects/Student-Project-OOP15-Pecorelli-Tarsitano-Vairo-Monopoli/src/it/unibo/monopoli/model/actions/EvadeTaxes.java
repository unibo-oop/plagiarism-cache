package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.ItalianNeutralArea;

/**
 * This class represent one of the {@link Action}s of the game. This one is for
 * evading taxes: the money that you have to give to the bank, you'll hide them
 * in the {@link ItalianNeutralArea} and you won't pay the 22% of them. This
 * {@link Action} is present only in the italian version of the game.
 *
 */
public class EvadeTaxes implements Action {

    private static final double TAX = 0.22;

    private final int amount;
    private final ItalianNeutralArea neutralArea;

    /**
     * Constructs an instance of this {@link Action}. The amount is how many
     * money you should pay, while {@link ItalianNeutralArea} is where you'll
     * hide them.
     * 
     * @param amount
     *            _ is how many money you should pay
     * @param neutralArea
     *            - where you'll hide money.
     */
    public EvadeTaxes(final int amount, final ItalianNeutralArea neutralArea) {
        this.amount = amount - ((int) Math.floor(amount * TAX));
        if (amount <= 0) {
            throw new IllegalArgumentException("Only positive amount different of zero!");
        }
        this.neutralArea = neutralArea;
    }

    @Override
    public void play(final Player player) {
        player.setMoney(player.getMoney() - amount);
        this.neutralArea.setDirtyMoney(this.neutralArea.getDirtyMoney() + this.amount);
    }

}
