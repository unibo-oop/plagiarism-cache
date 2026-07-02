package javaclimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.gameobj.impl.CoinImpl;
import it.unibo.model.gameobj.impl.EliCap;
import it.unibo.model.gameobj.impl.EnemyImpl;
import it.unibo.model.gameobj.platformbuilder.impl.PlatformBuilderImpl;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.physics.platformphysic.impl.HorizontalMovementBehavior;
import it.unibo.model.physics.platformphysic.impl.OnTouchDestroyBehavior;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.RealWorld;

/**
 * Test for the {@link RealWorld}.
 */
class RealWorldTest {

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 800;

    private static final double X_MIN = 0;
    private static final double X_MAX = 600;

    private static final double POS_X = 0;
    private static final double POS_Y = 0;

    private static final double VELOCITY_X = 0;
    private static final double VELOCITY_Y = 0;

    private static final double PLATFORM_WIDTH = 10;
    private static final double PLATFORM_HEIGHT = 10;

    private static final double MONSTER_WIDTH = 10;
    private static final double MONSTER_HEIGHT = 10;

    private static final double GADGET_WIDTH = 10;
    private static final double GADGET_HEIGHT = 10;

    private static final double MONEY_WIDTH = 10;
    private static final double MONEY_HEIGHT = 10;

    /**
     * The real world to test.
     */
    private GameWorld realWorld;

    /**
     * Set up the real world before each test.
     */
    @BeforeEach
    void setUpRealWorld() {
        this.realWorld = new RealWorld(
                new AlienImpl(new Vector2dImpl(POS_X, POS_Y), new Vector2dImpl(VELOCITY_X, VELOCITY_Y), PLATFORM_WIDTH,
                        PLATFORM_HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()))),
                new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX)));
    }

    /**
     * Create a static platform at the given position.
     * 
     * @param pos the position of the platform
     * @return the static platform
     */
    private Platform createStaticPlatform(final Vector2d pos) {
        final PlatformBuilderImpl platformBuilder = new PlatformBuilderImpl();
        return platformBuilder.at(pos).build();
    }

    /**
     * Create a moving platform at the given position.
     * 
     * @param pos the position of the platform
     * @return the moving platform
     */
    private Platform createMovingPlatform(final Vector2d pos) {
        final PlatformBuilderImpl platformBuilder = new PlatformBuilderImpl();
        return platformBuilder.at(pos).addMovementBehaviour(new HorizontalMovementBehavior(100)).build();
    }

    /**
     * Create an on-touch platform at the given position.
     * 
     * @param pos the position of the platform
     * @return the on-touch platform
     */
    private Platform createOnTouchPlatform(final Vector2d pos) {
        final PlatformBuilderImpl platformBuilder = new PlatformBuilderImpl();
        return platformBuilder.at(pos).addJumpBehaviour(new OnTouchDestroyBehavior()).build();
    }

    /**
     * Test for getting the alien.
     */
    @Test
    void getterAlienTest() {
        final Alien alien = this.realWorld.getAlien();
        assertEquals(alien, this.realWorld.getAlien());
        assertEquals(POS_X, alien.getPosX());
        assertEquals(POS_Y, alien.getPosY());
    }

    /**
     * Test for removing a static platform.
     */
    @Test
    void removeStaticPlatformTest() {
        final Platform platform = createStaticPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.realWorld.addStaticPlatform(platform);
        assertTrue(this.realWorld.removeStaticPlatform(platform));
    }

    /**
     * Test for removing a moving platform.
     */
    @Test
    void removeMovingPlatformTest() {
        final Platform platform = createMovingPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.realWorld.addMovingPlatform(platform);
        assertTrue(this.realWorld.removeMovingPlatform(platform));
    }

    /**
     * Test for removing an on-touch platform.
     */
    @Test
    void removeOnTouchPlatformTest() {
        final Platform platform = createOnTouchPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.realWorld.addOnTouchPlatform(platform);
        assertTrue(this.realWorld.removeOnTouchPlatform(platform));
    }

    /**
     * Test for removing a monster.
     */
    @Test
    void removeMonsterTest() {
        final Enemy monster = new EnemyImpl(MONSTER_WIDTH, MONSTER_HEIGHT, new Vector2dImpl(POS_X, POS_Y));
        this.realWorld.addMonster(monster);
        assertTrue(this.realWorld.removeMonster(monster));
    }

    /**
     * Test for removing a gadget.
     */
    @Test
    void removeGadgetTest() {
        final Gadget gadget = new EliCap(GADGET_WIDTH, GADGET_HEIGHT, new Vector2dImpl(POS_X, POS_Y));
        this.realWorld.addGadget(gadget);
        assertTrue(this.realWorld.removeGadget(gadget));
    }

    /**
     * Test for removing money.
     */
    @Test
    void removeMoneyTest() {
        final Coin money = new CoinImpl(MONEY_WIDTH, MONEY_HEIGHT, new Vector2dImpl(POS_X, POS_Y), null);
        this.realWorld.addMoney(money);
        assertTrue(this.realWorld.removeMoney(money));
    }
}
