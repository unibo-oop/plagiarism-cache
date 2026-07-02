package ala.controllers;

import ala.models.GameObjectModel;
import ala.views.GameObjectView;

/**
 * GameObjectController class.
 * 
 */
public abstract class GameObjectController {

    //Attributes:
    private GameObjectModel gameObjectModel;
    private GameObjectView gameObjectView;

    //Constructors:
    public GameObjectController() { }

    /**
     * Constructor.
     * 
     * @param gameObjectModel
     * @param gameObjectView
     * 
     */
    public GameObjectController(final GameObjectModel gameObjectModel, final GameObjectView gameObjectView) {
        this.gameObjectModel = gameObjectModel;
        this.gameObjectView = gameObjectView;
    }
 
    //Getters&setters:

    /**
     * 
     * @return GameObjectModel
     */
    public GameObjectModel getGameObjectModel() {
        return gameObjectModel;
    }

    /**
     * 
     * @param gameObjectModel
     */
    public void setGameObjectModel(final GameObjectModel gameObjectModel) {
        this.gameObjectModel = gameObjectModel;
    }

    /**
     * 
     * @return GameObjectView
     */
    public GameObjectView getGameObjectView() {
        return gameObjectView;
    }

    /**
     * 
     * @param gameObjectView
     */
    public void setGameObjectView(final GameObjectView gameObjectView) {
        this.gameObjectView = gameObjectView;
    }
}

