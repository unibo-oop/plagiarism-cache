package javaclimber.worldconstructor.gameobjectspawn.addonspawn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.gameobj.impl.PlatformImpl;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.world.api.QueueWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api.AddOnCreator;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.AddOnCreatorImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.AddOnPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.AddOnPoolMedium;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolEasy;

/**
 * Test for the {@link AddOnCreator}.
 */
class AddOnCreatorTest {

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 800;
    private static final double X_MIN = 0;
    private static final double X_MAX = 400;

    private static final double PLATFORM_WIDTH = 20;
    private static final double PLATFORM_HEIGHT = 10;

    private static final double POS_X = 200;
    private static final double POS_Y = 700;

    private static final double CHANCE_ADDON = 1.0;

    private static final double CHANCE = 0.1;

    /**
     * Fields for the test class.
     */
    private Random random;

    /**
     * World used for testing.
     */
    private QueueWorld world;

    /**
     * The AddOnCreator for test the class.
     */
    private AddOnCreator addOnCreator;

    /**
     * The platform for testing.
     */
    private Platform platform;

    /**
     * The SpawnPoolCreator instance used for testing.
     */
    private SpawnPoolCreatorImpl spawnPoolCreator;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    void setUp() {
        this.random = new Random();
        this.world = new UpperWorld(new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX)));
        this.spawnPoolCreator = new SpawnPoolCreatorImpl(world);
        spawnPoolCreator.setSpawnPool(new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl()));
        this.addOnCreator = new AddOnCreatorImpl(new AddOnPoolEasy(spawnPoolCreator, CHANCE_ADDON));
        this.platform = new PlatformImpl(new Vector2dImpl(POS_X, POS_Y), PLATFORM_WIDTH, PLATFORM_HEIGHT,
                Optional.empty(), Optional.empty());
    }

    /**
     * Test for creating an add-on.
     */
    @Test
    void createAddOnTest() {
        this.addOnCreator.selectAddOn(random.nextDouble(1.0), this.platform);
        if (!this.world.getGadgets().isEmpty()) {
            final var gadget = this.world.removeFirstGadget().get();
            assertEquals(POS_X + ((PLATFORM_WIDTH - gadget.getWidth()) / 2), gadget.getPosX());
            assertEquals(POS_Y - gadget.getHeight(), gadget.getPosY());
        } else if (!this.world.getMoneys().isEmpty()) {
            final var money = this.world.removeFirstMoney().get();
            assertEquals(POS_X + ((PLATFORM_WIDTH - money.getWidth()) / 2), money.getPosX());
            assertEquals(POS_Y - money.getHeight(), money.getPosY());
        } else {
            final var monster = this.world.removeFirstMonster().get();
            assertEquals(POS_X + ((PLATFORM_WIDTH - monster.getWidth()) / 2), monster.getPosX());
            assertEquals(POS_Y - monster.getHeight(), monster.getPosY());
        }
    }

    /**
     * Test for setting a new add-on pool.
     */
    @Test
    void setAddOnPoolTest() {
        this.addOnCreator
                .setAddOnPool(new AddOnPoolMedium(this.spawnPoolCreator, CHANCE_ADDON));
        this.addOnCreator.selectAddOn(CHANCE, this.platform);
        assertFalse(this.world.getGadgets().isEmpty());
    }

}
