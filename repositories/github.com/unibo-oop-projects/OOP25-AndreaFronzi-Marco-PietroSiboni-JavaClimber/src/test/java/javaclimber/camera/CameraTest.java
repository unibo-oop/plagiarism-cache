package javaclimber.camera;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.camera.impl.CameraImpl;
import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.gameobj.impl.PlatformImpl;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.RealWorld;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.world.impl.World;
import it.unibo.model.worldconstructor.data.Difficult;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.AddOnPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.GameObjDimension;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.Distance;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PlatformPoolEasy;
import it.unibo.model.worldconstructor.worldgenerator.api.WorldConstructor;
import it.unibo.model.worldconstructor.worldgenerator.impl.WorldConstructorImpl;
import javaclimber.TestCostants;

/**
 * Tests for {@link CameraImpl}.
 */
class CameraTest {

    private static final double VIEW_WIDTH = 800.0;
    private static final double VIEW_HEIGHT = 600.0;
    private static final double PLAT_W = 100.0;
    private static final double PLAT_H = 20.0;
    private static final double PLAT_X_Y_100 = 100.0;
    private static final double PLAT_X_200 = 200.0;
    private static final double PLAT_Y_101 = -101.0;
    private static final double PLAT_Y_OUT_OF_BOUNDS = 2000.0;
    private static final double ALIEN_X = 10.0;
    private static final double ALIEN_Y = 10.0;
    private static final double ALIEN_WIDTH = 50.0;
    private static final double ALIEN_HEIGHT = 50.0;
    private static final double MIN_Y_BOUND = -2000.0;
    private static final double MAX_Y_DISTANCE = 200.0;
    private static final double MIN_Y_DISTANCE = 100.0;
    private static final double MAX_X_DISTANCE = 300.0;
    private static final double DELTA_20 = 20.0;
    private World world;
    private CameraImpl camera;

    /**
     * Set up all to simulate the camera behaviour.
     */
    @BeforeEach
    void setUp() {
        final ShopItemFactory factory = new ShopItemFactoryImpl();
        final Inventory inventory = new InventoryImpl(factory);
        final ActiveUpgrades activeUpgrades = new ActiveUpgradesImpl(inventory);
        final AlienImpl alien = new AlienImpl(new Vector2dImpl(0, 0), new Vector2dImpl(ALIEN_X, ALIEN_Y), ALIEN_WIDTH,
                ALIEN_HEIGHT, activeUpgrades);

        final Boundary boundX = new Boundary(0, VIEW_WIDTH);
        final BoundY boundY = new BoundY(MIN_Y_BOUND, VIEW_HEIGHT);
        final BoundWorld boundWorld = new BoundWorldImpl(boundY, boundX);
        final RealWorld realWorld = new RealWorld(alien, boundWorld);
        final UpperWorld upperWorld = new UpperWorld(boundWorld);
        world = new World(upperWorld, realWorld);
        final ScoreManager scoreManager = new ScoreManagerImpl();

        final var distanceEasy = new Distance(MAX_Y_DISTANCE, MIN_Y_DISTANCE, MAX_X_DISTANCE);
        final var spawnPoolCreator = new SpawnPoolCreatorImpl(upperWorld);
        final var spawnPoolEasy = new SpawnPoolEasy(GameObjDimension.EASY_PLATFORM_WIDTH,
                GameObjDimension.EASY_PLATFORM_HEIGHT, scoreManager);
        final var platformPoolEasy = new PlatformPoolEasy(spawnPoolCreator, GameObjDimension.EASY_PLATFORM_WIDTH,
                GameObjDimension.EASY_PLATFORM_HEIGHT);
        final var addOnPoolEasy = new AddOnPoolEasy(spawnPoolCreator, 0.5);
        final var difficultList = List
                .of(new Difficult(0, distanceEasy, spawnPoolEasy, addOnPoolEasy, platformPoolEasy));
        spawnPoolCreator.setSpawnPool(spawnPoolEasy);

        final WorldConstructor wc = new WorldConstructorImpl(upperWorld, difficultList.getFirst(), spawnPoolCreator);

        this.camera = new CameraImpl(VIEW_WIDTH, VIEW_HEIGHT, world, wc);
    }

    /**
     * Tests that the camera is initialized with the correct dimensions.
     */
    @Test
    void testCorrectInitialization() {
        assertEquals(VIEW_WIDTH, camera.getViewportWidth());
        assertEquals(VIEW_HEIGHT, camera.getViewportHeight());
    }

    /**
     * Verifies that the game objects(in this case platform) move downwards when the
     * camera moves up.
     */
    @Test
    void testMovement() {
        final PlatformImpl platform = new PlatformImpl(new Vector2dImpl(PLAT_X_Y_100, PLAT_X_Y_100), PLAT_W, PLAT_H,
                Optional.empty(),
                Optional.empty());
        world.getRealWorld().addStaticPlatform(platform);
        camera.update(TestCostants.TOTAL_DELTA_50);
        assertEquals(PLAT_X_Y_100 + TestCostants.TOTAL_DELTA_50, platform.getPosY());
        assertEquals(TestCostants.TOTAL_DELTA_50, world.getRealWorld().getAlien().getPosY());
    }

    /**
     * Verifies the promotion logic from the UpperWorld to RealWorld when a object
     * enter in a specific limit.
     */
    @Test
    void testTransferFromUpperToReal() {
        final PlatformImpl platform = new PlatformImpl(new Vector2dImpl(PLAT_X_200, PLAT_Y_101), PLAT_W, PLAT_H,
                Optional.empty(),
                Optional.empty());
        world.getUpperWorld().addStaticPlatform(platform);
        camera.update(0.5);
        assertFalse(world.getUpperWorld().getStaticPlatforms().isEmpty());

        camera.update(10.0);
        assertFalse(world.getRealWorld().getStaticPlatforms().isEmpty());
    }

    /**
     * Verifies that objects that fall below the screen are removed from RealWorld.
     */
    @Test
    void testCleanRealWorld() {
        final PlatformImpl platform = new PlatformImpl(new Vector2dImpl(PLAT_X_Y_100, PLAT_Y_OUT_OF_BOUNDS), PLAT_W, PLAT_H,
                Optional.empty(),
                Optional.empty());
        world.getRealWorld().addStaticPlatform(platform);
        camera.update(DELTA_20);
        assertFalse(world.getRealWorld().getStaticPlatforms().contains(platform));
    }

}
