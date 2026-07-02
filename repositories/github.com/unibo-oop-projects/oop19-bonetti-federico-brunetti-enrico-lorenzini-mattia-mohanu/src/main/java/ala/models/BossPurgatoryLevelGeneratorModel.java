package ala.models;

/**
 * BossPurgatoryLevelGeneratorModel class.
 * 
 */
public class BossPurgatoryLevelGeneratorModel extends NormalPurgatoryLevelGeneratorModel {
    //Attributes:
    private static final double CAINO_SPAWN_X = 640;
    private static final double CAINO_SPAWN_Y = -200;

    private static final double MOON_SPAWN_X = 0;
    private static final double MOON_SPAWN_Y = -100;

    private static final double ASTEROID_SPAWN_X = 0;
    private static final double ASTEROID_SPAWN_Y = -200;
    private static final double ASTEROID_ROTATION = 270;

    private CainoModel cainoModel;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param lvlNumber
     * 
     */
    public BossPurgatoryLevelGeneratorModel(final LVLNUMBER lvlNumber) {
        super(lvlNumber);
        cainoModel = new CainoModel(CAINO_SPAWN_X, CAINO_SPAWN_Y, new MoonModel(MOON_SPAWN_X, MOON_SPAWN_Y), new AsteroidModel(ASTEROID_SPAWN_X, ASTEROID_SPAWN_Y, ASTEROID_ROTATION));
    }

    //Getters&Setters:
    public final double getCainoSpawnX() {
        return CAINO_SPAWN_X;
    }

    public final double getCainoSpawnY() {
        return CAINO_SPAWN_Y;
    }

    public final CainoModel getCainoModel() {
        return cainoModel;
    }

}
