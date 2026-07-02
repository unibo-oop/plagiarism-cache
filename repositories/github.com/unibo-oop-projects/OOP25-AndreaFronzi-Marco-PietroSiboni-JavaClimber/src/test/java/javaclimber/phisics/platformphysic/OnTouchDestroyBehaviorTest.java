package javaclimber.phisics.platformphysic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.controller.app.impl.MainControllerImpl;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.gameobj.impl.PlatformImpl;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.launchedgame.impl.LaunchedGameImpl;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.physics.alienphysic.api.AlienPhysic;
import it.unibo.model.physics.alienphysic.impl.AlienNormalPhysic;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.physics.platformphysic.impl.OnTouchDestroyBehavior;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.RealWorld;
import it.unibo.view.app.impl.MainViewImpl;

/**
 * Test class for {@link OnTouchDestroyBehavior}.
 */
class OnTouchDestroyBehaviorTest {

    private static final double WIDTH = 10;
    private static final double HEIGHT = 10;

    private static final double PLATFORM_WIDTH = 10;
    private static final double PLATFORM_HEIGHT = 10;

    private static final double INITIAL_Y = 40;
    private static final double INITIAL_X = 10;

    private static final double SPEED_X = 0;
    private static final double SPEED_Y = 10;

    private static final double LEFT_BOUNDARY = 0;
    private static final double RIGHT_BOUNDARY = 100;

    private static final double UPPER_BOUND = 0;
    private static final double LOWER_BOUND = 100;

    private static final double PLATFORM1_X = 50;
    private static final double PLATFORM1_Y = 100;
    private static final double PLATFORM2_X = 50;
    private static final double PLATFORM2_Y = 50;

    /**
     * The {@link Alien}.
     */
    private Alien alien;

    /**
     * The {@link RealWorld} in which the test will be executed.
     */
    private RealWorld world;

    /**
     * The {@link Platform} that will be removed when touched by the alien.
     */
    private Platform platformToRemove;

    /**
     * Set up the test environment by initializing the alien, the world, and the
     * platforms before each test case.
     */
    @BeforeEach
    void setUp() {
        final ActiveUpgrades upgrades = new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()));
        this.alien = new AlienImpl(new Vector2dImpl(INITIAL_X, INITIAL_Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH,
                HEIGHT, upgrades);
        this.world = new RealWorld(this.alien,
                new BoundWorldImpl(new BoundY(UPPER_BOUND, LOWER_BOUND), new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY)));
        this.platformToRemove = new PlatformImpl(new Vector2dImpl(PLATFORM2_X, PLATFORM2_Y), PLATFORM_WIDTH,
                PLATFORM_HEIGHT, Optional.empty(), Optional.of(new OnTouchDestroyBehavior()));

        this.world.addOnTouchPlatform(new PlatformImpl(new Vector2dImpl(PLATFORM1_X, PLATFORM1_Y), PLATFORM_WIDTH,
                PLATFORM_HEIGHT, Optional.empty(), Optional.empty()));
        this.world.addOnTouchPlatform(this.platformToRemove);
    }

    /**
     * Verify that when the alien touches the {@link Platform} with the
     * {@link OnTouchDestroyBehavior}, the platform is removed from the world.
     */
    @Test
    void verifyPlatformDestruction() {
        final ActiveUpgrades upgrades = new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()));
        final AlienPhysic physic = new AlienNormalPhysic();
        final Boundary boundary = new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY);
        final LaunchedGame launchedGame = new LaunchedGameImpl(new MenuImpl(new MainControllerImpl(new MainViewImpl())));

        this.alien.setPhysic(physic);

        assertEquals(this.platformToRemove.getPosY(), alien.getPosY() + HEIGHT);

        this.platformToRemove.onHitBy(this.alien, physic, boundary, this.world, launchedGame, upgrades);
        assertFalse(this.world.getOnTouchPlatforms().contains(this.platformToRemove));
    }
}
