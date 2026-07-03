package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.GameState;

/**
 * The states are defined as implemented in the
 * GameStatus ENUM
 * The course of the game is optimally defined as follows:
 *  1. Initial state is Menu
 *  2. From the Menu the player may start the game
 *  3. While the player is in-game they may finish (or lose) the game,
 *     pause the game or return to the Menu
 *  4. If the game is over, the player may return to the Menu
 */

public class GameStateImpl implements GameState {
    private GameStatus status = GameStatus.MENU;

    public void startGame(){
        this.status = GameStatus.PLAYING;
    }

    public void pauseGame(){
        if(this.status == GameStatus.PLAYING){
            this.status = GameStatus.PAUSED;
        }
    }

    public void resumeGame(){
        if(this.status == GameStatus.PAUSED){
            this.status = GameStatus.PLAYING;
        }
    }

    public void gameOver(){
        if(this.status == GameStatus.PLAYING){
            this.status = GameStatus.GAME_OVER;
        }
    }

    public void goToMenu(){
        this.status = GameStatus.MENU;
    }

    @Override
    public GameStatus getState(){
        return this.status;
    }
}
