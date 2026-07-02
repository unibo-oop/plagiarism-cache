package ala.controllers;

import ala.models.LightBallModel;
import ala.views.LightBallView;

/**
 * LightBallController class.
 * 
 */
public class LightBallController extends DynamicGameObjectController {

    //Attributes:
    private LightBallModel lightBallModel;
    private LightBallView lightBallView;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param lightBallModel
     * @param lightBallView
     * 
     */
    public LightBallController(final LightBallModel lightBallModel, final LightBallView lightBallView) {
        super(lightBallModel, lightBallView);
        this.lightBallModel = lightBallModel;
        this.lightBallView = lightBallView;
    }

    //Getters & Setter:
    public final LightBallModel getLightBallModel() {
        return lightBallModel;
    }

    public final void setLightBallModel(final LightBallModel lightBallModel) {
        this.lightBallModel = lightBallModel;
    }

    public final LightBallView getLightBallView() {
        return lightBallView;
    }

    public final void setLightBallView(final LightBallView lightBallView) {
        this.lightBallView = lightBallView;
    }
}
