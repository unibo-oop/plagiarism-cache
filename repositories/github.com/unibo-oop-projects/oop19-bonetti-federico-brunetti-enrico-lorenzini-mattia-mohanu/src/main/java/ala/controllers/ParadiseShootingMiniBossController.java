package ala.controllers;

import java.util.List;

import ala.models.ArrowModel;
import ala.models.ParadiseShootingMiniBossModel;
import ala.models.StandardEnemyModel;
import ala.views.ArrowView;
import ala.views.ParadiseShootingMiniBossView;

/**
 * ParadiseShootingMiniBossController class.
 * 
 */
public class ParadiseShootingMiniBossController extends ParadiseShootingEnemyController implements ParadiseShootingEnemiesAbilitiesController {
    //Attributes:
    private ParadiseShootingMiniBossModel paradiseShootingMiniBossModel;
    private ParadiseShootingMiniBossView paradiseShootingMiniBossView;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param paradiseShootingMiniBossModel
     * @param paradiseShootingMiniBossView
     * 
     */
    public ParadiseShootingMiniBossController(final ParadiseShootingMiniBossModel paradiseShootingMiniBossModel, final ParadiseShootingMiniBossView paradiseShootingMiniBossView) {
        super(paradiseShootingMiniBossModel, paradiseShootingMiniBossView);
        this.paradiseShootingMiniBossModel = paradiseShootingMiniBossModel;
        this.paradiseShootingMiniBossView = paradiseShootingMiniBossView;
        this.paradiseShootingMiniBossView.paradiseMiniBossShooting();
    }

    //Getters&Setters:
    public final ParadiseShootingMiniBossModel getParadiseShootingMiniBossModel() {
        return paradiseShootingMiniBossModel;
    }

    public final ParadiseShootingMiniBossView getParadiseShootingMiniBossView() {
        return paradiseShootingMiniBossView;
    }

    public final void setParadiseShootingMiniBossModel(final ParadiseShootingMiniBossModel paradiseShootingMiniBossModel) {
        this.paradiseShootingMiniBossModel = paradiseShootingMiniBossModel;
    }

    public final void setParadiseShootingMiniBossView(final ParadiseShootingMiniBossView paradiseShootingMiniBossView) {
        this.paradiseShootingMiniBossView = paradiseShootingMiniBossView;
    }

    /**
     * Method that makes paradise miniboss stop and shoot arrows consequently.
     * @param dynamicObjectsInScene
     */
    public void fire(final List<DynamicGameObjectController> dynamicObjectsInScene) {
        if (this.paradiseShootingMiniBossModel.isAlive() && this.paradiseShootingMiniBossModel.isRanged()) {
            if (this.paradiseShootingMiniBossModel.isFiring()) {
                this.setFireRate(this.getFireRate() + 1);
                if (this.getFireRate() == StandardEnemyModel.FIRE_RATE) {
                    this.paradiseShootingMiniBossModel.setFiring(false);
                    this.paradiseShootingMiniBossView.paradiseMiniBossShootingAttack();
                    this.paradiseShootingMiniBossModel.setDx(this.getTempDx());
                }
            } else if (this.paradiseShootingMiniBossModel.getCurrentPosition() % StandardEnemyModel.PERCENT_OF_MOVEMENT_BEFORE_FIRING == 0) {
                    this.paradiseShootingMiniBossModel.setFiring(true);
                    this.paradiseShootingMiniBossView.paradiseMiniBossShootingAttack();
                    this.setFireRate(0);
                    this.setTempDx(this.paradiseShootingMiniBossModel.getDx());
                    this.paradiseShootingMiniBossModel.setDx(0);
                    ArrowModel newArrowModel = new ArrowModel(this.getStandardEnemyModel().getX(), this.getStandardEnemyModel().getY() + this.getStandardEnemyModel().getHeight() / 2, this.paradiseShootingMiniBossModel.getType());
                    ArrowView newArrowView = new ArrowView(this.getStandardEnemyView().getLayer(), newArrowModel);
                    dynamicObjectsInScene.add(new ArrowController(newArrowModel, newArrowView));
              }
         }
    }
}

