package ala.controllers;

import java.util.List;
import ala.models.ArrowModel;
import ala.models.ParadiseShootingEnemyModel;
import ala.models.ParadiseShootingMiniBossModel;
import ala.models.StandardEnemyModel;
import ala.views.ArrowView;
import ala.views.ParadiseShootingEnemyView;
import ala.views.ParadiseShootingMiniBossView;

/**
 * ParadiseShootingEnemyController class.
 * 
 */
public class ParadiseShootingEnemyController extends StandardEnemyController implements ParadiseShootingEnemiesAbilitiesController {
    //Attributes:
    private ParadiseShootingEnemyModel paradiseShootingEnemyModel;
    private ParadiseShootingEnemyView paradiseShootingEnemyView;

    private int fireRate;
    private double tempDx;

    //Constructors:
    /**
     * Constructor 1.
     * 
     * @param paradiseShootingEnemyModel
     * @param paradiseShootingEnemyView
     */
    public ParadiseShootingEnemyController(final ParadiseShootingEnemyModel paradiseShootingEnemyModel, final ParadiseShootingEnemyView paradiseShootingEnemyView) {
        super(paradiseShootingEnemyModel, paradiseShootingEnemyView);
        this.paradiseShootingEnemyModel = paradiseShootingEnemyModel;
        this.paradiseShootingEnemyView = paradiseShootingEnemyView;
        this.fireRate = 0;
        this.tempDx = 0;
        this.paradiseShootingEnemyView.angelWalk();
    }

    /**
     * Constructor 2.
     * 
     * @param paradiseShootingMiniBossModel
     * @param paradiseShootingMiniBossView
     */
    public ParadiseShootingEnemyController(final ParadiseShootingMiniBossModel paradiseShootingMiniBossModel, final ParadiseShootingMiniBossView paradiseShootingMiniBossView) {
        super(paradiseShootingMiniBossModel, paradiseShootingMiniBossView);
        this.fireRate = 0;
    }

    //Getters&Setters:
    public final ParadiseShootingEnemyView getParadiseShootingEnemyView() {
        return paradiseShootingEnemyView;
    }

    public final int getFireRate() {
        return fireRate;
    }

    public final double getTempDx() {
        return tempDx;
    }

    public final ParadiseShootingEnemyModel getParadiseShootingEnemyModel() {
        return paradiseShootingEnemyModel;
    }

    public final void setParadiseShootingEnemyView(final ParadiseShootingEnemyView paradiseShootingEnemyView) {
        this.paradiseShootingEnemyView = paradiseShootingEnemyView;
    }

    public final void setFireRate(final int fireRate) {
        this.fireRate = fireRate;
    }

    public final void setTempDx(final double tempDx) {
        this.tempDx = tempDx;
    }

    //Methods:
    /**
     * Method that makes paradise enemies stop and shoot arrows consequently.
     * 
     */
    @Override
    public void fire(final List<DynamicGameObjectController> dynamicObjectsInScene) {
        if (this.paradiseShootingEnemyModel.isAlive() && this.paradiseShootingEnemyModel.isRanged()) {
            if (this.paradiseShootingEnemyModel.isFiring()) {
                this.fireRate++;
                if (this.fireRate == StandardEnemyModel.FIRE_RATE) {
                    this.paradiseShootingEnemyModel.setFiring(false);
                    this.paradiseShootingEnemyView.angelWalk();
                    this.paradiseShootingEnemyModel.setDx(this.tempDx);
                }
            } else if (this.paradiseShootingEnemyModel.getCurrentPosition() % StandardEnemyModel.PERCENT_OF_MOVEMENT_BEFORE_FIRING == 0) {
                    this.paradiseShootingEnemyModel.setFiring(true);
                    this.paradiseShootingEnemyView.angelAttack();
                    this.fireRate = 0;
                    this.tempDx = this.paradiseShootingEnemyModel.getDx();
                    this.paradiseShootingEnemyModel.setDx(0);
                    ArrowModel newArrowModel = new ArrowModel(this.getStandardEnemyModel().getX(), this.getStandardEnemyModel().getY() + this.getStandardEnemyModel().getHeight() / 2, this.paradiseShootingEnemyModel.getType());
                    ArrowView newArrowView = new ArrowView(this.getStandardEnemyView().getLayer(), newArrowModel);
                    dynamicObjectsInScene.add(new ArrowController(newArrowModel, newArrowView));
              }
        }
    }
}
