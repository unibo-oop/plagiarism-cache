package ala.controllers;

import ala.models.FireBallModel;
import ala.views.FireBallView;

/**
 * FireBall controller class.
 * 
 */
public final class FireBallController extends DynamicGameObjectController {

    //Attributes:
    private FireBallModel fireBallModel;
    private FireBallView fireBallView;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param fireBallModel
     * @param fireBallView
     * 
     */
    public FireBallController(final FireBallModel fireBallModel, final FireBallView fireBallView) {
        super(fireBallModel, fireBallView);
        this.fireBallModel = fireBallModel;
        this.fireBallView = fireBallView;
    }

    //Getter&Setter:
    public FireBallModel getFireBallModel() {
        return fireBallModel;
    }

    public void setFireBallModel(final FireBallModel fireBallModel) {
        this.fireBallModel = fireBallModel;
    }

    public FireBallView getFireBallView() {
        return fireBallView;
    }

    public void setFireBallView(final FireBallView fireBallView) {
        this.fireBallView = fireBallView;
    }
}
