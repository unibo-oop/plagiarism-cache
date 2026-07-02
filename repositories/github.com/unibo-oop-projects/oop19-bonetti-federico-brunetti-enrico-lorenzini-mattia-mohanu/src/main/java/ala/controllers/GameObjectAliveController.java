package ala.controllers;

import java.util.List;

import ala.models.GameObjectAliveModel;
import ala.views.GameObjectAliveView;

/**
 * GameObjectAlive controller class.
 * 
 */
public abstract class GameObjectAliveController extends DynamicGameObjectController {
    //Attributes:
    private GameObjectAliveModel gameObjectAliveModel;
    private GameObjectAliveView gameObjectAliveView;

    //Constructor:
    public GameObjectAliveController() { }

    /**
     * Constructor.
     * 
     * @param gameObjectAliveModel
     * @param gameObjectAliveView
     * 
     */
    public GameObjectAliveController(final GameObjectAliveModel gameObjectAliveModel, final GameObjectAliveView gameObjectAliveView) {
        super(gameObjectAliveModel, gameObjectAliveView);

        this.gameObjectAliveModel = gameObjectAliveModel;
        this.gameObjectAliveView = gameObjectAliveView;
    }

    //Getters&Setters:
    public final GameObjectAliveModel getGameObjectAliveModel() {
        return gameObjectAliveModel;
    }

    public final void setGameObjectAliveModel(final GameObjectAliveModel gameObjectAliveModel) {
        this.gameObjectAliveModel = gameObjectAliveModel;
    }

    public final GameObjectAliveView getGameObjectAliveView() {
        return gameObjectAliveView;
    }

    public final void setGameObjectAliveView(final GameObjectAliveView gameObjectAliveView) {
        this.gameObjectAliveView = gameObjectAliveView;
    }

    //Methods:
    /**
     * update lifepoints of a GameObjectAlive object.
     * 
     * @param damage
     *        need to know damage value to decrease health points
     * 
     */
    public void gettingDamaged(final double damage) {
        this.gameObjectAliveModel.setHealth(this.gameObjectAliveModel.getHealth() - damage);
    }

    /**
     * add a GameObjectAlive object to dynamicObjectInScene list.
     * 
     * @param newDynamicGameObjectController
     *        need the DynamiObjectController to add to dynamicObjectsInScene list
     * @param dynamicObjectsInScene
     */
    public final void addNewObjectInScene(final List<DynamicGameObjectController> dynamicObjectsInScene, final DynamicGameObjectController newDynamicGameObjectController) {
        dynamicObjectsInScene.add(newDynamicGameObjectController);
    }
}
