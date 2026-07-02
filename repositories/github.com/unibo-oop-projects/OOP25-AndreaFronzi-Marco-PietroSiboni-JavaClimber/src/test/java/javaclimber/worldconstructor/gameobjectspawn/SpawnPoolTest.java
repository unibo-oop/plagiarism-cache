package javaclimber.worldconstructor.gameobjectspawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.api.SpawnPool;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolEasy;

/**
 * Test for the {@link SpawnPool}.
 */
class SpawnPoolTest {

    private static final double PLATFORM_WIDTH = 30;
    private static final double PLATFORM_HEIGHT = 20;

    /**
     * The spawn pool to test.
     */
    private SpawnPool platformPool;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    void setUp() {
        this.platformPool = new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl());
    }

    /**
     * Test for getting the static platform pool.
     */
    @Test
    void getterPlatformTest() {
        final var platforms = this.platformPool.getStaticPlatformPool();
        assertEquals(1, platforms.size());
    }

    /**
     * Test for getting the moving platform pool.
     */
    @Test
    void getterMovingPlatformTest() {
        final var movingPlatforms = this.platformPool.getMovingPlatformPool();
        assertEquals(1, movingPlatforms.size());
    }

    /**
     * Test for getting the on-touch platform pool.
     */
    @Test
    void getterOnTouchPlatformTest() {
        final var onTouchPlatforms = this.platformPool.getOnTouchPlatformPool();
        assertEquals(1, onTouchPlatforms.size());
    }

    /**
     * Test for getting the monster pool.
     */
    @Test
    void getterMonsterTest() {
        final var monsters = this.platformPool.getMonsterPool();
        assertEquals(1, monsters.size());
    }

    /**
     * Test for getting the gadget pool.
     */
    @Test
    void getterGadgetTest() {
        final var gadgets = this.platformPool.getGadgetPool();
        assertEquals(1, gadgets.size());
    }

    /**
     * Test for getting the money pool.
     */
    @Test
    void getterMoneyTest() {
        final var money = this.platformPool.getMoneyPool();
        assertEquals(1, money.size());
    }
}
