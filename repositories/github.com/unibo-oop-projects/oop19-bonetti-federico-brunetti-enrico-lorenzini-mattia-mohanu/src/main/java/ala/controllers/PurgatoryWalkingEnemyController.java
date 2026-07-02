package ala.controllers;

import ala.models.PurgatoryWalkingEnemyModel;
import ala.models.PurgatoryWalkingMiniBossModel;
import ala.models.StandardEnemyModel;
import ala.views.PurgatoryWalkingEnemyView;
import ala.views.PurgatoryWalkingMiniBossView;

/**
 * PurgatoryWalkingEnemyController class.
 * 
 */
public class PurgatoryWalkingEnemyController extends StandardEnemyController implements WalkingEnemiesAbilitiesController {

    //Attributes:
    private PurgatoryWalkingEnemyModel purgatoryWalkingEnemyModel;
    private PurgatoryWalkingEnemyView purgatoryWalkingEnemyView;

    //Constructors:
    /**
     * Constructor.
     * 
     * @param purgatoryWalkingEnemyModel
     * @param purgatoryWalkingEnemyView
     * 
     */
    public PurgatoryWalkingEnemyController(final PurgatoryWalkingEnemyModel purgatoryWalkingEnemyModel, final PurgatoryWalkingEnemyView purgatoryWalkingEnemyView) {
        super(purgatoryWalkingEnemyModel, purgatoryWalkingEnemyView);
        this.purgatoryWalkingEnemyModel = purgatoryWalkingEnemyModel;
        this.purgatoryWalkingEnemyView = purgatoryWalkingEnemyView;
        this.purgatoryWalkingEnemyView.purgatoryEnemyWalkingRight();
    }

    /**
     * Constructor 2.
     * 
     * @param purgatoryWalkingMiniBossModel
     * @param purgatoryWalkingMiniBossView
     * 
     */
    public PurgatoryWalkingEnemyController(final PurgatoryWalkingMiniBossModel purgatoryWalkingMiniBossModel, final PurgatoryWalkingMiniBossView purgatoryWalkingMiniBossView) {
        super(purgatoryWalkingMiniBossModel, purgatoryWalkingMiniBossView);
    }

    //Getters&setters:
    public final PurgatoryWalkingEnemyView getPurgatoryWalkingEnemyView() {
        return purgatoryWalkingEnemyView;
    }

    public final void setPurgatoryWalkingEnemyView(final PurgatoryWalkingEnemyView purgatoryWalkingEnemyView) {
        this.purgatoryWalkingEnemyView = purgatoryWalkingEnemyView;
    }

    //Methods:
    /**
     * make the purgatory enemy move left and right to walk on a platform.
     * 
     */
    @Override
    public void moveEnemy() {
        if (this.purgatoryWalkingEnemyModel.isAlive()) {
            if (this.purgatoryWalkingEnemyModel.getDx() < 0) {
                this.purgatoryWalkingEnemyModel.setCurrentPosition(this.purgatoryWalkingEnemyModel.getCurrentPosition() - 1);
                if (this.purgatoryWalkingEnemyModel.getCurrentPosition() < -StandardEnemyModel.getMaxDistance()) {
                    this.purgatoryWalkingEnemyModel.setDx(-this.purgatoryWalkingEnemyModel.getDx());
                    this.purgatoryWalkingEnemyView.purgatoryEnemyWalkingRight();
                }
            }  else if (this.purgatoryWalkingEnemyModel.getDx() > 0) {
                this.purgatoryWalkingEnemyModel.setCurrentPosition(this.purgatoryWalkingEnemyModel.getCurrentPosition() + 1);
                if (this.purgatoryWalkingEnemyModel.getCurrentPosition() > StandardEnemyModel.getMaxDistance()) {
                    this.purgatoryWalkingEnemyModel.setDx(-this.purgatoryWalkingEnemyModel.getDx());
                    this.purgatoryWalkingEnemyView.purgatoryEnemyWalkingLeft();
                }
            }
        }
    }
}

