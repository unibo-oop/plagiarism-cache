package ala.models;

/**
 * BossParadiseLevelGeneratorModel class.
 * 
 */
public class BossParadiseLevelGeneratorModel extends NormalParadiseLevelGeneratorModel { //fare view e controllers dei vari gamebosslevel
    //Attributes:
    //Inferno Boss static variables:
    private static final double MICHELE_SPAWN_X = 400;
    private static final double MICHELE_SPAWN_Y = -450;

    private static final double LIGHTBALL_SPAWN_X = 0;
    private static final double LIGHTBALL_SPAWN_Y = -200;
    private static final double LIGHTBALL_ROTATION = 270;

    private MicheleModel micheleModel;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param lvlNumber
     * 
     */
    public BossParadiseLevelGeneratorModel(final LVLNUMBER lvlNumber) {
        super(lvlNumber);
        this.micheleModel = new MicheleModel(MICHELE_SPAWN_X, MICHELE_SPAWN_Y, new LightBallModel(LIGHTBALL_SPAWN_X, LIGHTBALL_SPAWN_Y, LIGHTBALL_ROTATION));
    }

    //Getters&Setters:
    public final double getMicheleSpawnX() {
        return MICHELE_SPAWN_X;
    }

    public final double getMicheleSpawnY() {
        return MICHELE_SPAWN_Y;
    }

    public final MicheleModel getMicheleModel() {
        return micheleModel;
    }
}
