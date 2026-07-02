package aoc.model;

import aoc.model.Model.GameStatus;
import aoc.model.entity.EntityInterface;
import java.util.List;

public interface LevelProxy {
    
    /**
     * Returns the current state of the level played.
     * @return the current GameStatus.
     */
    GameStatus getGameStatus();
    
    /**
     * Updates the position and state of all the entities in the game,
     * and check if the level is lost or won.
     */
    void update();
    
    /**
     * Returns all the entities currently present in the world.
     * @return list of entities.
     */
    List<EntityInterface> getEntityList();
    
    /**
     * Returns the index representing
     * the level that is currently being played.
     * @return current level index
     */
    int getCurrentLevel();
}
