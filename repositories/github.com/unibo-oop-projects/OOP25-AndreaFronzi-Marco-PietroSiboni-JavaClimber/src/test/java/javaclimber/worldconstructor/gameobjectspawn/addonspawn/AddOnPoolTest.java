package javaclimber.worldconstructor.gameobjectspawn.addonspawn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api.AddOnPool;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.AddOnPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolEasy;

/**
 * Test for the {@link AddOnPool}.
 */
class AddOnPoolTest {

    private static final double PLATFORM_WIDTH = 30;
    private static final double PLATFORM_HEIGHT = 20;

    private static final double MIN_Y = 0;
    private static final double MAX_Y = 800;

    private static final double MIN_X = 0;
    private static final double MAX_X = 400;

    private static final double CHANCE_ADD_ON = 0.3;

    /**
     * The AddOnPool instance to be tested.
     */
    private AddOnPool addOnPoolEasy;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    void setUp() {
        final var spawnPoolEasy = new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl());
        final var world = new UpperWorld(new BoundWorldImpl(new BoundY(MIN_Y, MAX_Y), new Boundary(MIN_X, MAX_X)));
        final var spawnPoolCreator = new SpawnPoolCreatorImpl(world);
        spawnPoolCreator.setSpawnPool(spawnPoolEasy);
        this.addOnPoolEasy = new AddOnPoolEasy(spawnPoolCreator, CHANCE_ADD_ON);
    }

    /**
     * Test for getting the add-on pool.
     */
    @Test
    void getterAddOnPoolTest() {
        final var addOnPool = this.addOnPoolEasy.getAddOnPool();
        assertFalse(addOnPool.isEmpty());
    }

    /**
     * Test for getting the chance of add-on.
     */
    @Test
    void getterChanceAddOnTest() {
        final var chanceAddOn = this.addOnPoolEasy.getChanceAddOn();
        assertEquals(CHANCE_ADD_ON, chanceAddOn);
    }

}
