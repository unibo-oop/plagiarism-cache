package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * AsteroidModel class.
 * 
 */
public class AsteroidModel extends DynamicGameObjectModel {
    //Attributes:
    private static final double ASTEROID_WIDTH = 64;
    private static final double ASTEROID_HEIGHT = 64;
    private static final double ASTEROID_DELTA_X = 0;
    private static final double ASTEROID_DELTA_Y = 0;
    private static final double ASTEROID_DELTA_ROTATION = 0;
    private static final OBJECTSTYPE ASTEROID_TYPE = OBJECTSTYPE.BOSS;
    private static final double DAMAGE_ON_CONTACT = 2;

    //Constructors:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * @param r
     * 
     */
    public AsteroidModel(final double x, final double y, final double r) {
        super(x, y, r, ASTEROID_TYPE, ASTEROID_WIDTH, ASTEROID_HEIGHT, ASTEROID_DELTA_X, ASTEROID_DELTA_Y, ASTEROID_DELTA_ROTATION, DAMAGE_ON_CONTACT);
    }
}
