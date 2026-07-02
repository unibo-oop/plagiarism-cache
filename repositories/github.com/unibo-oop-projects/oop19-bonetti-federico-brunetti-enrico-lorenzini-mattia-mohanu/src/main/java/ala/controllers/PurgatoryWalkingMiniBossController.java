package ala.controllers;

import ala.models.PurgatoryWalkingMiniBossModel;
import ala.models.StandardEnemyModel;
import ala.views.PurgatoryWalkingMiniBossView;

/**
 * PurgatoryWalkingMiniBossController class.
 * 
 */
public class PurgatoryWalkingMiniBossController extends PurgatoryWalkingEnemyController implements WalkingEnemiesAbilitiesController {

    //Attributes:
    private PurgatoryWalkingMiniBossModel purgatoryWalkingMiniBossModel;
    private PurgatoryWalkingMiniBossView purgatoryWalkingMiniBossView;

    //Constructors:
    /**
     * Constructor.
     * 
     * @param purgatoryWalkingMiniBossModel
     * @param purgatoryWalkingMiniBossView
     * 
     */
    public PurgatoryWalkingMiniBossController(final PurgatoryWalkingMiniBossModel purgatoryWalkingMiniBossModel, final PurgatoryWalkingMiniBossView purgatoryWalkingMiniBossView) {
        super(purgatoryWalkingMiniBossModel, purgatoryWalkingMiniBossView);
        this.purgatoryWalkingMiniBossModel = purgatoryWalkingMiniBossModel;
        this.purgatoryWalkingMiniBossView = purgatoryWalkingMiniBossView;
        this.purgatoryWalkingMiniBossView.purgatoryMiniBossWalkingRight();
    }

    //Getters&Setters:
    public final PurgatoryWalkingMiniBossView getPurgatoryWalkingMiniBossView() {
        return purgatoryWalkingMiniBossView;
    }

    public final void setPurgatoryWalkingMiniBossView(final PurgatoryWalkingMiniBossView purgatoryWalkingMiniBossView) {
        this.purgatoryWalkingMiniBossView = purgatoryWalkingMiniBossView;
    }

    //Methods:
    /**
     * make the miniboss move left and right to walk on a platform.
     * 
     */
    @Override
    public void moveEnemy() {
        if (this.purgatoryWalkingMiniBossModel.isAlive()) {
            if (this.purgatoryWalkingMiniBossModel.getDx() < 0) {
                this.purgatoryWalkingMiniBossModel.setCurrentPosition(this.purgatoryWalkingMiniBossModel.getCurrentPosition() - 1);
                if (this.purgatoryWalkingMiniBossModel.getCurrentPosition() < -StandardEnemyModel.getMaxDistance()) {
                    this.purgatoryWalkingMiniBossModel.setDx(-this.purgatoryWalkingMiniBossModel.getDx());
                    this.purgatoryWalkingMiniBossView.purgatoryMiniBossWalkingRight();
                }
            } else if (this.purgatoryWalkingMiniBossModel.getDx() > 0) {
                this.purgatoryWalkingMiniBossModel.setCurrentPosition(this.purgatoryWalkingMiniBossModel.getCurrentPosition() + 1);
                if (this.purgatoryWalkingMiniBossModel.getCurrentPosition() > StandardEnemyModel.getMaxDistance()) {
                    this.purgatoryWalkingMiniBossModel.setDx(-this.purgatoryWalkingMiniBossModel.getDx());
                    this.purgatoryWalkingMiniBossView.purgatoryMiniBossWalkingLeft();
                }
            }
        }
    }
}
