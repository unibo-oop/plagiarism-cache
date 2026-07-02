package it.unibo;

import it.unibo.controller.GameLoop;
import it.unibo.controller.ExitController;
import it.unibo.controller.interfaces.ExitControllerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the ExitController class.
 * It verifies that the exit button click correctly stops the game
 * and triggers the event to go to the main menu.
 */
class ExitControllerTest {

    private ExitControllerInterface exitController;
    private GameListener listener;
    private GameLoop gameLoop;

    /**
     * Sets up the testing environment by creating mock objects for the GameListener
     * and GameLoop. This method is called before each test to ensure a clean setup.
     */
    @BeforeEach
    void setUp() {
        listener = mock(GameListener.class);
        gameLoop = mock(GameLoop.class);

        exitController = new ExitController(listener, gameLoop);
    }

    /**
     * Tests that the exit button click correctly stops the game and triggers
     * the event to go to the main menu. It ensures that the game is stopped and
     * the goToMainMenu event is triggered.
     */
    @Test
    void testOnExitClicked() {
        ArgumentCaptor<GameEvent> eventCaptor = ArgumentCaptor.forClass(GameEvent.class);
        exitController.onExitClicked();
        verify(gameLoop).stopGame();
        verify(listener).goToMainMenu(eventCaptor.capture());
        GameEvent capturedEvent = eventCaptor.getValue();
        
        assertNotNull(capturedEvent, "Captured event should not be null.");
        assertEquals(exitController, capturedEvent.getSource(), "The source of the GameEvent should match the ExitController.");
    }
}
