package core;

import java.util.List;
import java.util.Map;

import model.common.Counter;
import model.enemy.Enemy;

/**
 * Represent the current status of the game.
 *
 */
public interface GameStatus {
    /**
     * @return current playing level;
     */
    int getLevel();

    /**
     * @return the number of residue tank to spawn in the level;
     */
    int getResidueTank();

    /**
     * 
     * @return a list of integer representing the player life, the first element is
     *         the first player life, the second the second player life
     */

    List<Integer> getPlayerLife();

    /**
     * 
     * @return a list of integer representing the player points, the first element
     *         is the first player points, the second the second player points
     */
    List<Integer> getPlayerPoints();

    /**
     * 
     * @return a list of killed tank for each player, the first player list of enemy
     *         is the first element in principal list
     */

    List<Map<Enemy, Counter>> getKilledTank();

    /**
     * 
     * @return the actual state of the game to assign a right render scene.
     */

    State getGameState();

}

enum State {
    RUN, LOSE, WIN;
}
