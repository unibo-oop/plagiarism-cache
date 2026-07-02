package ala.controllers;

import ala.models.CainoModel;
import ala.views.CainoView;

/**
 * the caino's controller class.
 *
 */
public final class CainoController extends GameObjectAliveController implements BossPositionController {
    //Attributes:
    private CainoModel cainoModel;
    private CainoView cainoView;
    private MoonController moonController;
    private AsteroidController asteroidController;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param cainoModel
     * @param cainoView
     * @param asteroidController
     */
    public CainoController(final CainoModel cainoModel, final CainoView cainoView, final AsteroidController asteroidController) {
        super(cainoModel, cainoView.getCainoVisualEntity());

        this.cainoModel = cainoModel;
        this.cainoView = cainoView;

        this.moonController = new MoonController(cainoModel.getMoonModel(), cainoView.getMoonView());
        this.asteroidController = asteroidController;
    }

    //Getters&Setters:
    public MoonController getMoonController() {
        return moonController;
    }

    public CainoView getCainoView() {
        return cainoView;
    }

    public CainoModel getCainoModel() {
        return cainoModel;
    }

    public AsteroidController getAsteroidController() {
        return asteroidController;
    }

    public void setFireBallController(final AsteroidController asteroidController) {
        this.asteroidController = asteroidController;
    }

    /**
     *  check the position of all the Caino's elements every sixtieth of seconds.
     */
    @Override
    public void checkPosition() {
        this.checkCainoPosition();
        this.checkAsteroid();
    }

    /**
     *  check the position of Caino every sixtieth of seconds.
     */
    private void checkCainoPosition() {
        this.cainoModel.setX(CainoModel.X_OFFSET + this.cainoView.getCainoLayer().translateXProperty().getValue());
        this.cainoModel.setY(CainoModel.Y_OFFSET + this.cainoView.getCainoLayer().translateYProperty().getValue());
        this.cainoModel.manualHitBoxMovement(
            CainoModel.X_OFFSET + cainoView.getCainoLayer().translateXProperty().getValue(),
            CainoModel.X_OFFSET + cainoView.getCainoLayer().translateXProperty().getValue() + this.cainoModel.getWidth(),
            CainoModel.Y_OFFSET + cainoView.getCainoLayer().translateYProperty().getValue(),
            CainoModel.Y_OFFSET + cainoView.getCainoLayer().translateYProperty().getValue() + this.cainoModel.getHeight());
    }

    /**
     * Subtract the damage to the health of the boss. IT managed the respective lenght of the Health bar and the death of Michele.
     * 
     * @param damage
     *        need to know damage value to set how much decrease Cerbero's health bar
     */
    public void gettingDamaged(final double damage) {
        super.gettingDamaged(damage);
        cainoView.getCainoHealthView().getStatusBar().setWidth(cainoView.getCainoHealthView().getStatusBar().getWidth() - (damage * 1000) / cainoModel.getMaxHealth());
        if (!this.cainoModel.isAlive()) {
            this.cainoView.explode();
        }
    }

    /**
     *  check the position of the asteroids every sixtieth of seconds.
     */
    private void checkAsteroid() {
            this.cainoModel.getAsteroidModel().setX(this.cainoView.getAsteroidView().getLayer().translateXProperty().getValue());
            this.cainoModel.getAsteroidModel().setY(CainoModel.Y_OFFSET + this.cainoView.getAsteroidView().getLayer().translateYProperty().getValue());
            this.cainoModel.getAsteroidModel().manualHitBoxMovement(this.getCainoModel().getAsteroidModel().getX(), this.getCainoModel().getAsteroidModel().getX() + 64, this.getCainoModel().getAsteroidModel().getY(), this.getCainoModel().getAsteroidModel().getY() + 64);
    }
}
