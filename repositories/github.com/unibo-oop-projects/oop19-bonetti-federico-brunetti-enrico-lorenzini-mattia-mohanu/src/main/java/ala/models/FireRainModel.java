package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * FireRainModel class.
 * 
 */
public class FireRainModel { //FireBall Aggregator.
    //Attributes:
    private FireBallModel fireBallModelA;
    private FireBallModel fireBallModelB;
    private FireBallModel fireBallModelC;

    private static final double ROTATION = 270;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * 
     */
    public FireRainModel(final double x, final double y) {
        this.fireBallModelA = new FireBallModel(x, y, ROTATION, OBJECTSTYPE.BOSS);
        this.fireBallModelB = new FireBallModel(x + CerberoModel.FIRERAIN_MANUAL_HITBOX_X_ADJUSTEMENT, y, ROTATION, OBJECTSTYPE.BOSS);
        this.fireBallModelC = new FireBallModel(x + CerberoModel.FIRERAIN_MANUAL_HITBOX_X_ADJUSTEMENT * 2, y, ROTATION, OBJECTSTYPE.BOSS);
    }

    //Getters&Setters:
    public final FireBallModel getFireBallModelA() {
        return fireBallModelA;
    }

    public final void setFireBallModelA(final FireBallModel fireBallModelA) {
        this.fireBallModelA = fireBallModelA;
    }

    public final FireBallModel getFireBallModelB() {
        return fireBallModelB;
    }

    public final void setFireBallModelB(final FireBallModel fireBallModelB) {
        this.fireBallModelB = fireBallModelB;
    }

    public final FireBallModel getFireBallModelC() {
        return fireBallModelC;
    }

    public final void setFireBallModelC(final FireBallModel fireBallModelC) {
        this.fireBallModelC = fireBallModelC;
    }
}

