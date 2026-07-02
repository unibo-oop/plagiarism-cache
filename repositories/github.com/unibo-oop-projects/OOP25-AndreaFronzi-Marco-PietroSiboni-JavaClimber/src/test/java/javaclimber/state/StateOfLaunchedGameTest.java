package javaclimber.state;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.controller.app.impl.MainControllerImpl;
import it.unibo.model.camera.impl.AltitudeManager;
import it.unibo.model.launchedgame.impl.InitialState;
import it.unibo.model.launchedgame.impl.LaunchedGameImpl;
import it.unibo.model.launchedgame.impl.RunningState;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.physics.collision.impl.CollisionManagerImpl;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.view.app.impl.MainViewImpl;

/**
 * Test class for {@link StateOfLaunchedGame}.
 */
class StateOfLaunchedGameTest {

    private static final double DELTA = 0.05;

    /**
     * The launched game instance used for testing the StateOfLaunchedGame class.
     */
    private LaunchedGameImpl launchedGame;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        final var view = new MainViewImpl();
        final var controller = new MainControllerImpl(view);
        final var menu = new MenuImpl(controller);
        this.launchedGame = new LaunchedGameImpl(menu);
    }

    /**
     * Tests the execute method.
     */
    @Test
    void executeTest() {
        final var initialState = new InitialState(this.launchedGame);
        this.launchedGame.setState(initialState);
        final var runningState = new RunningState(this.launchedGame, this.launchedGame.getWorld().get(),
                new CollisionManagerImpl(
                        this.launchedGame.getWorld().get().getUpperWorld().getBoundWorld().getBoundX()),
                new AltitudeManager(this.launchedGame.getWorld().get().getRealWorld().getAlien(), 100),
                new ScoreManagerImpl());
        final var alienPosY = this.launchedGame.getWorld().get().getRealWorld().getAlien().getPosY();
        this.launchedGame.setState(runningState);
        this.launchedGame.getState().execute(DELTA);
        assertNotEquals(alienPosY, this.launchedGame.getWorld().get().getRealWorld().getAlien().getPosY());
    }

    /**
     * Tests the onEnter method.
     */
    @Test
    void onEnterTest() {
        final var state = new InitialState(launchedGame);
        state.onEnter();
        assertTrue(this.launchedGame.isRunning());
    }
}
