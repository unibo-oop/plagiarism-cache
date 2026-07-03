package breakout.model.levels;

import breakout.model.entities.Brick;
import breakout.model.entities.Wall;
import java.util.List;

/**
 * This interface defines all the main methods that a level must implements.
 */
public interface BasicLevel {

    /**
     * 
     * @return the List of all briks in the level
     */
    List<Brick> getBricks();

    /**
     * 
     * @return the List of all wall in the level
     */
    List<Wall> getWalls();

    /**
     * 
     * @return the int probability to spawn bonus in the level
     */
    int getSpawnProb();

}
