package javaclimber.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.controller.app.impl.MainControllerImpl;
import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.launchedgame.impl.LaunchedGameImpl;
import it.unibo.model.launchedgame.impl.RunningState;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.RealWorld;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.world.impl.World;
import it.unibo.view.app.impl.MainViewImpl;

/**
 * Test class for {@link LaunchedGame}.
 */
class LaunchedGameTest {

    /**
     * The launched game instance used for testing the class.
     */
    private LaunchedGame launchedGame;

    /**
     * The menu instance used for testing.
     */
    private Menu menu;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        final var mainView = new MainViewImpl();
        final var mainController = new MainControllerImpl(mainView);
        this.menu = new MenuImpl(mainController);
        this.launchedGame = new LaunchedGameImpl(this.menu);
        this.menu.setLaunchedGame(this.launchedGame);
    }

    /**
     * Tests the setState method.
     */
    @Test
    void setStateTest() {
        final var state = new RunningState(launchedGame, null, null, null, null);
        this.launchedGame.setState(state);
        assertEquals(state, this.launchedGame.getState());
    }

    /**
     * Tests the getState method.
     */
    @Test
    void getterStateTest() {
        final var state = new RunningState(launchedGame, null, null, null, null);
        this.launchedGame.setState(state);
        assertEquals(state, this.launchedGame.getState());
    }

    /**
     *  Test the getWorld method.
     */
    @Test
    void getterWorldTest() {
        final var pos = new Vector2dImpl(0, 0);
        final var vel = new Vector2dImpl(0, 0);
        final var alien = new AlienImpl(pos, vel, 0, 0, null);
        final var bound = new BoundWorldImpl(new BoundY(0, 0), new Boundary(0, 0));
        final var realWorld = new RealWorld(alien, bound);
        final var upperWorld = new UpperWorld(bound);
        final var world = new World(upperWorld, realWorld);
        this.launchedGame.setWorld(world);
        assertEquals(world, this.launchedGame.getWorld().get());
    }

    /**
     * Tests the setWorld method.
     */
    @Test
    void setWorldTest() {
        final var pos = new Vector2dImpl(0, 0);
        final var vel = new Vector2dImpl(0, 0);
        final var alien = new AlienImpl(pos, vel, 0, 0, null);
        final var bound = new BoundWorldImpl(new BoundY(0, 0), new Boundary(0, 0));
        final var realWorld = new RealWorld(alien, bound);
        final var upperWorld = new UpperWorld(bound);
        final var world = new World(upperWorld, realWorld);
        this.launchedGame.setWorld(world);
        assertEquals(world, this.launchedGame.getWorld().get());
    }

    /**
     * Tests the isRunning method.
     */
    @Test
    void isRunningTest() {
        this.launchedGame.setRunning(true);
        assertTrue(this.launchedGame.isRunning());
    }

    /**
     * Tests the setRunning method.
     */
    @Test
    void setRunningTest() {
        this.launchedGame.setRunning(true);
        assertTrue(this.launchedGame.isRunning());
    }

    /**
     * Tests the getMenu method.
     */
    @Test
    void getterMenuTest() {
        assertEquals(this.menu, this.launchedGame.getMenu());
    }

}
