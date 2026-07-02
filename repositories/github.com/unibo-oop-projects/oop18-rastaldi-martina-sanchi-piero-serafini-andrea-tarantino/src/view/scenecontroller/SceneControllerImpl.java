package view.scenecontroller;

import controller.Controller;
import controller.ControllerImpl;
import view.sceneloader.SceneLoader;

/**
 *
 * Chiara Tarantino. 
 * Scenes Controller class.
 *
 */
public class SceneControllerImpl implements SceneController {

    private SceneLoader sceneLoader;
    private final Controller controller = ControllerImpl.getLog();

    /**
     *
     * @return the controller instance
     */
    protected Controller getController() {
        return this.controller;
    }

    @Override
    public final SceneLoader getSceneLoader() {
        return this.sceneLoader;
    }

    @Override
    public final void setSceneLoader(final SceneLoader sceneLoader) {
        this.sceneLoader = sceneLoader;
    }

}
