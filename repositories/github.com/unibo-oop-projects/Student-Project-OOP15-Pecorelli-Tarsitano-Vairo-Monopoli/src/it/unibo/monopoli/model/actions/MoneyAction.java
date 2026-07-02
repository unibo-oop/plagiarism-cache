package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;

/**
 * This abstract class represent the common contract for all the {@link Action}s
 * that use money.
 *
 */
public abstract class MoneyAction implements Action {

    private final int amount;

    /**
     * This constructor serves to get the amount of the {@link Action}.
     * 
     * @param amount
     *            - of the {@link Action}
     */
    protected MoneyAction(final int amount) {
        this.amount = amount;
    }

    @Override
    public void play(final Player player) {
        this.strategy(player);
        player.setMoney(player.getMoney() + this.amount);
    }

    /**
     * This is an abstract method that the specifics sub-classes have to
     * implements depending on the strategy to accomplish. This method represent
     * how to operate with {@link MoneyAction}s.
     * 
     * @param player
     *            - the {@link Player} who performs the {@link MoneyAction}
     */
    protected abstract void strategy(final Player player);
}
