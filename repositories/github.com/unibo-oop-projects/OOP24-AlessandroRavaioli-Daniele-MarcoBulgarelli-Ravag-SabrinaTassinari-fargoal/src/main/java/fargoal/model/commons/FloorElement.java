package fargoal.model.commons;

import fargoal.commons.api.Position;
import fargoal.model.manager.api.FloorManager;

/**
 * Interface that has general methods for all the elements
 * in the map.
 */
public interface FloorElement {
    /**
     * Return the position of the element selected.
     * 
     * @return - the current position
     */
    Position getPosition();

    /**
     * Return the name of the element selected.
     * 
     * @return - the element in a string subframe 
     */
    String getTag();

    /**
     * Method to get un update about the element status
     * that is called for each frame.
     * 
     * @param floorManager - to get infos about all the other elements in the map
     */
    void update(FloorManager floorManager);

    /**
     * Method that call the method render of the
     * Entity and put the Object in the queue of
     * the Object to be drawn.
     */
    void render();
}
