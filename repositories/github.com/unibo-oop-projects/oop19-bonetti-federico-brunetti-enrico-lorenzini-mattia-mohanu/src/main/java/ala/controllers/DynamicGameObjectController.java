package ala.controllers;

import ala.models.DynamicGameObjectModel;
import ala.views.DynamicGameObjectView;

/**
 * Dynamic Game Object controller class.
 * 
 */
public abstract class DynamicGameObjectController extends GameObjectController {
    //Attributes:
    private DynamicGameObjectModel dynamicGameObjectModel;
    private DynamicGameObjectView dynamicGameObjectView;

    //Constructors:
    public DynamicGameObjectController() { }

    /**
     * Constructor.
     * 
     * @param dynamicGameObjectModel
     * @param dynamicGameObjectView
     * 
     */
    public DynamicGameObjectController(final DynamicGameObjectModel dynamicGameObjectModel, final DynamicGameObjectView dynamicGameObjectView) {
        super(dynamicGameObjectModel, dynamicGameObjectView);

        this.dynamicGameObjectModel = dynamicGameObjectModel;
        this.dynamicGameObjectView = dynamicGameObjectView;
    }

    //Getters&Setters:
    public final DynamicGameObjectModel getDynamicGameObjectModel() {
        return dynamicGameObjectModel;
    }

    public final void setDynamicGameObjectModel(final DynamicGameObjectModel dynamicGameObjectModel) {
        this.dynamicGameObjectModel = dynamicGameObjectModel;
    }

    public final DynamicGameObjectView getDynamicGameObjectView() {
        return dynamicGameObjectView;
    }

    public final void setDynamicGameObjectView(final DynamicGameObjectView dynamicGameObjectView) {
        this.dynamicGameObjectView = dynamicGameObjectView;
    }
}
