package javaclimber.phisics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.controller.app.impl.MainControllerImpl;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.gameobj.impl.CoinImpl;
import it.unibo.model.gameobj.impl.EliCap;
import it.unibo.model.launchedgame.impl.LaunchedGameImpl;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.physics.alienphysic.impl.AlienEliCapPhysic;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.RealWorld;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.GameObjDimension;
import it.unibo.view.app.impl.MainViewImpl;

/** Test class for {@link AlienEliCapPhysic}. */
class AlienEliCapPhysicTest {

    private static final double EPSILON = 0.001;

    private static final double X = 10;
    private static final double Y = 100;

    private static final double X1 = 10;
    private static final double Y1 = -400;

    private static final double X2 = 10;

    private static final double X3 = 10;

    private static final double WIDTH = 0;
    private static final double HEIGHT = 0;

    private static final double SPEED_X = 0;
    private static final double SPEED_Y = 0;

    private static final double ELICAP_SPEED_X = 0;
    private static final double ELICAP_SPEED_Y = -500;

    private static final double SPEED_AFTER_ELICAP_AND_GRAVITY_X = 0;

    private static final double LEFT_SIDE = 0;
    private static final double RIGHT_SIDE = 100;

    private static final double UPPER_WORLD = 0;
    private static final double LOWER_WORLD = 100;

    private static final double DT = 1;
    private static final double DT2 = 3;
    private static final double DT3 = 4;

    private static final int COINS_NUMBER = 1;

    /** Verify that AlienEliCapPhysic is set correctly when EliCap is collected and that updates work as expected. */
    @Test
    void testAlienEliCapPhysicBehavior() {
        final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD), new Boundary(LEFT_SIDE, RIGHT_SIDE));
        final Gadget eliCap = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(X, Y));
        final ActiveUpgradesImpl upgrades = new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()));
        final Alien alien = new AlienImpl(
                new Vector2dImpl(X, Y),
                new Vector2dImpl(SPEED_X, SPEED_Y),
                WIDTH,
                HEIGHT,
                upgrades
        );
        // velocita del mostra quando prende elicap = 10
        eliCap.onCollect(alien, new RealWorld(alien, boundary));

        assertEquals(SPEED_Y, alien.getSpeedY(), EPSILON);
        assertEquals(SPEED_X, alien.getSpeedX(), EPSILON);

        final MainControllerImpl mainController = new MainControllerImpl(new MainViewImpl());
        final MenuImpl menu = new MenuImpl(mainController);
        final LaunchedGameImpl launchedGame = new LaunchedGameImpl(menu);
        alien.updatePosition(DT, boundary, launchedGame);
        assertEquals(X1, alien.getPosX(), EPSILON);
        assertEquals(Y1, alien.getPosY(), EPSILON);
        assertEquals(ELICAP_SPEED_X, alien.getSpeedX(), EPSILON);
        assertEquals(ELICAP_SPEED_Y, alien.getSpeedY(), EPSILON);
    }

    /** Test to verify an update equal to the EliCap time interval updates position and speed. */
    @Test
    void testAlienEliCapPhysicBehaviorUpdatingWithTimeInterval() {
        final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD), new Boundary(LEFT_SIDE, RIGHT_SIDE));
        final Gadget eliCap = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(X, Y));
        final ActiveUpgradesImpl upgrades = new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()));
        final Alien alien = new AlienImpl(
                new Vector2dImpl(X, Y),
                new Vector2dImpl(SPEED_X, SPEED_Y),
                WIDTH,
                HEIGHT,
                upgrades
        );
        eliCap.onCollect(alien, new RealWorld(alien, boundary));
        assertEquals(SPEED_Y, alien.getSpeedY(), EPSILON);
        assertEquals(SPEED_X, alien.getSpeedX(), EPSILON);

        final MainControllerImpl mainController = new MainControllerImpl(new MainViewImpl());
        final MenuImpl menu = new MenuImpl(mainController);
        final LaunchedGameImpl launchedGame = new LaunchedGameImpl(menu);

        alien.updatePosition(DT2, boundary, launchedGame);
        assertEquals(X2, alien.getPosX(), EPSILON);
        assertEquals(Y + ELICAP_SPEED_Y * DT2, alien.getPosY(), EPSILON);
        assertEquals(ELICAP_SPEED_X, alien.getSpeedX(), EPSILON);
        assertEquals(ELICAP_SPEED_Y, alien.getSpeedY(), EPSILON);
    }

    /** Test to verify an update greater than the EliCap time interval updates position/speed and restores normal physic. */
    @Test
    void testAlienEliCapPhysicBehaviorUpdatingWithMoreThanTimeInterval() {
        final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD), new Boundary(LEFT_SIDE, RIGHT_SIDE));
        final Gadget eliCap = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(X, Y));
        final ActiveUpgradesImpl upgrades = new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()));
        final Alien alien = new AlienImpl(
                new Vector2dImpl(X, Y),
                new Vector2dImpl(SPEED_X, SPEED_Y),
                WIDTH,
                HEIGHT,
                upgrades
        );

        eliCap.onCollect(alien, new RealWorld(alien, boundary));
        final MainControllerImpl mainController = new MainControllerImpl(new MainViewImpl());
        final MenuImpl menu = new MenuImpl(mainController);
        final LaunchedGameImpl launchedGame = new LaunchedGameImpl(menu);
        alien.updatePosition(DT3, boundary, launchedGame);

        assertEquals(X3, alien.getPosX(), EPSILON);
        assertEquals(Y + ELICAP_SPEED_Y * DT2 + (ELICAP_SPEED_Y + (GameObjDimension.GRAVITY * (DT3 - DT2))) * (DT3 - DT2),
            alien.getPosY(), EPSILON);
        assertEquals(SPEED_AFTER_ELICAP_AND_GRAVITY_X, alien.getSpeedX(), EPSILON);
        assertEquals(ELICAP_SPEED_Y + GameObjDimension.GRAVITY * (DT3 - DT2), alien.getSpeedY(), EPSILON);
    }

    /** Tests {@link AlienEliCapPhysic#hitCoin(Coin, ActiveUpgrades, GameWorld)} and verifies coin count update. */
    @Test
    void testHitCoin() {
        final ScoreManager scoreManager = new ScoreManagerImpl();
        final Coin coin = new CoinImpl(HEIGHT, WIDTH, new Vector2dImpl(X, Y + HEIGHT), scoreManager);
        final Gadget eliCap = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(X, Y));
        final ActiveUpgradesImpl upgrades = new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()));
        final Alien alien = new AlienImpl(
                new Vector2dImpl(X, Y),
                new Vector2dImpl(SPEED_X, SPEED_Y),
                WIDTH,
                HEIGHT,
                upgrades
        );
        final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD), new Boundary(LEFT_SIDE, RIGHT_SIDE));

        eliCap.onCollect(alien, new RealWorld(alien, boundary));

        final MainControllerImpl mainController = new MainControllerImpl(new MainViewImpl());
        final MenuImpl menu = new MenuImpl(mainController);
        final LaunchedGameImpl launchedGame = new LaunchedGameImpl(menu);
        alien.notifyCollision(coin, boundary.getBoundX(), new RealWorld(alien, boundary), launchedGame);

        assertEquals(COINS_NUMBER, scoreManager.getCoins(), EPSILON);
    }
}
