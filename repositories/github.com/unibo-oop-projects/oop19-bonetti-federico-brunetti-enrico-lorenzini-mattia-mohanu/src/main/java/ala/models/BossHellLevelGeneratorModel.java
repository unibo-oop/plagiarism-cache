package ala.models;

/**
 * BossHellLevelGeneratorModel class.
 * 
 */
public class BossHellLevelGeneratorModel extends NormalHellLevelGeneratorModel {
    //Attributes:
    //Inferno Boss static variables:
    private static final double CERBERO_SPAWN_X = 1200; //Da chiederee a Mirko come usare i chunk per posizionare il boss.
    private static final double CERBERO_SPAWN_Y = 250;

    private static final double FIREBALL_X = -400;
    private static final double FIREBALL_Y = 400;
    private static final double FIREBALL_R = 0;

    private static final double FIRERAIN_X = 0;
    private static final double FIRERAIN_Y = -400;

    private CerberoModel cerberoModel;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param lvlNumber
     * 
     */
    public BossHellLevelGeneratorModel(final LVLNUMBER lvlNumber) {
        super(lvlNumber);

        this.cerberoModel = new CerberoModel(CERBERO_SPAWN_X, CERBERO_SPAWN_Y, new FireBallModel(FIREBALL_X, FIREBALL_Y, FIREBALL_R, OBJECTSTYPE.BOSS), new FireRainModel(FIRERAIN_X, FIRERAIN_Y));
    }

    //Getters&Setters:
    public final CerberoModel getCerberoModel() {
        return cerberoModel;
    }

    public final void setCerberoModel(final CerberoModel cerberoModel) {
        this.cerberoModel = cerberoModel;
    }

    public static double getCerberoSpawnX() {
        return CERBERO_SPAWN_X;
    }

    public static double getCerberoSpawnY() {
        return CERBERO_SPAWN_Y;
    }

}
