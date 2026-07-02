package ala.controllers;

import ala.models.HellWalkingEnemyModel;
import ala.models.HellWalkingMiniBossModel;
import ala.models.StandardEnemyModel;
import ala.views.HellWalkingEnemyView;
import ala.views.HellWalkingMiniBossView;

/**
 * HellWalkingEnemyController class.
 * 
 */
public class HellWalkingEnemyController extends StandardEnemyController implements WalkingEnemiesAbilitiesController {

    //Attributes:
    private HellWalkingEnemyModel hellWalkingEnemyModel;
    private HellWalkingEnemyView hellWalkingEnemyView;

    //Constructor:
    /**
     * Constructor 1.
     * 
     * @param hellWalkingEnemyModel
     * @param hellWalkingEnemyView
     */
    public HellWalkingEnemyController(final HellWalkingEnemyModel hellWalkingEnemyModel, final HellWalkingEnemyView hellWalkingEnemyView) {
        super(hellWalkingEnemyModel, hellWalkingEnemyView);
        this.hellWalkingEnemyModel = hellWalkingEnemyModel;
        this.hellWalkingEnemyView = hellWalkingEnemyView;
        this.hellWalkingEnemyView.hellEnemyWalkingRight();
    }

    /**
     * Constructor 2.
     * 
     * @param hellWalkingMiniBossModel
     * @param hellWalkingMiniBossView
     */
    public HellWalkingEnemyController(final HellWalkingMiniBossModel hellWalkingMiniBossModel, final HellWalkingMiniBossView hellWalkingMiniBossView) {
        super(hellWalkingMiniBossModel, hellWalkingMiniBossView);
    }

    //Getters&Setters:
    public final HellWalkingEnemyView getHellWalkingEnemyView() {
        return hellWalkingEnemyView;
    }

    public final void setHellWalkingEnemyView(final HellWalkingEnemyView hellWalkingEnemyView) {
        this.hellWalkingEnemyView = hellWalkingEnemyView;
    }

    public final HellWalkingEnemyModel getHellWalkingEnemyModel() {
        return hellWalkingEnemyModel;
    }

    public final void setHellWalkingEnemyModel(final HellWalkingEnemyModel hellWalkingEnemyModel) {
        this.hellWalkingEnemyModel = hellWalkingEnemyModel;
    }

    //Methods:
    /**
     * make the enemy move left and right to walk on a platform.
     * 
     */
    @Override
    public void moveEnemy() {
        if (this.hellWalkingEnemyModel.isAlive()) {
            if (this.hellWalkingEnemyModel.getDx() < 0) {
                this.hellWalkingEnemyModel.setCurrentPosition(this.hellWalkingEnemyModel.getCurrentPosition() - 1);
                if (this.hellWalkingEnemyModel.getCurrentPosition() < -StandardEnemyModel.getMaxDistance()) {
                    this.hellWalkingEnemyModel.setDx(-this.hellWalkingEnemyModel.getDx());
                    this.hellWalkingEnemyView.hellEnemyWalkingRight();
                }
            } else if (this.hellWalkingEnemyModel.getDx() > 0) {
                this.hellWalkingEnemyModel.setCurrentPosition(this.hellWalkingEnemyModel.getCurrentPosition() + 1);
                if (this.hellWalkingEnemyModel.getCurrentPosition() > StandardEnemyModel.getMaxDistance()) {
                    this.hellWalkingEnemyModel.setDx(-this.hellWalkingEnemyModel.getDx());
                    this.hellWalkingEnemyView.hellEnemyWalkingLeft();
                }
            }
        }
    }
}
