package javawulf.controller;

import java.util.List;

import javawulf.model.Collectable;
import javawulf.model.enemy.Pawn;
import javawulf.model.item.AmuletPiece;
import javawulf.model.map.Map;
import javawulf.model.powerup.PowerUp;

/**
 * GameLoop is an important Controller element: determines the game Time.
 * Number of ticks per seconds are determined by FPS value.
 * In each tick, GameLoop update the state of all game elements: objects,
 * enemies and player.
 * After updating the game element states, GameLoop ask View to redraw graphics.
 * 
 */
public interface GameLoop {

    /**
     * How many ticks per second the game state and the View representation will be
     * refredhed.
     */
    int FPS = 30;

    /**
     * GameLoop thread will be start only after this method is invoked.
     */
    void startGameLoopThread();

    /**
     * @return game Map.
     */
    Map getMap();

    /**
     * @return The Player character
     */
    PlayerStatus getPlayer();

    /**
     * @return The PlayerController
     */
    PlayerController getPlayerController();

    /**
     * @return The Items in the game
     */
    List<Collectable> getItems();

    /**
     * @return The Pawns in the game
     */
    List<Pawn> getPawns();

    /**
     * @return The AmuletPieces in the game
     */
    List<AmuletPiece> getAmuletPieces();

    /**
     * @return The PowerUps in the game
     */
    List<PowerUp> getPowerUps();
}
