package javaclimber.worldconstructor.gameobjectspawn.addonspawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api.FactoryAddOn;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.FactoryAddOnImpl;

/**
 * Test for the {@link FactoryAddOn}.
 */
class FactoryAddOnTest {

    private static final double POSITION_X = 0;
    private static final double POSITION_Y = 0;

    /**
     * The FactoryAddOn instance to test.
     */
    private FactoryAddOn factoryAddOn;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    void setUp() {
        this.factoryAddOn = new FactoryAddOnImpl(new ScoreManagerImpl());
    }

    /**
     * Test for creating a coin.
     */
    @Test
    void createCoinTest() {
        final Coin coin = this.factoryAddOn.createCoin(new Vector2dImpl(POSITION_X, POSITION_Y));
        assertEquals(coin, coin);
    }

    /**
     * Test for creating an enemy.
     */
    @Test
    void createEnemyTest() {
        final Enemy enemy = this.factoryAddOn.createEnemy(new Vector2dImpl(POSITION_X, POSITION_Y));
        assertEquals(enemy, enemy);
    }

    /**
     * Test for creating an EliCap gadget.
     */
    @Test
    void createElycapTest() {
        final Gadget elycap = this.factoryAddOn.createElycap(new Vector2dImpl(POSITION_X, POSITION_Y));
        assertEquals(elycap, elycap);
    }
}
