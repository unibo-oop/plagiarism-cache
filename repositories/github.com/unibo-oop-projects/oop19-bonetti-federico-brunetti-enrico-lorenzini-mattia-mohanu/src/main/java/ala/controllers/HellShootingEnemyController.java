package ala.controllers;

import java.util.List;

import ala.models.FireBallModel;
import ala.models.HellShootingEnemyModel;
import ala.models.HellShootingMiniBossModel;
import ala.models.StandardEnemyModel;
import ala.views.FireBallView;
import ala.views.HellShootingEnemyView;
import ala.views.HellShootingMiniBossView;

/**
 * HellShootingEnemyController class.
 * 
 */
public class HellShootingEnemyController extends StandardEnemyController implements ShootingEnemiesAbilitiesController {

    private static final double R_LEFT = 0;
    private static final double R_RIGHT = 180;
    private static final double DX = 5;
    private static final double DY = 0;
    private static final double FIREBALL_DAMAGE_ON_CONTACT = 5;

    private HellShootingEnemyModel hellShootingEnemyModel;
    private HellShootingEnemyView hellShootingEnemyView;

    private int fireRate;

    /**
     * Constructor 1.
     * 
     * @param hellShootingEnemyModel
     * @param hellShootingEnemyView
     */
    public HellShootingEnemyController(final HellShootingEnemyModel hellShootingEnemyModel, final HellShootingEnemyView hellShootingEnemyView) {
        super(hellShootingEnemyModel, hellShootingEnemyView);
        this.hellShootingEnemyModel = hellShootingEnemyModel;
        this.hellShootingEnemyView = hellShootingEnemyView;
        this.fireRate = 0;
        this.hellShootingEnemyView.hellEnemyShootingLeft();
    }

    /**
     * Constructor 2.
     * 
     * @param hellShootingMiniBossModel
     * @param hellShootingMiniBossView
     * 
     */
    public HellShootingEnemyController(final HellShootingMiniBossModel hellShootingMiniBossModel, final HellShootingMiniBossView hellShootingMiniBossView) {
        super(hellShootingMiniBossModel, hellShootingMiniBossView);
        this.fireRate = 0;
    }

    public final void setFireRate(final int fireRate) {
        this.fireRate = fireRate;
    }

    public static double getRLeft() {
        return R_LEFT;
    }

    public static double getRRight() {
        return R_RIGHT;
    }

    public static double getDx() {
        return DX;
    }

    public static double getDy() {
        return DY;
    }

    public final int getFireRate() {
        return fireRate;
    }

    public static double getFireballDamageOnContact() {
        return FIREBALL_DAMAGE_ON_CONTACT;
    }

    public final HellShootingEnemyModel getHellShootingEnemyModel() {
        return hellShootingEnemyModel;
    }

    public final HellShootingEnemyView getHellShootingEnemyView() {
        return hellShootingEnemyView;
    }

    public final void setHellShootingEnemyModel(final HellShootingEnemyModel hellShootingEnemyModel) {
        this.hellShootingEnemyModel = hellShootingEnemyModel;
    }

    public final void setHellShootingEnemyView(final HellShootingEnemyView hellShootingEnemyView) {
        this.hellShootingEnemyView = hellShootingEnemyView;
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
        if (this.hellShootingEnemyModel.isAlive() && this.hellShootingEnemyModel.isRanged() && y <= this.hellShootingEnemyModel.getY() + HellShootingEnemyModel.SHOOTING_RANGE_ALERT) {
            if (this.fireRate == 0) {
                FireBallModel fireballModel;
                if (x <= this.hellShootingEnemyModel.getX()) {
                    this.hellShootingEnemyView.hellEnemyShootingLeft();
                    fireballModel = new FireBallModel(this.getStandardEnemyModel().getX(), this.getStandardEnemyModel().getY() + this.getStandardEnemyModel().getHeight() / 2, R_LEFT, this.hellShootingEnemyModel.getType(), -DX, DY, FIREBALL_DAMAGE_ON_CONTACT);
                } else {
                    this.hellShootingEnemyView.hellEnemyShootingRight();
                    fireballModel = new FireBallModel(this.getStandardEnemyModel().getX(), this.getStandardEnemyModel().getY() + this.getStandardEnemyModel().getHeight() / 2, -R_RIGHT, this.hellShootingEnemyModel.getType(), DX, DY, FIREBALL_DAMAGE_ON_CONTACT);
                }
                FireBallView fireballView = new FireBallView(this.getStandardEnemyView().getLayer(), fireballModel);
                dynamicObjectsInScene.add(new FireBallController(fireballModel, fireballView));
            } else if (this.fireRate == StandardEnemyModel.FIRE_RATE * 3) {
                this.fireRate = -1;
            } 
            this.fireRate++;
        }
    }
}

