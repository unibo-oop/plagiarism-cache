package ala.controllers;

import ala.models.ParadiseWalkingEnemyModel;
import ala.models.ParadiseWalkingMiniBossModel;
import ala.models.StandardEnemyModel;
import ala.views.ParadiseWalkingEnemyView;
import ala.views.ParadiseWalkingMiniBossView;

/**
 * ParadiseWalkingEnemyController class.
 * 
 */
public class ParadiseWalkingEnemyController extends StandardEnemyController implements ParadiseWalkingEnemiesAbilitiesController {

    //Attributes:
    private ParadiseWalkingEnemyModel paradiseWalkingEnemyModel;
    private ParadiseWalkingEnemyView paradiseWalkingEnemyView;

    private int counter;
    private double tempDx;

    //Constructors:
    /**
     * Constructor 1.
     * 
     * @param paradiseWalkingEnemyModel
     * @param paradiseWalkingEnemyView
     * 
     */
    public ParadiseWalkingEnemyController(final ParadiseWalkingEnemyModel paradiseWalkingEnemyModel, final ParadiseWalkingEnemyView paradiseWalkingEnemyView) {
        super(paradiseWalkingEnemyModel, paradiseWalkingEnemyView);
        this.paradiseWalkingEnemyModel = paradiseWalkingEnemyModel;
        this.paradiseWalkingEnemyView = paradiseWalkingEnemyView;
        this.paradiseWalkingEnemyView.archangelWalk();
        this.counter = 0;
        this.tempDx = 0;
    }

    /**
     * Constructor 2.
     * 
     * @param paradiseWalkingMiniBossModel
     * @param paradiseWalkingMiniBossView
     * 
     */
    public ParadiseWalkingEnemyController(final ParadiseWalkingMiniBossModel paradiseWalkingMiniBossModel, final ParadiseWalkingMiniBossView paradiseWalkingMiniBossView) {
        super(paradiseWalkingMiniBossModel, paradiseWalkingMiniBossView);
    }

    //Getters&Setters:
    public final int getCounter() {
        return counter;
    }

    public final void setCounter(final int counter) {
        this.counter = counter;
    }

    public final double getTempDx() {
        return tempDx;
    }

    public final void setTempDx(final double tempDx) {
        this.tempDx = tempDx;
    }

    public final ParadiseWalkingEnemyModel getParadiseWalkingEnemyModel() {
        return paradiseWalkingEnemyModel;
    }

    public final ParadiseWalkingEnemyView getParadiseWalkingEnemyView() {
        return paradiseWalkingEnemyView;
    }

    public final void setParadiseWalkingEnemyModel(final ParadiseWalkingEnemyModel paradiseWalkingEnemyModel) {
        this.paradiseWalkingEnemyModel = paradiseWalkingEnemyModel;
    }

    public final void setParadiseWalkingEnemyView(final ParadiseWalkingEnemyView paradiseWalkingEnemyView) {
        this.paradiseWalkingEnemyView = paradiseWalkingEnemyView;
    }

    //Methods:
    /**
     * Method that makes paradise enemies attack when Lucifer enter in his range.
     * 
     */
    @Override
    public void walkingEnemyAttack() {
        if (this.paradiseWalkingEnemyModel.isAlive() && this.paradiseWalkingEnemyModel.isRanged()) {
            if (this.paradiseWalkingEnemyModel.isAttacking()) {
                if (this.counter == 0) {
                    this.paradiseWalkingEnemyView.archangelAttack();
                    this.tempDx = this.paradiseWalkingEnemyModel.getDx();
                    this.paradiseWalkingEnemyModel.setDx(0);
                }
                this.counter++;
                if (this.counter == StandardEnemyModel.MELEE_RATE) {
                    this.paradiseWalkingEnemyModel.setAttacking(false);
                    this.counter = 0;
                    this.paradiseWalkingEnemyModel.setDx(tempDx);
                    this.paradiseWalkingEnemyView.archangelWalk();
                }
            }
        }
    }
}
