package ala.controllers;

import java.util.List;

import ala.models.FireBallModel;
import ala.models.PurgatoryShootingEnemyModel;
import ala.models.PurgatoryShootingMiniBossModel;
import ala.models.StandardEnemyModel;
import ala.views.FireBallView;
import ala.views.PurgatoryShootingEnemyView;
import ala.views.PurgatoryShootingMiniBossView;

/**
 * PurgatoryShootingEnemyController class.
 * 
 */
public class PurgatoryShootingEnemyController extends StandardEnemyController implements ShootingEnemiesAbilitiesController {
    //Attributes:
    private static final double R_LEFT = 0;
    private static final double R_RIGHT = 180;
    private static final double DX = 7;
    private static final double DY = 0;
    private static final double FIREBALL_DAMAGE_ON_CONTACT = 10;

    private PurgatoryShootingEnemyModel purgatoryShootingEnemyModel;
    private PurgatoryShootingEnemyView purgatoryShootingEnemyView;

    private int fireRate;

    //Constructors:
    /**
     * Constructor 1.
     * 
     * @param purgatoryShootingEnemyModel
     * @param purgatoryShootingEnemyView
     */
    public PurgatoryShootingEnemyController(final PurgatoryShootingEnemyModel purgatoryShootingEnemyModel, final PurgatoryShootingEnemyView purgatoryShootingEnemyView) {
        super(purgatoryShootingEnemyModel, purgatoryShootingEnemyView);
        this.purgatoryShootingEnemyModel = purgatoryShootingEnemyModel;
        this.purgatoryShootingEnemyView = purgatoryShootingEnemyView;
        this.fireRate = 0;
        this.purgatoryShootingEnemyView.purgatoryEnemyShootingLeft();
    }

    /**
     * Constructor 2.
     * 
     * @param purgatoryShootingMiniBossModel
     * @param purgatoryShootingMiniBossView
     * 
     */
    public PurgatoryShootingEnemyController(final PurgatoryShootingMiniBossModel purgatoryShootingMiniBossModel, final PurgatoryShootingMiniBossView purgatoryShootingMiniBossView) {
        super(purgatoryShootingMiniBossModel, purgatoryShootingMiniBossView);
        this.fireRate = 0;
    }

   //Getters&Setters:
    public final double getRLeft() {
        return R_LEFT;
    }

    public final double getRRight() {
        return R_RIGHT;
    }

    public final double getDx() {
        return DX;
    }

    public final double getDy() {
        return DY;
    }

    public final double getFireballDamageOnContact() {
        return FIREBALL_DAMAGE_ON_CONTACT;
    }

    public final PurgatoryShootingEnemyModel getPurgatoryShootingEnemyModel() {
        return purgatoryShootingEnemyModel;
    }

    public final PurgatoryShootingEnemyView getPurgatoryShootingEnemyView() {
        return purgatoryShootingEnemyView;
    }

    public final int getFireRate() {
        return fireRate;
    }

    public final void setPurgatoryShootingEnemyModel(final PurgatoryShootingEnemyModel purgatoryShootingEnemyModel) {
        this.purgatoryShootingEnemyModel = purgatoryShootingEnemyModel;
    }

    public final void setPurgatoryShootingEnemyView(final PurgatoryShootingEnemyView purgatoryShootingEnemyView) {
        this.purgatoryShootingEnemyView = purgatoryShootingEnemyView;
    }

    public final void setFireRate(final int fireRate) {
        this.fireRate = fireRate;
    }

    //Methods:
    /**
     * Method that makes purgatory shooting enemies fire against Lucifer.
     * 
     * @param x
     *        need to know Lucifer x position to fire on it
     * @param y
     *        need to know Lucifer y position to fire on it
     *        @param dynamicObjectsInScene
     */
    @Override
    public void fire(final double x, final double y, final List<DynamicGameObjectController> dynamicObjectsInScene) {
        if (this.purgatoryShootingEnemyModel.isAlive() && this.purgatoryShootingEnemyModel.isRanged() && y <= this.purgatoryShootingEnemyModel.getY() + StandardEnemyModel.PERCENT_OF_MOVEMENT_BEFORE_FIRING) {
            if (this.fireRate == 0) {
                FireBallModel fireballModel;
                if (x <= this.purgatoryShootingEnemyModel.getX()) {
                    this.purgatoryShootingEnemyView.purgatoryEnemyShootingLeft();
                    fireballModel = new FireBallModel(this.getStandardEnemyModel().getX(), this.getStandardEnemyModel().getY() + this.getStandardEnemyModel().getHeight() / 2, R_LEFT, this.purgatoryShootingEnemyModel.getType(), -DX, DY, FIREBALL_DAMAGE_ON_CONTACT);
                } else {
                    this.purgatoryShootingEnemyView.purgatoryEnemyShootingRight();
                    fireballModel = new FireBallModel(this.getStandardEnemyModel().getX(), this.getStandardEnemyModel().getY() + this.getStandardEnemyModel().getHeight() / 2, R_RIGHT, this.purgatoryShootingEnemyModel.getType(), DX, DY, FIREBALL_DAMAGE_ON_CONTACT);
                }
                FireBallView fireballView = new FireBallView(this.getStandardEnemyView().getLayer(), fireballModel);
                dynamicObjectsInScene.add(new FireBallController(fireballModel, fireballView));
            } else if (this.fireRate == StandardEnemyModel.FIRE_RATE * 2) {
                this.fireRate = -1;
            } 
            this.fireRate++;
        }
    }
}

