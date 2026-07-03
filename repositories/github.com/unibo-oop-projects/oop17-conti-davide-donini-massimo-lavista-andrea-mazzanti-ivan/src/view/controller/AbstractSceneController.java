package view.controller;

import view.scenefactory.SceneFactory;

/**
 * This Class factories the shared methods of the scenes controllers.
 */
public abstract class AbstractSceneController implements SceneController {

    private SceneFactory sceneFactory;

    @Override
    public final void setSceneFactory(final SceneFactory sceneFactory) {
        this.sceneFactory = sceneFactory;
    }

    /**
     * 
     * @return view containing scenes.
     */
    @Override
    public final SceneFactory getSceneFactory() {
        return this.sceneFactory;
    }

}
