package javaclimber.phisics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.unibo.controller.app.api.MainController;
import it.unibo.controller.app.impl.MainControllerImpl;
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
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.launchedgame.impl.EndState;
import it.unibo.model.launchedgame.impl.LaunchedGameImpl;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.physics.alienphysic.api.AlienPhysic;
import it.unibo.model.physics.alienphysic.impl.AlienNormalPhysic;
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
import it.unibo.view.app.api.MainView;
import it.unibo.view.app.impl.MainViewImpl;

import org.junit.jupiter.api.Test;

/** Test class for {@link AlienNormalPhysic}. */
class AlienNormalPhysicTest {

        private static final double EPSILON = 0.001;

        private static final double X = 10;
        private static final double Y = 0;

        private static final double X1 = 90;

        private static final double SPEED_X = 0;
        private static final double SPEED_Y = 0;

        private static final double SPEEDY_WITH_ELICAP = -500;
        private static final double SPEED1_Y = 50;
        private static final double SPEED2_X = -200;

        private static final double WIDTH = 50;
        private static final double HEIGHT = 50;
        private static final double WIDTH1 = 10;

        private static final double LEFT_BOUNDARY = 0;
        private static final double RIGHT_BOUNDARY = 100;

        private static final double UPPER_WORLD = 0;
        private static final double LOWER_WORLD = 100;

        private static final double DT = 0.1;

        private static final int COINS_NUMBER = 1;

        /**
         * Test the
         * {@link AlienNormalPhysic#update(Alien, double, BoundWorld, ActiveUpgrades, LaunchedGame)}
         * method to verify expected vertical movement behavior.
         */
        @Test
        void testUpdateAlienPosition() {
                final ActiveUpgrades activeUpgrades = new ActiveUpgradesImpl(
                                new InventoryImpl(new ShopItemFactoryImpl()));
                final AlienPhysic physic = new AlienNormalPhysic();
                final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH,
                                HEIGHT,
                                activeUpgrades);
                assertEquals(0, alien.getSpeedY(), EPSILON);
                final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD),
                                new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY));
                physic.update(alien, DT, boundary, activeUpgrades,
                                new LaunchedGameImpl(new MenuImpl(new MainControllerImpl(new MainViewImpl()))));
                assertEquals(GameObjDimension.GRAVITY * DT, alien.getSpeedY(), EPSILON);
                assertEquals(GameObjDimension.GRAVITY * DT * DT, alien.getPosY(), EPSILON);
        }

        /**
         * Tests the behavior of the
         * {@link AlienNormalPhysic#update(Alien, double, BoundWorld, ActiveUpgrades, LaunchedGame)}
         * method.
         * It verifies that Pacman effect correctly repositions the alien to the left
         * edge of the boundary.
         */
        @Test
        void testRightToLeftPacmanEffect() {
                final ActiveUpgrades activeUpgrades = new ActiveUpgradesImpl(
                                new InventoryImpl(new ShopItemFactoryImpl()));
                final AlienPhysic physic = new AlienNormalPhysic();
                final Alien alien = new AlienImpl(new Vector2dImpl(X1, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH,
                                HEIGHT,
                                activeUpgrades);
                alien.moveRight();
                final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD),
                                new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY));
                physic.update(alien, DT, boundary, activeUpgrades,
                                new LaunchedGameImpl(new MenuImpl(new MainControllerImpl(new MainViewImpl()))));
                assertEquals(LEFT_BOUNDARY, alien.getPosX(), EPSILON);
        }

        /**
         * Tests the behavior of the
         * {@link AlienNormalPhysic#update(Alien, double, BoundWorld, ActiveUpgrades, LaunchedGame)}
         * method.
         * It verifies that Pacman effect correctly repositions the alien to the right
         * edge of the boundary.
         */
        @Test
        void testLeftToRightPacmanEffect() {
                final ActiveUpgrades activeUpgrades = new ActiveUpgradesImpl(
                                new InventoryImpl(new ShopItemFactoryImpl()));
                final AlienPhysic physic = new AlienNormalPhysic();
                final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED2_X, SPEED_Y), WIDTH1,
                                HEIGHT,
                                activeUpgrades);
                alien.moveLeft();
                final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD),
                                new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY));
                physic.update(alien, DT, boundary, activeUpgrades,
                                new LaunchedGameImpl(new MenuImpl(new MainControllerImpl(new MainViewImpl()))));
                // debug: replaced System.out.println with no-op
                assertEquals(RIGHT_BOUNDARY - WIDTH1, alien.getPosX(), EPSILON);
        }

        /**
         * Tests the
         * {@link AlienNormalPhysic#hitEnemy(Alien, Enemy, LaunchedGame, ActiveUpgrades)}
         * method. Verify wheter, after the touch, is setted the new vertical speed and
         * if the position update deal with the logic.
         */
        @Test
        void testHitEnemy() {
                final ActiveUpgrades activeUpgrades = new ActiveUpgradesImpl(
                                new InventoryImpl(new ShopItemFactoryImpl()));
                final AlienPhysic physic = new AlienNormalPhysic();
                final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED1_Y), WIDTH,
                                HEIGHT,
                                activeUpgrades);
                final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD),
                                new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY));
                final Enemy enemy = new EnemyImpl(HEIGHT, WIDTH, new Vector2dImpl(X, Y + HEIGHT));

                // before updating position
                physic.hitEnemy(alien, enemy, new RealWorld(alien, boundary),
                                new LaunchedGameImpl(new MenuImpl(new MainControllerImpl(new MainViewImpl()))),
                                activeUpgrades);
                assertEquals(alien.getPosY(), enemy.getPosY() - alien.getHeight(), EPSILON);
                assertEquals(GameObjDimension.JUMP_ALIEN_SPEED_Y, alien.getSpeedY(), EPSILON);

                // after updating position
                alien.updatePosition(DT, boundary,
                                new LaunchedGameImpl(new MenuImpl(new MainControllerImpl(new MainViewImpl()))));
                assertEquals((GameObjDimension.JUMP_ALIEN_SPEED_Y + GameObjDimension.GRAVITY * DT) * DT + Y,
                                alien.getPosY(),
                                EPSILON);
        }

        /**
         * Tests the {@link AlienNormalPhysic#hitCoin(Coin, ActiveUpgrades, GameWorld)}
         * method. Verify wheter, after the touch, is updated the number of coin
         * collected.
         */
        @Test
        void testHitCoin() {
                final ActiveUpgrades activeUpgrades = new ActiveUpgradesImpl(
                                new InventoryImpl(new ShopItemFactoryImpl()));
                final AlienPhysic physic = new AlienNormalPhysic();
                final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH,
                                HEIGHT,
                                activeUpgrades);
                final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD),
                                new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY));
                final ScoreManager scoreManager = new ScoreManagerImpl();
                final Coin coin = new CoinImpl(HEIGHT, WIDTH, new Vector2dImpl(X, Y + HEIGHT), scoreManager);

                physic.hitCoin(coin, activeUpgrades, new RealWorld(alien, boundary));

                assertEquals(COINS_NUMBER, scoreManager.getCoins(), EPSILON);
        }

        /**
         * Tests the {@link AlienNormalPhysic#hitGadget(Alien, Gadget, ActiveUpgrades)}
         * method.
         */
        @Test
        void testHitGadget() {
                final ActiveUpgrades activeUpgrades = new ActiveUpgradesImpl(
                                new InventoryImpl(new ShopItemFactoryImpl()));
                final AlienPhysic physic = new AlienNormalPhysic();
                final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED1_Y), WIDTH,
                                HEIGHT,
                                activeUpgrades);
                final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD),
                                new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY));
                final Gadget eliCap = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(X, Y + HEIGHT));

                physic.hitGadget(alien, eliCap, new RealWorld(alien, boundary));
                assertEquals(alien.getPosY(), eliCap.getPosY() - alien.getHeight(), EPSILON);
                assertEquals(SPEED1_Y, alien.getSpeedY(), EPSILON);

                alien.updatePosition(DT, boundary,
                                new LaunchedGameImpl(new MenuImpl(new MainControllerImpl(new MainViewImpl()))));
                assertEquals(SPEEDY_WITH_ELICAP, alien.getSpeedY(), EPSILON);
                assertEquals(Y + SPEEDY_WITH_ELICAP * DT, alien.getPosY(), EPSILON);
        }

        /**
         * Tests the
         * {@link AlienNormalPhysic#hitPlatform(Alien, Platform, Boundary, ActiveUpgrades)}
         * method.
         */
        @Test
        void testHitPlatform() {
                final ActiveUpgrades activeUpgrades = new ActiveUpgradesImpl(
                                new InventoryImpl(new ShopItemFactoryImpl()));
                final AlienPhysic physic = new AlienNormalPhysic();
                final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED1_Y), WIDTH,
                                HEIGHT,
                                activeUpgrades);
                final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD),
                                new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY));
                final PlatformBuilderImpl platformBuilder = new PlatformBuilderImpl();
                final Platform platform = platformBuilder
                                .at(new Vector2dImpl(X, Y + HEIGHT))
                                .size(WIDTH, HEIGHT)
                                .build();

                physic.hitPlatform(alien, platform, boundary.getBoundX(), new RealWorld(alien, boundary),
                                activeUpgrades);
                assertEquals(alien.getPosY(), platform.getPosY() - alien.getHeight(), EPSILON);
                assertEquals(GameObjDimension.JUMP_ALIEN_SPEED_Y, alien.getSpeedY(), EPSILON);
        }

        /**
         * Tests the
         * {@link AlienNormalPhysic#hitEnemy(Alien, Enemy, RealWorld, LaunchedGame, ActiveUpgrades)}
         * method to verify that the game transitions to an end state when the alien
         * collides with an enemy.
         */
        @Test
        void testAlienDeadOnEnemyCollision() {
                final ActiveUpgrades activeUpgrades = new ActiveUpgradesImpl(
                                new InventoryImpl(new ShopItemFactoryImpl()));
                final AlienPhysic physic = new AlienNormalPhysic();
                final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, -SPEED1_Y), WIDTH,
                                HEIGHT,
                                activeUpgrades);
                final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD),
                                new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY));
                final Enemy enemy = new EnemyImpl(HEIGHT, WIDTH, new Vector2dImpl(X, Y + HEIGHT));
                final MainController dummyController = new MainController() {
                        @Override
                        public void setView(final MainView view) {
                        }

                        @Override
                        public void openMenuView() {
                        }

                        @Override
                        public void launchGame() {
                        }

                        @Override
                        public void openInventoryView() {
                        }

                        @Override
                        public void openShopView() {
                        }

                        @Override
                        public void openEndView() {
                        }

                        @Override
                        public void openPauseView() {
                        }

                        @Override
                        public void saveProgress() {
                        }

                        @Override
                        public Menu getMenu() {
                                return null;
                        }
                };

                final MenuImpl menu = new MenuImpl(dummyController);
                final LaunchedGame game = new LaunchedGameImpl(menu);

                // before updating position: this collision should end the game
                physic.hitEnemy(alien, enemy, new RealWorld(alien, boundary), game, activeUpgrades);
                assertTrue(game.getState() instanceof EndState);
        }
}
