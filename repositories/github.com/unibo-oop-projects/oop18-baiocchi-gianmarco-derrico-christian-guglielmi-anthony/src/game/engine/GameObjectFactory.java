package game.engine;

import model.BalloomEnemy;
import model.Block;
import model.Door;
import model.PickableObject;
import model.Player;
import utilities.Position;

/**
 * This interface declares the method of a GameObjectFactory.
 */
public interface GameObjectFactory {

    /**
     * Create the player.
     * @param position : player's position.
     * @return a new player
     */
    Player createPlayer(Position position);

    /**
     * Create a BalloomEnemy.
     * @param position : enemy's position
     * @return a new BalloomEnemy
     */
    BalloomEnemy createBalloomEnemy(Position position);

    /**
     * Create an unbreakable block.
     * @param position : block's position
     * @return a new unbreakable Block 
     */
    Block createUnbreakableBlock(Position position);

    /**
     * Create a breakable block.
     * @param position : block's position
     * @return a new breakable Block 
     */
    Block createBreakableBlock(Position position);

    /**
     * Create a door.
     * @param position : door's position
     * @return a new Door
     */
    Door createDoor(Position position);

    /**
     * Create a key.
     * @param position : key's position
     * @return a new Key
     */
    PickableObject createKey(Position position);
}
