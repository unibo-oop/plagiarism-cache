package javaclimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.gameobj.impl.CoinImpl;
import it.unibo.model.gameobj.impl.EliCap;
import it.unibo.model.gameobj.impl.EnemyImpl;
import it.unibo.model.gameobj.platformbuilder.impl.PlatformBuilderImpl;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.physics.platformphysic.impl.HorizontalMovementBehavior;
import it.unibo.model.physics.platformphysic.impl.OnTouchDestroyBehavior;

/**
 * Test for the {@link QueueWorld}.
 */
class QueueWorldTest {

    private static final double POS_X = 0;
    private static final double POS_Y = 0;

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 800;

    private static final double X_MIN = 0;
    private static final double X_MAX = 600;

    private static final double WIDTH = 0;
    private static final double HEIGHT = 0;

    /**
     * The upper world to test.
     */
    private UpperWorld upperWorld;

    /**
     * Set up the upper world before each test.
     */
    @BeforeEach
    void setUpUpperWorld() {
        this.upperWorld = new UpperWorld(new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX)));
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
    private Platform createOnToucPlatform(final Vector2d pos) {
        final PlatformBuilderImpl platformBuilder = new PlatformBuilderImpl();
        return platformBuilder.at(pos).addJumpBehaviour(new OnTouchDestroyBehavior()).build();
    }

    /**
     * Test for adding a static platform.
     */
    @Test
    void addStaticPlatformTest() {
        final Platform platform = createStaticPlatform(new Vector2dImpl(POS_X, POS_Y));
        assertTrue(this.upperWorld.addStaticPlatform(platform));
    }

    /**
     * Test for adding a moving platform.
     */
    @Test
    void addMovingPlatformTest() {
        final Platform platform = createMovingPlatform(new Vector2dImpl(POS_X, POS_Y));
        assertTrue(this.upperWorld.addMovingPlatform(platform));
    }

    /**
     * Test for adding an on-touch platform.
     */
    @Test
    void addOnTouchPlatformTest() {
        final Platform platform = createOnToucPlatform(new Vector2dImpl(POS_X, POS_Y));
        assertTrue(this.upperWorld.addOnTouchPlatform(platform));
    }

    /**
     * Test for adding a monster.
     */
    @Test
    void addMonsterTest() {
        final Enemy monster = new EnemyImpl(HEIGHT, WIDTH, new Vector2dImpl(POS_X, POS_Y));
        assertTrue(this.upperWorld.addMonster(monster));
    }

    /**
     * Test for adding a gadget.
     */
    @Test
    void addGadgetTest() {
        final Gadget gadget = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(POS_X, POS_Y));
        assertTrue(this.upperWorld.addGadget(gadget));
    }

    /**
     * Test for adding money.
     */
    @Test
    void addMoneyTest() {
        final Coin money = new CoinImpl(HEIGHT, WIDTH, new Vector2dImpl(POS_X, POS_Y), null);
        assertTrue(this.upperWorld.addMoney(money));
    }

    /**
     * Test for removing the first static platform.
     */
    @Test
    void removeFirstStaticPlatformTest() {
        final Platform platform = createStaticPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addStaticPlatform(platform);
        assertEquals(platform, this.upperWorld.removeFirstStaticPlatform().get());
    }

    /**
     * Test for removing the first moving platform.
     */
    @Test
    void removeFirstMovingPlatformTest() {
        final Platform platform = createMovingPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addMovingPlatform(platform);
        assertEquals(platform, this.upperWorld.removeFirstMovingPlatform().get());
    }

    /**
     * Test for removing the first on-touch platform.
     */
    @Test
    void removeFirstOnTouchPlatformTest() {
        final Platform platform = createOnToucPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addOnTouchPlatform(platform);
        assertEquals(platform, this.upperWorld.removeFirstOnTouchPlatform().get());
    }

    /**
     * Test for removing the first monster.
     */
    @Test
    void removeFirstMonsterTest() {
        final Enemy monster = new EnemyImpl(HEIGHT, WIDTH, new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addMonster(monster);
        assertEquals(monster, this.upperWorld.removeFirstMonster().get());
    }

    /**
     * Test for removing the first gadget.
     */
    @Test
    void removeFirstGadgetTest() {
        final Gadget gadget = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addGadget(gadget);
        assertEquals(gadget, this.upperWorld.removeFirstGadget().get());
    }

    /**
     * Test for removing the first coin.
     */
    @Test
    void removeFirstCoinTest() {
        final Coin coin = new CoinImpl(HEIGHT, WIDTH, new Vector2dImpl(POS_X, POS_Y), null);
        this.upperWorld.addMoney(coin);
        assertEquals(coin, this.upperWorld.removeFirstMoney().get());
    }

    /**
     * Test for getting the static platforms.
     */
    @Test
    void getterStaticPlatformsTest() {
        final Platform platform = createStaticPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addStaticPlatform(platform);
        assertEquals(1, this.upperWorld.getStaticPlatforms().size());
    }

    /**
     * Test for getting the moving platforms.
     */
    @Test
    void getterMovingPlatformsTest() {
        final Platform platform = createMovingPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addMovingPlatform(platform);
        assertEquals(1, this.upperWorld.getMovingPlatforms().size());
    }

    /**
     * Test for getting the on-touch platforms.
     */
    @Test
    void getterOnTouchPlatformsTest() {
        final Platform platform = createOnToucPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addOnTouchPlatform(platform);
        assertEquals(1, this.upperWorld.getOnTouchPlatforms().size());
    }

    /**
     * Test for getting the monsters.
     */
    @Test
    void getterMonstersTest() {
        final Enemy monster = new EnemyImpl(HEIGHT, WIDTH, new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addMonster(monster);
        assertEquals(1, this.upperWorld.getMonsters().size());
    }

    /**
     * Test for getting the gadgets.
     */
    @Test
    void getterGadgetsTest() {
        final Gadget gadget = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addGadget(gadget);
        assertEquals(1, this.upperWorld.getGadgets().size());
    }

    /**
     * Test for getting the coins.
     */
    @Test
    void getterCoinsTest() {
        final Coin coin = new CoinImpl(HEIGHT, WIDTH, new Vector2dImpl(POS_X, POS_Y), null);
        this.upperWorld.addMoney(coin);
        assertEquals(1, this.upperWorld.getMoneys().size());
    }

}
