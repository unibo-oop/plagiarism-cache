package javaclimber.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.controller.app.impl.MainControllerImpl;
import it.unibo.model.launchedgame.impl.LaunchedGameImpl;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.menu.impl.MenuState;
import it.unibo.view.app.impl.MainViewImpl;

/**
 * Test class for {@link Menu}.
 */
class MenuTest {

    /**
     * The menu instance to be tested.
     */
    private Menu menu;

    /**
     * The main controller instance used for testing the menu.
     */
    private MainControllerImpl mainController;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        final var mainView = new MainViewImpl();
        this.mainController = new MainControllerImpl(mainView);
        this.menu = new MenuImpl(this.mainController);
    }

    /**
     * Tests the setState method.
     */
    @Test
    void setState() {
        final var state = new MenuState(this.menu);
        this.menu.setState(state);
        assertEquals(state, this.menu.getState());
    }

    /**
     * Tests the getState method.
     */
    @Test
    void getterState() {
        final var state = new MenuState(this.menu);
        this.menu.setState(state);
        assertEquals(state, this.menu.getState());
    }

    /**
     * Tests the getLaunchedGame method.
     */
    @Test
    void getterLaunchedGame() {
        final var launchedGame = new LaunchedGameImpl(this.menu);
        this.menu.setLaunchedGame(launchedGame);
        assertEquals(launchedGame, this.menu.getLaunchedGame().get());
    }

    /**
     * Tests the setLaunchedGame method.
     */
    @Test
    void setLaunchedGame() {
        final var launchedGame = new LaunchedGameImpl(this.menu);
        this.menu.setLaunchedGame(launchedGame);
        assertEquals(launchedGame, this.menu.getLaunchedGame().get());
    }

    /**
     * Tests the getMainController method.
     */
    @Test
    void getterMainController() {
        assertEquals(this.mainController, this.menu.getMainController());
    }

    /**
     * Tests the getInventory method.
     */
    @Test
    void getterInventory() {
        assertNotEquals(null, this.menu.getInventory());
    }

    /**
     * Tests the getShopManager method.
     */
    @Test
    void getterShopManager() {
        assertNotEquals(null, this.menu.getShopManager());
    }

    /**
     * Tests the getScoreManager method.
     */
    @Test
    void getterScoreManager() {
        assertNotEquals(null, this.menu.getScoreManager());
    }
}
