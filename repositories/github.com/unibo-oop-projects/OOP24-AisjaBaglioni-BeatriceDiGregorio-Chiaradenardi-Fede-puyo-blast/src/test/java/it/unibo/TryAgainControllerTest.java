package it.unibo;

import it.unibo.controller.GameLoop;
import it.unibo.controller.LevelManager;
import it.unibo.controller.TryAgainController;
import it.unibo.controller.interfaces.TryAgainControllerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the TryAgainController class.
 * It ensures that the "Try Again" button click correctly stops the game 
 * and starts a new game with the current level configuration.
 */
class TryAgainControllerTest {

    private TryAgainControllerInterface tryAgainController;
    private GameListener listener;
    private GameLoop gameLoop;
    private LevelManager.LevelConfig levelConfig;

    /**
     * Sets up the testing environment by creating mock objects for the GameListener, 
     * GameLoop, and LevelConfig. This method is called before each test to ensure 
     * a clean and consistent setup.
     */
    @BeforeEach
    void setUp() {
        listener = mock(GameListener.class);
        gameLoop = mock(GameLoop.class);
        levelConfig = mock(LevelManager.LevelConfig.class);

        tryAgainController = new TryAgainController(levelConfig, listener, gameLoop);
    }

    /**
     * Tests that the "Try Again" button click correctly stops the game and starts 
     * a new game with the current level configuration.
     * It verifies that both the game is stopped and the startGame event is triggered.
     */
    @Test
    void testHandleClick() {
        ArgumentCaptor<GameEvent> eventCaptor = ArgumentCaptor.forClass(GameEvent.class);
        tryAgainController.handleClick();
        verify(gameLoop).stopGame();
        verify(listener).startGame(eventCaptor.capture());
        GameEvent capturedEvent = eventCaptor.getValue();
        
        assertNotNull(capturedEvent, "Captured event should not be null.");
        assertEquals(tryAgainController, capturedEvent.getSource(), "The source of the GameEvent should match the TryAgainController.");
    }
}
