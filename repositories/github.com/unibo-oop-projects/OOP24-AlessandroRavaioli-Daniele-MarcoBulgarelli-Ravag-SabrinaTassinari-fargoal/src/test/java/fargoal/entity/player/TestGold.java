package fargoal.entity.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

 import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fargoal.model.entity.player.api.Gold;
import fargoal.model.entity.player.impl.GoldImpl;

//CHECKSTYLE: MagicNumber OFF
//Deactivates all magic numbers as they are needed for test porpouses.
/**
 * Class to test {@link GoldImpl} class.
 */
class TestGold {

    private Gold gold;

    /**
     * Configurates the gold implementation before each test.
     * If this class gets extended, make sure to call {@code super.setup()}
     * to maintain the configuration.
     */
    @BeforeEach
    void setup() {
        gold = new GoldImpl();
    }

    @Test
    void testInitialization() {
        assertEquals(0, gold.getCurrentGold());
        assertEquals(100, gold.getMaxCapacity());
        assertEquals(0, gold.getGoldDonated());
    }

    @Test
    void testObtainingGold() {
        gold.addGold(11);
        gold.addGold(100);
        assertEquals(100, gold.getCurrentGold());
    }

    @Test
    void testGoldReset() {
        gold.addGold(99);
        assertEquals(99, gold.getCurrentGold());
        gold.resetGold();
        assertEquals(0, gold.getCurrentGold());
    }

    @Test
    void testMaxCapacity() {
        gold.setMaxCapacity(1000);
        assertEquals(1000, gold.getMaxCapacity());
        assertEquals(0, gold.getCurrentGold());

        gold.addGold(9999);
        assertEquals(1000, gold.getCurrentGold());
        assertEquals(1000, gold.getMaxCapacity());
    }

    @Test
    void testDonatedGold() {
        gold.setGoldDonated(100);
        assertEquals(100, gold.getGoldDonated());
        gold.setGoldDonated(0);
        assertEquals(0, gold.getGoldDonated());
    }

    @Test
    void testRemoveGold() {
        gold.removeGold(19);
        assertEquals(0, gold.getCurrentGold());
        gold.addGold(100);
        gold.removeGold(99);
        assertEquals(1, gold.getCurrentGold());
    }
    //CHECKSTYLE: MagicNumber ON
}
