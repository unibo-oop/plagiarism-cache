package ala.controllers;

import ala.models.ParadiseWalkingMiniBossModel;
import ala.models.StandardEnemyModel;
import ala.views.ParadiseWalkingMiniBossView;

/**
 * ParadiseWalkingMiniBossController class.
 * 
 */
public final class ParadiseWalkingMiniBossController extends ParadiseWalkingEnemyController implements ParadiseWalkingEnemiesAbilitiesController {

    //Attributes:
    private ParadiseWalkingMiniBossModel paradiseWalkingMiniBossModel;
    private ParadiseWalkingMiniBossView paradiseWalkingMiniBossView;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param paradiseWalkingMiniBossModel
     * @param paradiseWalkingMiniBossView
     * 
     */
    public ParadiseWalkingMiniBossController(final ParadiseWalkingMiniBossModel paradiseWalkingMiniBossModel, final ParadiseWalkingMiniBossView paradiseWalkingMiniBossView) {
        super(paradiseWalkingMiniBossModel, paradiseWalkingMiniBossView);
        this.paradiseWalkingMiniBossModel = paradiseWalkingMiniBossModel;
        this.paradiseWalkingMiniBossView = paradiseWalkingMiniBossView;
        this.paradiseWalkingMiniBossView.paradiseMiniBossWalking();
    }

    //Getters&Setters:
    public ParadiseWalkingMiniBossView getParadiseWalkingMiniBossView() {
        return paradiseWalkingMiniBossView;
    }

    public void setParadiseWalkingMiniBossView(final ParadiseWalkingMiniBossView paradiseWalkingMiniBossView) {
        this.paradiseWalkingMiniBossView = paradiseWalkingMiniBossView;
    }

    public ParadiseWalkingMiniBossModel getParadiseWalkingMiniBossModel() {
        return paradiseWalkingMiniBossModel;
    }

    public void setParadiseWalkingMiniBossModel(final ParadiseWalkingMiniBossModel paradiseWalkingMiniBossModel) {
        this.paradiseWalkingMiniBossModel = paradiseWalkingMiniBossModel;
    }

    //Methods:
    /**
     * Method that makes paradise miniboss attack when Lucifer enter in his range.
     * 
     */
    @Override
    public void walkingEnemyAttack() {
        if (this.paradiseWalkingMiniBossModel.isAlive() && this.paradiseWalkingMiniBossModel.isRanged()) {
            if (this.paradiseWalkingMiniBossModel.isAttacking()) {
                if (this.getCounter() == 0) {
                    this.paradiseWalkingMiniBossView.paradiseMiniBossAttack();
                    this.setTempDx(this.paradiseWalkingMiniBossModel.getDx());
                    this.paradiseWalkingMiniBossModel.setDx(0);
                }
                this.setCounter(this.getCounter() + 1);
                if (this.getCounter() == StandardEnemyModel.FIRE_RATE) {
                    this.paradiseWalkingMiniBossModel.setAttacking(false);
                    this.setCounter(0);
                    this.paradiseWalkingMiniBossModel.setDx(this.getTempDx());
                    this.paradiseWalkingMiniBossView.paradiseMiniBossWalking();
                }
            }
        }
    }
}

