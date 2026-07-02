package fargoal.model.map.api;

import fargoal.model.manager.api.FloorManager;

/**
 * interface that models a cover for the floor.
 */
public interface FloorMask {

    /**
     * a method to reset the mask to the initial state.
     */
    void resetMask();

    /**
     * A method that updates the state of the mask, has to be called every frame.
     * This method also should decide wheter something in the floor is seen or not.
     * 
     * @param manager - the manager to have every possible
     */
    void update(FloorManager manager);

    /**
     * A method to fully clear the mask.
     */
    void clearMask();
}
