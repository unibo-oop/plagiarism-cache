package it.unibo.isaccoop.test.model.item;

import it.unibo.isaccoop.model.item.Coin;
import it.unibo.isaccoop.model.item.Heart;
import it.unibo.isaccoop.model.item.Item;
import it.unibo.isaccoop.model.player.PlayerStat;
import it.unibo.isaccoop.model.player.PlayerStatImpl;
import it.unibo.isaccoop.model.powerup.DamageUp;
import it.unibo.isaccoop.model.powerup.HealthUp;
import it.unibo.isaccoop.model.powerup.PowerUp;
import it.unibo.isaccoop.model.powerup.SpeedUp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
/**
 *
 * Class to test item.
 *
 */
class TestItem {
    private final PlayerStat stat = new PlayerStatImpl(null);
    private final Item coin = new Coin();
    private final Item heart = new Heart();
    private final PowerUp damageUp = new DamageUp();
    private final PowerUp healthUp = new HealthUp();
    private final PowerUp speedUp = new SpeedUp();

    /**
     * Test function for power ups.
     */
    @Test
    void testPowerUp() {
        assertEquals(1.0, stat.getDamage());
        damageUp.setSuperItem(true);
        damageUp.interact(stat);
        assertEquals(3.0, stat.getDamage());
        damageUp.setSuperItem(false);
        damageUp.interact(stat);
        assertEquals(4.0, stat.getDamage());

        assertEquals(3, stat.getMaxHeart());
        healthUp.setSuperItem(false);
        healthUp.interact(stat);
        assertEquals(4, stat.getMaxHeart());

        assertEquals(1, stat.getSpeed());
        speedUp.setSuperItem(true);
        speedUp.interact(stat);
        assertEquals(2, stat.getSpeed());

    }

    /**
     * Test function for basic items.
     * */
    @Test
    void testItem() {
        coin.interact(stat);
        assertEquals(1, stat.getCoin());

        assertEquals(3, stat.getHeart());
        heart.interact(stat);
        assertEquals(3, stat.getHeart());
    }
}
