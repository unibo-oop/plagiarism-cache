package javaclimber.worldconstructor.gameobjectspawn.platformspawn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.PlatformPool;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PlatformPoolEasy;

/**
 * Test for the {@link PlatformPool}.
 */
class PlatformPoolTest {

    private static final double X_MIN = 0;
    private static final double X_MAX = 100;

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 100;

    private static final double PLATFORM_WIDTH = 10;
    private static final double PLATFORM_HEIGHT = 10;

    /**
     * The PlatformPool instance to be tested.
     */
    private PlatformPool platformPool;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    void setUp() {
        final var world = new UpperWorld(new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX)));
        final var spawnPoolCreator = new SpawnPoolCreatorImpl(world);
        spawnPoolCreator.setSpawnPool(new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl()));
        this.platformPool = new PlatformPoolEasy(spawnPoolCreator, PLATFORM_WIDTH, PLATFORM_HEIGHT);
    }

    /**
     * Test for getting the platform pool.
     */
    @Test
    void getterPlatformPoolTest() {
        final var pool = this.platformPool.getPlatformPool();
        assertFalse(pool.isEmpty());
    }

    /**
     * Test for getting the platform width.
     */
    @Test
    void getterWidthTest() {
        assertEquals(PLATFORM_WIDTH, this.platformPool.getWidth());
    }

    /**
     * Test for getting the platform height.
     */
    @Test
    void getterHeightTest() {
        assertEquals(PLATFORM_HEIGHT, this.platformPool.getHeight());
    }
}
