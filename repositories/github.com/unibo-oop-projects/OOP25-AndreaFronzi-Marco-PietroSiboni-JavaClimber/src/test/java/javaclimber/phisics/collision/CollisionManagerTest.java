package javaclimber.phisics.collision;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.controller.app.impl.MainControllerImpl;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.gameobj.impl.CoinImpl;
import it.unibo.model.gameobj.impl.EliCap;
import it.unibo.model.gameobj.impl.EnemyImpl;
import it.unibo.model.launchedgame.impl.LaunchedGameImpl;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.physics.collision.api.CollisionManager;
import it.unibo.model.physics.collision.impl.CollisionManagerImpl;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.RealWorld;
import it.unibo.view.app.impl.MainViewImpl;

/**
 * Test class for the {@link CollisionManagerImpl}.
 * This class contains unit tests to verify the correct behavior of the
 * collision detection mechanism:
 * verify that the collision logic correctly work and that when a collision
 * occurs the collided element is removed from the world.
 */
class CollisionManagerTest {

    private static final double X = 10;
    private static final double Y = 100;

    private static final double SPEED_X = 0;
    private static final double SPEED_Y = 30;

    private static final double WIDTH = 50;
    private static final double HEIGHT = 50;

    private static final double LEFT_SIDE = 0;
    private static final double RIGHT_SIDE = 100;
    private static final double UPPER_WORLD = 0;
    private static final double LOWER_WORLD = 100;

    /**
     * The {@link CollisionManager} instance used to detect collisions in the tests.
     */
    private CollisionManager collisionManager;

    /**
     * The {@link RealWorld} instance representing the game world in which the
     * collisions are detected and where GameObj stands.
     */
    private RealWorld realWorld;

    /**
     * The {@link Enemy}, {@link Coin}, and {@link Gadget} instances used in the
     * tests to verify the collision detection and the correct removal of collided
     * elements from the world.
     */
    private Enemy e;
    private Coin c;
    private Gadget g;

    /** Sets up the test environment before each test case. */
    @BeforeEach
    void setUp() {
        final ActiveUpgradesImpl upgrades = new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()));
        final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT,
                upgrades);
        final Boundary boundary = new Boundary(LEFT_SIDE, RIGHT_SIDE);
        this.collisionManager = new CollisionManagerImpl(boundary);
        this.realWorld = new RealWorld(alien,
                new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD), new Boundary(LEFT_SIDE, RIGHT_SIDE)));
        this.e = new EnemyImpl(HEIGHT, WIDTH, new Vector2dImpl(X, Y + HEIGHT - 1));
        this.c = new CoinImpl(HEIGHT, WIDTH, new Vector2dImpl(X, Y + HEIGHT - 1), new ScoreManagerImpl());
        this.g = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(X, Y + HEIGHT - 1));
    }

    /**
     * Tests the {@link CollisionManager#detectCollisions(RealWorld)} method to
     * verify that when the Alien collides with an Enemy, the Enemy is removed from
     * the RealWorld.
     */
    @Test
    void detectCollisionOnEnemyTest() {
        final LaunchedGameImpl lg = new LaunchedGameImpl(new MenuImpl(new MainControllerImpl(new MainViewImpl())));
        this.realWorld.addMonster(this.e);
        this.collisionManager.detectCollisions(realWorld, lg);
        assertFalse(this.realWorld.getMonsters().contains(this.e));
    }

    /**
     * Tests the {@link CollisionManager#detectCollisions(RealWorld)} method to
     * verify that when the Alien collides with a Coin, the Coin is removed from the
     * RealWorld.
     */
    @Test
    void detectCollisionOnCoinTest() {
        final LaunchedGameImpl lg = new LaunchedGameImpl(new MenuImpl(new MainControllerImpl(new MainViewImpl())));
        this.realWorld.addMoney(this.c);
        this.collisionManager.detectCollisions(realWorld, lg);
        assertFalse(this.realWorld.getMoneys().contains(this.c));
    }

    /**
     * Tests the {@link CollisionManager#detectCollisions(RealWorld)} method to
     * verify that when the Alien collides with a Gadget, the Gadget is removed from
     * the RealWorld.
     */
    @Test
    void detectCollisionOnGadgetTest() {
        final LaunchedGameImpl lg = new LaunchedGameImpl(new MenuImpl(new MainControllerImpl(new MainViewImpl())));
        this.realWorld.addGadget(this.g);
        this.collisionManager.detectCollisions(realWorld, lg);
        assertFalse(this.realWorld.getGadgets().contains(this.g));
    }
}
