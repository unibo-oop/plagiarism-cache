package ala.controllers;

import java.util.List;

import ala.models.CerberoModel;
import ala.views.CerberoView;

/**
* Cerbero's controller class.
* 
*/
public final class CerberoController implements BossPositionController {

    //Attributes:
    private CerberoModel cerberoModel;
    private CerberoView cerberoView;

    private CerberoImportantHeadController importantHeadController;

    private FireBallController fireBallController;
    private FireRainController fireRainController;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param cerberoModel
     *        A refer to all the model components which are part of Cerbero
     * @param cerberoView
     *        A refer to all the view components which are part of Cerbero
     * @param dynamicObjectsInScene
     *        A refer to the list that contains all the attacks, in this way Cerbero can add his attacks on this list
     *        in order to let the main loop to menage the collisions.
     *        This connects the Federico to the Mattia part.
     */
    public CerberoController(final CerberoModel cerberoModel, final CerberoView cerberoView, final List<DynamicGameObjectController> dynamicObjectsInScene) {
        //Set the local refer to the CerberoModel
        this.cerberoModel = cerberoModel;
        //Set the local refer to the CerberoView
        this.cerberoView = cerberoView;

        //Create the controllers for the attacks of Cerbero connecting data from model and graphic from view:
        //Create the fireball attack controller
        this.fireBallController = new FireBallController(this.cerberoModel.getFireBallModel(), this.cerberoView.getFireBallView());
        //Create the firerain attack controller
        this.fireRainController = new FireRainController(this.cerberoModel.getFireRainModel(), this.cerberoView.getFireRainView());

        /*
         * Create the controller for the important head which is the real important component of Cerbero that
         * represent the entity on screen that take and get damage and menage the life
         */
        this.importantHeadController = new CerberoImportantHeadController(cerberoModel.getFirstHead(), cerberoView.getFirstHead());
    }

    //Getters&Setters:
    public CerberoModel getCerberoModel() {
        return cerberoModel;
    }

    public CerberoView getCerberoView() {
        return cerberoView;
    }

    public void setCerberoModel(final CerberoModel cerberoModel) {
        this.cerberoModel = cerberoModel;
    }

    public void setCerberoView(final CerberoView cerberoView) {
        this.cerberoView = cerberoView;
    }

    public CerberoImportantHeadController getImportantHeadController() {
        return importantHeadController;
    }

    public FireBallController getFireBallController() {
        return fireBallController;
    }

    public void setFireBallController(final FireBallController fireBallController) {
        this.fireBallController = fireBallController;
    }

    public FireRainController getFireRainController() {
        return fireRainController;
    }

    public void setFireRainController(final FireRainController fireRainController) {
        this.fireRainController = fireRainController;
    }

    public void setImportantHeadController(final CerberoImportantHeadController importantHeadController) {
        this.importantHeadController = importantHeadController;
    }

    //Methods:
    //Update the positions :
    /**
     * this method update the positions.
     * 
     */
    @Override
    public void checkPosition() {
        this.checkCerberoPosition();
        this.checkFireBall();
        this.checkFireRain();
    }

    /**
     * update Cerbero position and hitbox movement.
     * 
     */
    private void checkCerberoPosition() {
        this.cerberoModel.getFirstHead().setX(CerberoModel.FIRST_HEAD_X_OFFSET + this.cerberoView.getCerberoLayer().translateXProperty().getValue());
        this.cerberoModel.getFirstHead().setY(CerberoModel.FIRST_HEAD_Y_OFFSET + this.cerberoView.getCerberoLayer().translateYProperty().getValue());
        this.cerberoModel.getFirstHead().manualHitBoxMovement(CerberoModel.FIRST_HEAD_X_OFFSET + cerberoView.getCerberoLayer().translateXProperty().getValue() + 100, CerberoModel.FIRST_HEAD_X_OFFSET + cerberoView.getCerberoLayer().translateXProperty().getValue() + this.cerberoModel.getFirstHead().getWidth(), 100 + CerberoModel.FIRST_HEAD_Y_OFFSET + cerberoView.getCerberoLayer().translateYProperty().getValue(), CerberoModel.FIRST_HEAD_Y_OFFSET + cerberoView.getCerberoLayer().translateYProperty().getValue() + this.cerberoModel.getFirstHead().getHeight());
    }
 
    /**
     * update Fireball position and hitbox movement.
     * 
     */
    private void checkFireBall() {
        this.cerberoModel.getFireBallModel().setX(CerberoModel.FIREBALL_X_OFFSET + this.cerberoView.getFireBallView().getLayer().translateXProperty().getValue());
        this.cerberoModel.getFireBallModel().setY(CerberoModel.FIREBALL_Y_OFFSET + this.cerberoView.getFireBallView().getLayer().translateYProperty().getValue());
        this.cerberoModel.getFireBallModel().manualHitBoxMovement(this.getCerberoModel().getFireBallModel().getX(), this.getCerberoModel().getFireBallModel().getX() + CerberoModel.MANUAL_HITBOX_X_ADJUSTEMENT, this.getCerberoModel().getFireBallModel().getY(), this.getCerberoModel().getFireBallModel().getY() + CerberoModel.MANUAL_HITBOX_Y_ADJUSTEMENT);
    }

    /**
     * update FireRain position and hitbox movement.
     * 
     */
    private void checkFireRain() {
        this.cerberoModel.getFireRainModel().getFireBallModelA().setX(this.cerberoView.getFireRainView().getFireBallViewA().getLayer().translateXProperty().getValue());
        this.cerberoModel.getFireRainModel().getFireBallModelA().setY(this.cerberoView.getFireRainView().getFireBallViewA().getLayer().translateXProperty().getValue());
        this.cerberoModel.getFireRainModel().getFireBallModelB().setX(this.cerberoView.getFireRainView().getFireBallViewA().getLayer().translateXProperty().getValue() + CerberoModel.FIRERAIN_X_OFFSET);
        this.cerberoModel.getFireRainModel().getFireBallModelB().setY(this.cerberoView.getFireRainView().getFireBallViewA().getLayer().translateXProperty().getValue());
        this.cerberoModel.getFireRainModel().getFireBallModelC().setX(this.cerberoView.getFireRainView().getFireBallViewA().getLayer().translateXProperty().getValue() + CerberoModel.FIRERAIN_X_OFFSET * 2);
        this.cerberoModel.getFireRainModel().getFireBallModelC().setY(this.cerberoView.getFireRainView().getFireBallViewA().getLayer().translateXProperty().getValue());
        this.cerberoModel.getFireRainModel().getFireBallModelA().getHitBox().manualHitBoxMovement(cerberoView.getFireRainView().getFireBallViewA().getLayer().translateXProperty().getValue(), cerberoView.getFireRainView().getFireBallViewA().getLayer().translateXProperty().getValue() + CerberoModel.MANUAL_HITBOX_X_ADJUSTEMENT, CerberoModel.FIRERAIN_MANUAL_HITBOX_Y_ADJUSTEMENT + cerberoView.getFireRainView().getFireBallViewA().getLayer().translateYProperty().getValue(), CerberoModel.FIRERAIN_MANUAL_HITBOX_Y_ADJUSTEMENT + cerberoView.getFireRainView().getFireBallViewA().getLayer().translateYProperty().getValue() + CerberoModel.MANUAL_HITBOX_Y_ADJUSTEMENT);
        this.cerberoModel.getFireRainModel().getFireBallModelB().getHitBox().manualHitBoxMovement(CerberoModel.FIRERAIN_MANUAL_HITBOX_X_ADJUSTEMENT + cerberoView.getFireRainView().getFireBallViewA().getLayer().translateXProperty().getValue(), CerberoModel.FIRERAIN_MANUAL_HITBOX_X_ADJUSTEMENT + cerberoView.getFireRainView().getFireBallViewA().getLayer().translateXProperty().getValue() + CerberoModel.MANUAL_HITBOX_X_ADJUSTEMENT, CerberoModel.FIRERAIN_MANUAL_HITBOX_Y_ADJUSTEMENT + cerberoView.getFireRainView().getFireBallViewA().getLayer().translateYProperty().getValue(), CerberoModel.FIRERAIN_MANUAL_HITBOX_Y_ADJUSTEMENT + cerberoView.getFireRainView().getFireBallViewA().getLayer().translateYProperty().getValue() + CerberoModel.MANUAL_HITBOX_Y_ADJUSTEMENT);
        this.cerberoModel.getFireRainModel().getFireBallModelC().getHitBox().manualHitBoxMovement(CerberoModel.FIRERAIN_MANUAL_HITBOX_X_ADJUSTEMENT * 2 + cerberoView.getFireRainView().getFireBallViewA().getLayer().translateXProperty().getValue(), CerberoModel.FIRERAIN_MANUAL_HITBOX_X_ADJUSTEMENT * 2 + cerberoView.getFireRainView().getFireBallViewA().getLayer().translateXProperty().getValue() + CerberoModel.MANUAL_HITBOX_X_ADJUSTEMENT, CerberoModel.FIRERAIN_MANUAL_HITBOX_Y_ADJUSTEMENT + cerberoView.getFireRainView().getFireBallViewA().getLayer().translateYProperty().getValue(), CerberoModel.FIRERAIN_MANUAL_HITBOX_Y_ADJUSTEMENT + cerberoView.getFireRainView().getFireBallViewA().getLayer().translateYProperty().getValue() + CerberoModel.MANUAL_HITBOX_Y_ADJUSTEMENT);
    }

    /**
     * check and update Cerbero's lifepoints.
     * 
     * @param damage
     *        need to know damage value to set how much decrease Cerbero's health bar
     */
    public void gettingDamaged(final double damage) {
        this.importantHeadController.gettingDamaged(damage);
        cerberoView.getCerberoHealthBar().getStatusBar().setWidth(cerberoView.getCerberoHealthBar().getStatusBar().getWidth() - (damage * 1000) / cerberoModel.getFirstHead().getMaxHealth());
        if (!this.getImportantHeadController().getImportantHeadModel().isAlive()) {
            this.cerberoView.explode();
        }
    }
}
