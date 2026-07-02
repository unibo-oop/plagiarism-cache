package fargoal.model.map.api;

import fargoal.model.manager.api.FloorManager;

/**
 * interface for a class that generates a floor.
 */
public interface FloorGenerator {

    /**
     * the method that creates a floor.
     * 
     * @param manager - the manager that contains information on the current level and the renders to use
     * @return - a new map of the floor
     */
    FloorMap createFloor(FloorManager manager);
}
