package view.scenecontroller;

import view.sceneloader.SceneLoader;

/**
 * 
 * Chiara Tarantino. 
 * Scenes Controller interface.
 *
 */
public interface SceneController {
    /**
     *
     * @return the scene loader.
     */
    SceneLoader getSceneLoader();

    /**
     *
     * @param sceneLoader
     *            scene loader.
     */
    void setSceneLoader(SceneLoader sceneLoader);

}
