package ala.controllers;

import ala.models.MicheleModel;
import ala.views.MicheleView;

/**
 * MicheleController class.
 * 
 */
public final class MicheleController extends GameObjectAliveController implements BossPositionController {
    //Attributes:
    private MicheleModel micheleModel;
    private MicheleView micheleView;

    private LightBallController lightBallController;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param micheleModel
     * @param micheleView
     */
    public MicheleController(final MicheleModel micheleModel, final MicheleView micheleView) {
        super(micheleModel, micheleView.getMicheleVisualEntity());
        this.micheleModel = micheleModel;
        this.micheleView = micheleView;
        this.lightBallController = new LightBallController(this.micheleModel.getLightBallModel(), this.micheleView.getLightBallView());
    }

    //Getters&Setters:
    public MicheleModel getMicheleModel() {
        return micheleModel;
    }

    public MicheleView getMicheleView() {
        return micheleView;
    }

    public LightBallController getLightBallController() {
        return lightBallController;
    }

    //Methods:
    /**
     * Update Michele and LightBall position.
     * 
     */
    @Override
    public void checkPosition() {
        this.checkMichelePosition();
        this.checkLightBall();
    }
    /**
     * Update Michele position.
     * 
     */
    private void checkMichelePosition() {
        this.micheleModel.setX(MicheleModel.MICHELE_X_OFFSET + this.micheleView.getMichele().translateXProperty().getValue());
        this.micheleModel.setY(MicheleModel.MICHELE_Y_OFFSET + this.micheleView.getMichele().translateYProperty().getValue());
        this.micheleModel.manualHitBoxMovement(MicheleModel.MICHELE_X_OFFSET + micheleView.getMichele().translateXProperty().getValue(), MicheleModel.MICHELE_X_OFFSET + micheleView.getMichele().translateXProperty().getValue() + micheleModel.getWidth(), MicheleModel.MICHELE_Y_OFFSET + micheleView.getMichele().translateYProperty().getValue(), MicheleModel.MICHELE_Y_OFFSET + micheleView.getMichele().translateYProperty().getValue() + this.micheleModel.getHeight());
    }

    /**
     * Update LightBall position.
     * 
     */
    private void checkLightBall() {
        this.micheleModel.getLightBallModel().setX(this.micheleView.getLightBallView().getLayer().translateXProperty().getValue());
        this.micheleModel.getLightBallModel().setY(this.micheleView.getLightBallView().getLayer().translateYProperty().getValue());
        this.micheleModel.getLightBallModel().manualHitBoxMovement(this.getMicheleModel().getLightBallModel().getX(), this.getMicheleModel().getLightBallModel().getX() + 64, MicheleModel.LIGHTBALL_Y_OFFSET + this.getMicheleModel().getLightBallModel().getY(), MicheleModel.LIGHTBALL_Y_OFFSET + this.getMicheleModel().getLightBallModel().getY() + 64);
    }


    /**
     * Update Michele lifepoints.
     * 
     * @param damage
     *        need to take trace of damage to decrease it from health bar
     */
    public void gettingDamaged(final double damage) {
        super.gettingDamaged(damage);
        micheleView.getMicheleHealthBar().getStatusBar().setWidth(micheleView.getMicheleHealthBar().getStatusBar().getWidth() - (damage * 1000) / micheleModel.getMaxHealth());
        if (!this.micheleModel.isAlive()) {
            this.micheleView.getMicheleVisualEntity().explode();
            this.micheleView.stop();
        }
    }
}
