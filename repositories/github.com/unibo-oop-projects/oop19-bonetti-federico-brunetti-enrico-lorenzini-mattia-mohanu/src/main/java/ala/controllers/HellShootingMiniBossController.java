package ala.controllers;

import java.util.List;

import ala.models.FireBallModel;
import ala.models.HellShootingMiniBossModel;
import ala.models.StandardEnemyModel;
import ala.views.FireBallView;
import ala.views.HellShootingMiniBossView;

/**
 * HellShootingMiniBossController class.
 * 
 */
public class HellShootingMiniBossController extends HellShootingEnemyController implements ShootingEnemiesAbilitiesController {

    private HellShootingMiniBossModel hellShootingMiniBossModel;
    private HellShootingMiniBossView hellShootingMiniBossView;

    /**
     * Constructor.
     * 
     * @param hellShootingMiniBossModel
     * @param hellShootingMiniBossView
     */
    public HellShootingMiniBossController(final HellShootingMiniBossModel hellShootingMiniBossModel, final HellShootingMiniBossView hellShootingMiniBossView) {
        super(hellShootingMiniBossModel, hellShootingMiniBossView);
        this.hellShootingMiniBossModel = hellShootingMiniBossModel;
        this.hellShootingMiniBossView = hellShootingMiniBossView;
        this.hellShootingMiniBossView.hellMiniBossShootingLeft();
    }

    public final HellShootingMiniBossView getHellShootingMiniBossView() {
        return hellShootingMiniBossView;
    }

    public final void setHellShootingMiniBossView(final HellShootingMiniBossView hellShootingMiniBossView) {
        this.hellShootingMiniBossView = hellShootingMiniBossView;
    }

    public final HellShootingMiniBossModel getHellShootingMiniBossModel() {
        return hellShootingMiniBossModel;
    }

    public final void setHellShootingMiniBossModel(final HellShootingMiniBossModel hellShootingMiniBossModel) {
        this.hellShootingMiniBossModel = hellShootingMiniBossModel;
    }

    /**
     * generate a Fireball that goes left or right depending on Lucifer positiom every 3 seconds.
     * 
     * @param x
     *        need to know Lucifer x position to know the tirection to fire on
     * @param y
     *        need to know Lucifer y position to know when to start firing
     * 
     */
    @Override
    public void fire(final double x, final double y, final List<DynamicGameObjectController> dynamicObjectsInScene) {
        if (this.hellShootingMiniBossModel.isAlive() && this.hellShootingMiniBossModel.isRanged() && y <= this.hellShootingMiniBossModel.getY() + HellShootingMiniBossModel.SHOOTING_RANGE_ALERT) {
            if (this.getFireRate() == 0) {
                FireBallModel fireballModel;
                if (x <= this.hellShootingMiniBossModel.getX()) {
                    this.hellShootingMiniBossView.hellMiniBossShootingLeft();
                    fireballModel = new FireBallModel(this.getStandardEnemyModel().getX(), this.getStandardEnemyModel().getY() + this.getStandardEnemyModel().getHeight() / 2, getRLeft(), this.hellShootingMiniBossModel.getType(), -getDx(), getDy(), getFireballDamageOnContact());
                } else {
                      this.hellShootingMiniBossView.hellMiniBossShootingRight();
                      fireballModel = new FireBallModel(this.getStandardEnemyModel().getX(), this.getStandardEnemyModel().getY() + this.getStandardEnemyModel().getHeight() / 2, getRRight(), this.hellShootingMiniBossModel.getType(), getDx(), getDy(), getFireballDamageOnContact());
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
