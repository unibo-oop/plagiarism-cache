package core;

import java.util.List;
import java.util.Queue;

import enums.Stage;
import model.enemy.Enemy;
import model.entities.Block;

/**
 * Interface for a standard level game with all its blocks and its enemies.
 */
public interface Level {

    /**
     * Method that returns all the blocks of the level map.
     * 
     * @return all the blocks of the level map.
     */
    List<Block> getMap();

    /**
     * Method that return all the enemies of the level.
     * 
     * @return all the enemies of the level.
     */
    Queue<Enemy> getEnemy();

    /**
     * Method that return the stage which the level is referred.
     * 
     * @return the stage which the level is referred
     */
    Stage getStage();

}
