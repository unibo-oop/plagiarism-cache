package ala.controllers;

import ala.models.FireRainModel;
import ala.views.FireRainView;

/**
 * FireRain controller class.
 * 
 */
public final class FireRainController {
    //Attributes:
    private FireRainModel fireRainModel;
    private FireRainView fireRainView;

    private FireBallController fireBallControllerA;
    private FireBallController fireBallControllerB;
    private FireBallController fireBallControllerC;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param fireRainModel
     * @param fireRainView
     * 
     */
    public FireRainController(final FireRainModel fireRainModel, final FireRainView fireRainView) {
        this.fireRainModel = fireRainModel;
        this.fireRainView = fireRainView;
        this.fireBallControllerA = new FireBallController(fireRainModel.getFireBallModelA(), fireRainView.getFireBallViewA());
        this.fireBallControllerB = new FireBallController(fireRainModel.getFireBallModelB(), fireRainView.getFireBallViewB());
        this.fireBallControllerC = new FireBallController(fireRainModel.getFireBallModelC(), fireRainView.getFireBallViewC());
    }

    //Getters&Setters:
    public FireRainModel getFireRainModel() {
        return fireRainModel;
    }

    public FireRainView getFireRainView() {
        return fireRainView;
    }

    public FireBallController getFireBallControllerA() {
        return fireBallControllerA;
    }

    public void setFireBallControllerA(final FireBallController fireBallControllerA) {
        this.fireBallControllerA = fireBallControllerA;
    }

    public FireBallController getFireBallControllerB() {
        return fireBallControllerB;
    }

    public void setFireBallControllerB(final FireBallController fireBallControllerB) {
        this.fireBallControllerB = fireBallControllerB;
    }

    public FireBallController getFireBallControllerC() {
        return fireBallControllerC;
    }

    public void setFireBallControllerC(final FireBallController fireBallControllerC) {
        this.fireBallControllerC = fireBallControllerC;
    }
}
