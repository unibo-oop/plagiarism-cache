package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * CainoModel class.
 * 
 */
public class CainoModel extends GameObjectAliveModel {
    //Attributes:
    private static final OBJECTSTYPE CAINO_TYPE = OBJECTSTYPE.BOSS;
    private static final double CAINO_HEIGHT = 172;
    private static final double CAINO_WIDTH = 86;
    private static final double CAINO_D = 0;
    private static final double CAINO_ROTATION = 0;
    private static final double CAINO_MAX_HEALTH = 300;
    private static final double CAINO_ON_CONTACT = 1;

    private MoonModel moonModel;
    private AsteroidModel asteroidModel;

    //Animation Offsets variables:
    /**
     *  offset in the x coordinates for the animations.
     */
    public static final int X_OFFSET = 640;
    /**
     * offset in the y coordinates for the animations.
     */
    public static final int Y_OFFSET = -200;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * @param moonModel
     * @param asteroidModel
     * 
     */
    public CainoModel(final double x, final double y, final MoonModel moonModel, final AsteroidModel asteroidModel) {
        super(x, y, CAINO_ROTATION, CAINO_TYPE, CAINO_WIDTH, CAINO_HEIGHT, CAINO_D, CAINO_D, CAINO_D, CAINO_MAX_HEALTH, CAINO_ON_CONTACT);
        this.moonModel = moonModel;
        this.asteroidModel = asteroidModel;
    }

    //Getters&Setters:
    public final MoonModel getMoonModel() {
        return moonModel;
    }

    public final AsteroidModel getAsteroidModel() {
        return asteroidModel;
    }
}
