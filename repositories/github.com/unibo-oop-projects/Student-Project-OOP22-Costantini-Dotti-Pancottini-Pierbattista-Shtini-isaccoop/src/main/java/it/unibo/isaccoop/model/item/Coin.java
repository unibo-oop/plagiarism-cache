package it.unibo.isaccoop.model.item;

import it.unibo.isaccoop.graphics.factory.ItemGraphicsComponentImpl;
import it.unibo.isaccoop.model.player.PlayerStat;

/**
 * Represents a coin in the game.
 * */
public class Coin extends AbstractItem {

    private static final int COIN_UP = 1;

    /**
     * Coin Constructor.
     * */
    public Coin() {
        super(new ItemGraphicsComponentImpl().getCoinGraphicsComponent());
    }

    /**
     *  Method for interacting with player money.
     *  @param p reference to player.
     * */
    @Override
    public void interact(final PlayerStat p) {
        p.setCoin(p.getCoin() + COIN_UP);
    }



}
