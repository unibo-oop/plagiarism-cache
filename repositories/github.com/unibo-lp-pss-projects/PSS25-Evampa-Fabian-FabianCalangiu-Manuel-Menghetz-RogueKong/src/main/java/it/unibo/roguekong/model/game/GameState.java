package it.unibo.roguekong.model.game;

import it.unibo.roguekong.model.game.impl.GameStatus;

public interface GameState {

    void startGame();
    void pauseGame();
    void resumeGame();
    void gameOver();
    void goToMenu();
    GameStatus getState();
}
