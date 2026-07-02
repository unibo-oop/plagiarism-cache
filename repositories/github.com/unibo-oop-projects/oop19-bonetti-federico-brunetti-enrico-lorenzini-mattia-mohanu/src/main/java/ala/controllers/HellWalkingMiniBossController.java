package ala.controllers;

import ala.models.HellWalkingMiniBossModel;
import ala.models.StandardEnemyModel;
import ala.views.HellWalkingMiniBossView;

/**
 * HellWalkingMiniBossController class.
 * 
 */
public class HellWalkingMiniBossController extends HellWalkingEnemyController implements WalkingEnemiesAbilitiesController {

    //Attributes:
    private HellWalkingMiniBossModel hellWalkingMiniBossModel;
    private HellWalkingMiniBossView hellWalkingMiniBossView;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param hellWalkingMiniBossModel
     * @param hellWalkingMiniBossView
     */
    public HellWalkingMiniBossController(final HellWalkingMiniBossModel hellWalkingMiniBossModel, final HellWalkingMiniBossView hellWalkingMiniBossView) {
        super(hellWalkingMiniBossModel, hellWalkingMiniBossView);
        this.hellWalkingMiniBossModel = hellWalkingMiniBossModel;
        this.hellWalkingMiniBossView = hellWalkingMiniBossView;
        this.hellWalkingMiniBossView.hellMiniBossWalkingRight();
    }

    //Getters&Setters:
    public final HellWalkingMiniBossView getHellWalkingMiniBossView() {
        return hellWalkingMiniBossView;
    }

    public final void setHellWalkingMiniBossView(final HellWalkingMiniBossView hellWalkingMiniBossView) {
        this.hellWalkingMiniBossView = hellWalkingMiniBossView;
    }

    //Methods:
    /**
     * make the enemy move left and right to walk on a platform.
     * 
     */
    @Override
    public void moveEnemy() {
        if (this.hellWalkingMiniBossModel.isAlive()) {
            if (this.hellWalkingMiniBossModel.getDx() < 0) {
                this.hellWalkingMiniBossModel.setCurrentPosition(this.hellWalkingMiniBossModel.getCurrentPosition() - 1);
                if (this.hellWalkingMiniBossModel.getCurrentPosition() < -StandardEnemyModel.getMaxDistance()) {
                    this.hellWalkingMiniBossModel.setDx(-this.hellWalkingMiniBossModel.getDx());
                    this.hellWalkingMiniBossView.hellMiniBossWalkingRight();
                }
            } else if (this.hellWalkingMiniBossModel.getDx() > 0) {
                this.hellWalkingMiniBossModel.setCurrentPosition(this.hellWalkingMiniBossModel.getCurrentPosition() + 1);
                if (this.hellWalkingMiniBossModel.getCurrentPosition() > StandardEnemyModel.getMaxDistance()) {
                    this.hellWalkingMiniBossModel.setDx(-this.hellWalkingMiniBossModel.getDx());
                    this.hellWalkingMiniBossView.hellMiniBossWalkingLeft();
                }
            }
        }
    }

}
