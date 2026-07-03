package model.levels;

import java.util.List;

import model.entities.FloorTile;
import model.entities.Stair;

/**
 * Interface that specify the basic properties of a level.
 */

public interface GameLevel {
    
    /**
     * 
     * @return the string containing level name.
     */
    String getLevelName();
    
    /**
     * 
     * @return the string containing image directory.
     */
    String getImageDirectory();

    /**
     * 
     * @return the list containing all the stairs
     */
    List<? extends Stair> getStairs();
    
    /**
     * 
     * @return the list containing all components of the floor
     */
    List<? extends FloorTile> getFloor();

}
