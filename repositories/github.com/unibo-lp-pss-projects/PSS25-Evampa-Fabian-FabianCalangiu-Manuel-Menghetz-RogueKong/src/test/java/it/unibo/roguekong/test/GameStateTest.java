package it.unibo.roguekong.test;

import it.unibo.roguekong.model.game.GameState;
import it.unibo.roguekong.model.game.impl.GameStateImpl;
import it.unibo.roguekong.model.game.impl.GameStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameStateTest {
    private GameState gameState;

    @BeforeEach
    void setUp(){
        gameState = new GameStateImpl();
    }

    @Test
    void testIfInitialStateIsMenu(){
        assertEquals(GameStatus.MENU, gameState.getState());
    }

    @Test
    void testIfGameStarts(){
        gameState.startGame();
        assertEquals(GameStatus.PLAYING, gameState.getState());
    }

    @Test
    void testIfGameIsPausedAndBack(){
        gameState.startGame();
        gameState.pauseGame();
        assertEquals(GameStatus.PAUSED, gameState.getState());

        gameState.resumeGame();
        assertEquals(GameStatus.PLAYING, gameState.getState());
    }

    @Test
    void testIfGameIsOver(){
        gameState.startGame();
        gameState.gameOver();
        assertEquals(GameStatus.GAME_OVER, gameState.getState());
    }

    @Test
    void testIfReturnedToMenu(){
        gameState.startGame();
        gameState.goToMenu();
        assertEquals(GameStatus.MENU, gameState.getState());
    }
}
