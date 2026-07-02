package javaclimber.worldconstructor.worldgenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.world.api.QueueWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldconstructor.data.Difficult;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.AddOnPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.AddOnPoolMedium;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.GameObjDimension;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.Distance;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PlatformPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PlatformPoolMedium;
import it.unibo.model.worldconstructor.worldgenerator.impl.WorldConstructorImpl;

/**
 * Test for the {@link WorldConstructorImpl}.
 */
class WorldConstructorTest {

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 600;

    private static final double X_MIN = 0;
    private static final double X_MAX = 800;

    private static final double PLATFORM_WIDTH = 100;
    private static final double PLATFORM_HEIGHT = 20;

    private static final double MAX_PLATFORM_DISTANCE_X = 20;

    private static final double MAX_PLATFORM_DISTANCE_Y = 70;
    private static final double MIN_PLATFORM_DISTANCE_Y = 10;

    private static final double SPAWN_PROBABILITY = 0.5;

    private static final double HEIGHT_EASY = 0;

    /**
     * The WorldConstructorImpl to test.
     */
    private WorldConstructorImpl worldConstructor;

    /**
     * The upper world for testing.
     */
    private QueueWorld upperWorld;

    /**
     * The spawn pool creator for testing.
     */
    private SpawnPoolCreatorImpl spawnPoolCreator;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    void setUp() {
        final var boundary = new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX));
        this.upperWorld = new UpperWorld(boundary);
        final var distance = new Distance(MAX_PLATFORM_DISTANCE_Y, MIN_PLATFORM_DISTANCE_Y, MAX_PLATFORM_DISTANCE_X);
        this.spawnPoolCreator = new SpawnPoolCreatorImpl(upperWorld);
        final var spawnPool = new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl());
        this.spawnPoolCreator.setSpawnPool(spawnPool);
        final var addOnPool = new AddOnPoolEasy(this.spawnPoolCreator, SPAWN_PROBABILITY);
        final var platformPool = new PlatformPoolEasy(this.spawnPoolCreator, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        final var difficult = new Difficult(HEIGHT_EASY, distance, spawnPool, addOnPool, platformPool);
        this.worldConstructor = new WorldConstructorImpl(upperWorld, difficult, this.spawnPoolCreator);
    }

    /**
     * Test for filling the world.
     */
    @Test
    void fillWorldTest() {
        this.worldConstructor.fillWorld();
        if (!this.upperWorld.getGadgets().isEmpty()) {
            assertTrue(this.upperWorld.getGadgets().getLast().getPosY() > Y_MIN - GameObjDimension.ELYCAP_HEIGHT);
        }
        if (!this.upperWorld.getMoneys().isEmpty()) {
            assertTrue(this.upperWorld.getMoneys().getLast().getPosY() > Y_MIN - GameObjDimension.COIN_HEIGHT);
        }
        if (!this.upperWorld.getMonsters().isEmpty()) {
            assertTrue(this.upperWorld.getMonsters().getLast().getPosY() > Y_MIN - GameObjDimension.ENEMY_HEIGHT);
        }
        if (!this.upperWorld.getMovingPlatforms().isEmpty()) {
            assertTrue(this.upperWorld.getMovingPlatforms().getLast().getPosY() > Y_MIN);
        }
        if (!this.upperWorld.getStaticPlatforms().isEmpty()) {
            assertTrue(this.upperWorld.getStaticPlatforms().getLast().getPosY() > Y_MIN);
        }
        if (!this.upperWorld.getOnTouchPlatforms().isEmpty()) {
            assertTrue(this.upperWorld.getOnTouchPlatforms().getLast().getPosY() > Y_MIN);
        }
    }

    /**
     * Test for updating the difficulty.
     */
    @Test
    void updateDifficultTest() {
        final var newAddOnPool = new AddOnPoolMedium(this.spawnPoolCreator, SPAWN_PROBABILITY);
        final var newPlatformPool = new PlatformPoolMedium(this.spawnPoolCreator, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        final var newSpawnPool = new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl());
        final var newDistance = new Distance(MAX_PLATFORM_DISTANCE_Y, MIN_PLATFORM_DISTANCE_Y, MAX_PLATFORM_DISTANCE_X);
        final var newDifficult = new Difficult(HEIGHT_EASY, newDistance, newSpawnPool, newAddOnPool, newPlatformPool);
        this.worldConstructor.update(newDifficult);
        this.worldConstructor.fillWorld();
        assertTrue(
                !this.upperWorld.getGadgets().isEmpty() || !this.upperWorld.getMoneys().isEmpty()
                        || !this.upperWorld.getMonsters().isEmpty() || !this.upperWorld.getMovingPlatforms().isEmpty()
                        || !this.upperWorld.getOnTouchPlatforms().isEmpty());
    }
}
