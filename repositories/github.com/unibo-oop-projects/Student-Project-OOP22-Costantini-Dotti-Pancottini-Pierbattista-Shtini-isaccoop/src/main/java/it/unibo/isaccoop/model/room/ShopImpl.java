package it.unibo.isaccoop.model.room;

import it.unibo.isaccoop.model.player.PlayerStat;
import it.unibo.isaccoop.model.powerup.PowerUp;

/**
 * Class that implements the Shop interface.
 * */
public class ShopImpl implements Shop {

    /**
     * Method that applies the collected powerup to the player, if the player 
     * has enough coins to buy it.
     * @param player
     * @param powerUp
     * @return true if the power up has been purchased
     */
    @Override
    public boolean buyItem(final PlayerStat player, final PowerUp powerUp) {
        if (player.getCoin() >= powerUp.getPrice()) {
            player.setCoin(player.getCoin() - powerUp.getPrice());
            powerUp.interact(player);
            return true;
        }
        return false;
    }

}
