package it.unibo.javacrush.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.controller.impl.GameControllerImpl;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GameMatchContext;
import it.unibo.javacrush.model.api.LevelConfig;
import it.unibo.javacrush.model.api.Match;
import it.unibo.javacrush.model.api.MatchManager;
import it.unibo.javacrush.model.api.MoveEngine;
import it.unibo.javacrush.model.api.PhysicsHandler;
import it.unibo.javacrush.model.api.Session;
import it.unibo.javacrush.model.api.StallEngine;
import it.unibo.javacrush.powerup.api.PowerUpManager;
import it.unibo.javacrush.view.api.GameView;

/**
 * Test class for {@link GameControllerImpl}.
 */
@ExtendWith(MockitoExtension.class)
class GameControllerTest {
    @Mock
    private GameView view;
    @Mock
    private GameMatchContext gameContext;
    @Mock
    private Board board;
    @Mock
    private PhysicsHandler physics;
    @Mock
    private LevelConfig levelConfig;
    @Mock
    private Session session;
    @Mock
    private StallEngine stallEngine;
    @Mock
    private MoveEngine moveEngine;
    @Mock
    private MatchManager matchManager;
    @Mock
    private PowerUpManager powerUpManager;

    private GameControllerImpl gameController;

    @BeforeEach
    void setUp() {
        when(gameContext.getBoard()).thenReturn(board);
        when(gameContext.getPhysicsHandler()).thenReturn(physics);
        when(gameContext.getLevelConfig()).thenReturn(levelConfig);
        when(gameContext.getSession()).thenReturn(session);
        when(gameContext.getStallEngine()).thenReturn(stallEngine);
        when(gameContext.getMoveEngine()).thenReturn(moveEngine);
        when(gameContext.getMatchManager()).thenReturn(matchManager);

        when(levelConfig.goals()).thenReturn(java.util.Map.of());
        when(levelConfig.powerUpManager()).thenReturn(powerUpManager);

        this.gameController = new GameControllerImpl(this.gameContext, this.view);
    }

    /**
     * Test the hit method, in the normal version.
     */
    @Test
    void testNormalHit() {
        final Position pos1 = new Position(0, 0);
        final Position pos2 = new Position(0, 1);

        when(this.moveEngine.canSwap(this.board, pos1, pos2)).thenReturn(true);
        when(this.matchManager.findAllMatches(this.board)).thenReturn(Set.of());

        boolean result = this.gameController.hit(pos1);
        assertFalse(result, "We cannot swap the cell with itself");

        result = this.gameController.hit(pos2);
        assertTrue(result, "The cells are swapped");

        verify(this.moveEngine).canSwap(this.board, pos1, pos2);
        verify(this.board).swapCells(pos1, pos2);
        verify(this.session).decreaseMoves();
    }

    /**
     * Test the applyGravity method when the board changes.
     */
    @Test
    void testGravityWhenBoardChanges() {
        when(this.physics.update(this.board)).thenReturn(true);

        final boolean result = this.gameController.applyGravity();
        assertTrue(result, "The board has changed");

        verify(this.physics).update(this.board);
        verify(this.view).updateView();
    }

    /**
     * Test the applyGravity method when the board 
     * is stable and without matches.
     */
    @Test
    void testGravityWhenBoardIsStableAndNoMatches() {
        when(this.physics.update(this.board)).thenReturn(false);
        when(this.matchManager.findAllMatches(this.board)).thenReturn(Set.of());

        final boolean result = this.gameController.applyGravity();
        assertFalse(result, "The board is stable");

        verify(this.physics).update(this.board);
        verify(this.stallEngine).resolveStall(this.board);
    }

    /**
     * Test the applyGravity method when the board is stable 
     * but there is at least one match.
     */
    @Test
    void testGravityWhenNewMatchesAreFound() {
        when(this.physics.update(this.board)).thenReturn(false);

        final Set<Match> mutableSet = new HashSet<>(Set.of(mock(Match.class)));

        when(this.matchManager.findAllMatches(this.board)).thenReturn(mutableSet);

        final boolean result = this.gameController.applyGravity();
        assertTrue(result, "Some matches have been found and handled");
        verify(this.view).updateView();
    }

    /**
     * Test the hit method when a PowerUp is selected.
     */
    @Test
    void testPowerUpHit() {
        when(this.powerUpManager.isPowerUpSelected()).thenReturn(true);
        when(this.powerUpManager.applyPowerUp(this.board, new Position(0, 0))).thenReturn(true);

        final boolean result = this.gameController.hit(new Position(0, 0));
        assertTrue(result, "The PowerUp should be applied");

        verify(this.powerUpManager).applyPowerUp(this.board, new Position(0, 0));
        verify(this.view).updateView();
    }
}
