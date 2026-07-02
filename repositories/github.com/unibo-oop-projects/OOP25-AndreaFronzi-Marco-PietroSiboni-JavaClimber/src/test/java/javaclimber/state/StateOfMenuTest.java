package javaclimber.state;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.controller.app.impl.MainControllerImpl;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.api.StateOfMenu;
import it.unibo.model.menu.impl.LaunchedGameState;
import it.unibo.view.app.impl.MainViewImpl;

/**
 * Test class for {@link StateOfMenu}.
 */
class StateOfMenuTest {

    /**
     * The menu instance used for testing the StateOfMenu class.
     */
    private Menu menu;

    /**
     * The state instance used for testing the class.
     */
    private StateOfMenu state;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        final var mainView = new MainViewImpl();
        final var mainController = new MainControllerImpl(mainView);
        this.menu = mainController.getMenu();
        this.menu.setState(new LaunchedGameState(this.menu));
        this.state = new LaunchedGameState(this.menu);
    }

    /**
     * Tests the execute method.
     */
    @Test
    void executeTest() {
        this.state.execute();
        assertTrue(this.menu.getLaunchedGame().get().isRunning());
    }

}
