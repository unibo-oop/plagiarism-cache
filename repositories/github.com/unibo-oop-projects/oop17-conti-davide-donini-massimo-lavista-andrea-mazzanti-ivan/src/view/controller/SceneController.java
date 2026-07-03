package view.controller;

import view.scenefactory.SceneFactory;

/**
 * Represents a simple scene controller.
 */
public interface SceneController {

    /**
     * 
     * @param sceneFactory
     *            reference to scene factory.
     */
    void setSceneFactory(SceneFactory sceneFactory);

    /**
     * 
     * @return factory used to change scene.
     */
    SceneFactory getSceneFactory();

}
