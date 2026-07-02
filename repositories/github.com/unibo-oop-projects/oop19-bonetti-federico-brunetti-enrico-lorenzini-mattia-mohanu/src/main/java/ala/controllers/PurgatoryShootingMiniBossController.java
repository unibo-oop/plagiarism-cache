package ala.controllers;

import java.util.List;

import ala.models.FireBallModel;
import ala.models.PurgatoryShootingMiniBossModel;
import ala.models.StandardEnemyModel;
import ala.views.FireBallView;
import ala.views.PurgatoryShootingMiniBossView;

/**
 * PurgatoryShootingMiniBossController class.
 * 
 */
public class PurgatoryShootingMiniBossController extends PurgatoryShootingEnemyController implements ShootingEnemiesAbilitiesController {

    //Attributes:
    private PurgatoryShootingMiniBossModel purgatoryShootingMiniBossModel;
    private PurgatoryShootingMiniBossView purgatoryShootingMiniBossView;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param purgatoryShootingMiniBossModel
     * @param purgatoryShootingMiniBossView
     * 
     */
    public PurgatoryShootingMiniBossController(final PurgatoryShootingMiniBossModel purgatoryShootingMiniBossModel, final PurgatoryShootingMiniBossView purgatoryShootingMiniBossView) {
        super(purgatoryShootingMiniBossModel, purgatoryShootingMiniBossView);
        this.purgatoryShootingMiniBossModel = purgatoryShootingMiniBossModel;
        this.purgatoryShootingMiniBossView = purgatoryShootingMiniBossView;
        this.purgatoryShootingMiniBossView.purgatoryMiniBossShootingLeft();
    }

    //Getters&Setters:
    public final PurgatoryShootingMiniBossModel getPurgatoryShootingMiniBossModel() {
        return purgatoryShootingMiniBossModel;
    }

    public final PurgatoryShootingMiniBossView getPurgatoryShootingMiniBossView() {
        return purgatoryShootingMiniBossView;
    }

    public final void setPurgatoryShootingMiniBossModel(final PurgatoryShootingMiniBossModel purgatoryShootingMiniBossModel) {
        this.purgatoryShootingMiniBossModel = purgatoryShootingMiniBossModel;
    }

    public final void setPurgatoryShootingMiniBossView(final PurgatoryShootingMiniBossView purgatoryShootingMiniBossView) {
        this.purgatoryShootingMiniBossView = purgatoryShootingMiniBossView;
    }

    /**
     * Method that makes purgatory shooting miniboss fire against Lucifer.
     *
     * @param x
     *        need to know Lucifer x position to fire on it
     * @param y
     *        need to know Lucifer y position to fire on it
     * @param dynamicObjectsInScene
     */
    public void fire(final double x, final double y, final List<DynamicGameObjectController> dynamicObjectsInScene) {
        if (this.purgatoryShootingMiniBossModel.isAlive() && this.purgatoryShootingMiniBossModel.isRanged() && y <= this.purgatoryShootingMiniBossModel.getY() + StandardEnemyModel.PERCENT_OF_MOVEMENT_BEFORE_FIRING) {
            if (this.getFireRate() == 0) {
                FireBallModel fireballModel;
                if (x <= this.purgatoryShootingMiniBossModel.getX()) {
                    this.purgatoryShootingMiniBossView.purgatoryMiniBossShootingLeft();
                    fireballModel = new FireBallModel(this.getStandardEnemyModel().getX(), this.getStandardEnemyModel().getY() + this.getStandardEnemyModel().getHeight() / 2, getRLeft(), this.purgatoryShootingMiniBossModel.getType(), -getDx(), getDy(), getFireballDamageOnContact());
                } else {
                    this.purgatoryShootingMiniBossView.purgatoryMiniBossShootingRight();
                    fireballModel = new FireBallModel(this.getStandardEnemyModel().getX(), this.getStandardEnemyModel().getY() + this.getStandardEnemyModel().getHeight() / 2, getRRight(), this.purgatoryShootingMiniBossModel.getType(), getDx(), getDy(), getFireballDamageOnContact());
                }
                FireBallView fireballView = new FireBallView(this.getStandardEnemyView().getLayer(), fireballModel);
                dynamicObjectsInScene.add(new FireBallController(fireballModel, fireballView));
            } else if (this.getFireRate() == StandardEnemyModel.FIRE_RATE * 3) {
                this.setFireRate(-1);
            } 
            this.setFireRate(this.getFireRate() + 1);
        }
    }
}
