package it.unibo.isaccoop.test.model.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.isaccoop.model.player.PlayerStat;
import it.unibo.isaccoop.model.player.PlayerStatImpl;
import it.unibo.isaccoop.model.powerup.CoinUp;
import it.unibo.isaccoop.model.powerup.DamageUp;
import it.unibo.isaccoop.model.powerup.HealthUp;
import it.unibo.isaccoop.model.powerup.PowerUp;
import it.unibo.isaccoop.model.room.Shop;
import it.unibo.isaccoop.model.room.ShopImpl;

/**
 * TestShop class to test shop behavior.
 * */
class TestShop {
    private final Shop shop = new ShopImpl();
    private final PlayerStat player = new PlayerStatImpl(null);
    private final PowerUp damageUp = new DamageUp();
    private final PowerUp healthUp = new HealthUp();
    private final PowerUp coinUp = new CoinUp();

    private static final int START_COIN = 10;
    private static final int COINUP_SUPER = 8;
    private static final int COINUP_NORMAL = 4;
    private static final int NORMAL_PRICE = 2;
    private static final int SUPER_PRICE = 4;

    /***/
    @Test
    void testPowerUp() {

        //Test shop damage-up
        player.setCoin(START_COIN);
        assertEquals(START_COIN, player.getCoin());
        damageUp.setSuperItem(true);
        shop.buyItem(player, damageUp);
        assertEquals(START_COIN - SUPER_PRICE, player.getCoin());
        damageUp.setSuperItem(false);
        shop.buyItem(player, damageUp);
        assertEquals(START_COIN - SUPER_PRICE - NORMAL_PRICE, player.getCoin());

        //Test shop coin-up
        coinUp.setSuperItem(true);
        shop.buyItem(player, coinUp);
        assertEquals(START_COIN - SUPER_PRICE * 2 - NORMAL_PRICE
                + COINUP_SUPER, player.getCoin());
        coinUp.setSuperItem(false);
        shop.buyItem(player, coinUp);
        assertEquals(START_COIN - SUPER_PRICE * 2 - NORMAL_PRICE * 2
                + COINUP_SUPER + COINUP_NORMAL, player.getCoin());

        player.setCoin(START_COIN);
        //Test shop health-up
        healthUp.setSuperItem(true);
        shop.buyItem(player, healthUp);
        assertEquals(START_COIN - SUPER_PRICE, player.getCoin());
        healthUp.setSuperItem(false);
        shop.buyItem(player, healthUp);
        assertEquals(START_COIN - SUPER_PRICE - NORMAL_PRICE, player.getCoin());
        healthUp.setSuperItem(true);
        shop.buyItem(player, healthUp);
        assertEquals(START_COIN - SUPER_PRICE * 2 - NORMAL_PRICE, player.getCoin());

    }

}
