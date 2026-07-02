package fargoal.model.manager.api;

import fargoal.model.core.GameEngine;

/**
 * An interface that models a manager for a scene of the application.
 */
public interface SceneManager {

    /**
     * This method will define what will happen every frame.
     * @param engine - the GameEngine that has called this method
     */
    void update(GameEngine engine);

    /**
     * A method that is needed to change the Scene that the engine has.
     * This method is needed beacouse every scene needs to connect to another.
     * @param engine - the engine in which the scene needs to be changed
     */
    void setSceneManager(GameEngine engine);
}
