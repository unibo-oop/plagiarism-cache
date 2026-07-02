package it.unibo.javacrush.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import it.unibo.javacrush.common.AppEventType;
import it.unibo.javacrush.common.GameEvent;
import it.unibo.javacrush.controller.impl.AppControllerImpl;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GameMatchContext;
import it.unibo.javacrush.model.api.LevelConfig;
import it.unibo.javacrush.model.api.LevelManager;
import it.unibo.javacrush.model.api.MatchManager;
import it.unibo.javacrush.model.api.MoveEngine;
import it.unibo.javacrush.model.api.PhysicsHandler;
import it.unibo.javacrush.model.api.Session;
import it.unibo.javacrush.model.api.StallEngine;
import it.unibo.javacrush.powerup.api.PowerUpManager;
import it.unibo.javacrush.view.api.SceneManager;

/**
 * Test class for {@link AppControllerImpl}.
 */
@ExtendWith(MockitoExtension.class)
class AppControllerTest {

    private static final int LEVEL_NUMBER = 1;

    @Mock
    private SceneManager sceneManager;

    @Mock
    private LevelManager levelManager;

    @InjectMocks
    private AppControllerImpl appController;

    /**
     * Test the execution of the ExitGameCommand 
     * when the EXIT_GAME event is notified.
     */
    @Test
    void testExitGameCommand() {
        final var exitEvent = new GameEvent(AppEventType.EXIT_GAME, Optional.empty());

        appController.notifyEvent(exitEvent);

        verify(sceneManager).quit();
        assertTrue(appController.getCurrentGameController().isEmpty());
    }

    /**
     * Test the execution of the GoToMenuCommand 
     * when the GO_TO_MENU event is notified.
     */
    @Test
    void testGoToMenuCommand() {
        final var menuEvent = new GameEvent(AppEventType.GO_TO_MENU, Optional.empty());

        appController.notifyEvent(menuEvent);

        verify(sceneManager).showMenu();
        assertTrue(appController.getCurrentGameController().isEmpty());
    }

    /**
     * Test the execution of the GoToLevelsCommand 
     * when the GO_TO_LEVELS event is notified.
     */
    @Test
    void testGoToLevelsCommand() {
        final var levelsEvent = new GameEvent(AppEventType.GO_TO_LEVELS, Optional.empty());

        appController.notifyEvent(levelsEvent);

        verify(sceneManager).showLevels();
    }

    /**
     * Test the execution of the ShowInstructionsCommand 
     * when the SHOW_INSTRUCTIONS event is notified.
     */
    @Test
    void testShowInstructionsCommand() {
        final var instructionsEvent = new GameEvent(AppEventType.SHOW_INSTRUCTIONS, Optional.empty());

        appController.notifyEvent(instructionsEvent);

        verify(sceneManager).showInstructions();
    }

    /**
     * Test the execution of the StartLevelCommand
     * when the START_LEVEL event is notified.
     */
    @Test
    void testStartLevelCommand() {
        assertTrue(appController.getCurrentGameController().isEmpty());

        final GameMatchContext mockContext = mock(GameMatchContext.class);
        final LevelConfig mockConfig = mock(LevelConfig.class);
        final PhysicsHandler mockPhysics = mock(PhysicsHandler.class);
        final Board mockBoard = mock(Board.class);

        // We need to configure the context otherwise the constructor will
        // receive null argument
        when(mockContext.getLevelConfig()).thenReturn(mockConfig);
        when(mockContext.getPhysicsHandler()).thenReturn(mockPhysics);
        when(mockContext.getBoard()).thenReturn(mockBoard);
 
        when(mockContext.getSession()).thenReturn(mock(Session.class));
        when(mockContext.getStallEngine()).thenReturn(mock(StallEngine.class));
        when(mockContext.getMoveEngine()).thenReturn(mock(MoveEngine.class));
        when(mockContext.getMatchManager()).thenReturn(mock(MatchManager.class));
        when(mockConfig.powerUpManager()).thenReturn(mock(PowerUpManager.class));
        when(mockConfig.goals()).thenReturn(Map.of());

        when(levelManager.startMatch(LEVEL_NUMBER)).thenReturn(mockContext);
        final var startLevelEvent = new GameEvent(AppEventType.START_LEVEL, Optional.of(LEVEL_NUMBER));

        appController.notifyEvent(startLevelEvent);

        assertTrue(appController.getCurrentGameController().isPresent());
        verify(sceneManager).showGame(appController.getCurrentGameController().get());
    }

}
