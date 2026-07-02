package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * CerberoModel class.
 * 
 */
public final class CerberoModel {
    //Attributes:
    private static final OBJECTSTYPE CERBERO_TYPE = OBJECTSTYPE.BOSS;

    private CerberoImportantHeadModel firstHead;
    private CerberoOtherHeadModel secondHead;
    private CerberoOtherHeadModel thirdHead;
    private CerberoBodyModel cerberoBody;

    private FireBallModel fireBallModel;
    private FireRainModel fireRainModel;

    //Animation and graphic variables:
    /**
     * animation offset.
     */
    public static final int FIRST_HEAD_X_OFFSET = 1200;
    /**
     * animation offset.
     */
    public static final int FIRST_HEAD_Y_OFFSET = 300;
    /**
     * animation offset.
     */
    public static final int MANUAL_HITBOX_X_ADJUSTEMENT = 128;
    /**
     * animation offset.
     */
    public static final int MANUAL_HITBOX_Y_ADJUSTEMENT = 78;
    /**
     * animation offset.
     */
    public static final int FIREBALL_X_OFFSET = -400;
    /**
     * animation offset.
     */
    public static final int FIREBALL_Y_OFFSET = 400;
    /**
     * animation offset.
     */
    public static final int FIRERAIN_X_OFFSET = 200;
    /**
     * animation offset.
     */
    public static final int FIRERAIN_MANUAL_HITBOX_X_ADJUSTEMENT = 300;
    /**
     * animation offset.
     */
    public static final int FIRERAIN_MANUAL_HITBOX_Y_ADJUSTEMENT = -400;
    /**
     * animation offset.
     */
    public static final int SECOND_HEAD_ALIGN = 100;
    /**
     * animation offset.
     */
    public static final int THIRD_HEAD_ALIGN = 200;
    /**
     * animation offset.
     */
    public static final int CERBERO_ALIGN = 600;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * @param fireBallModel
     * @param fireRainModel
     * 
     */
    public CerberoModel(final double x, final double y, final FireBallModel fireBallModel, final FireRainModel fireRainModel) {
        firstHead = new CerberoImportantHeadModel(x, y);
        secondHead = new CerberoOtherHeadModel(x + SECOND_HEAD_ALIGN, y);
        thirdHead = new CerberoOtherHeadModel(x + THIRD_HEAD_ALIGN, y);
        cerberoBody = new CerberoBodyModel(x + CERBERO_ALIGN, y);

        this.fireBallModel = fireBallModel;
        this.fireRainModel = fireRainModel;
    }

    //Getters&Setters:
    public CerberoImportantHeadModel getFirstHead() {
        return firstHead;
    }

    public CerberoOtherHeadModel getSecondHead() {
        return secondHead;
    }

    public CerberoOtherHeadModel getThirdHead() {
        return thirdHead;
    }

    public CerberoBodyModel getCerberoBody() {
        return cerberoBody;
    }

    public FireBallModel getFireBallModel() {
        return fireBallModel;
    }

    public void setFireBallModel(final FireBallModel fireBallModel) {
        this.fireBallModel = fireBallModel;
    }

    public FireRainModel getFireRainModel() {
        return fireRainModel;
    }

    public void setFireRainModel(final FireRainModel fireRainModel) {
        this.fireRainModel = fireRainModel;
    }

    public static OBJECTSTYPE getCerberoType() {
        return CERBERO_TYPE;
    }
}
