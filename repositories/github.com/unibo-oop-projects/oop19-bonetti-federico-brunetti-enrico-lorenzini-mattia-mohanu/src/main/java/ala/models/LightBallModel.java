package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * LightBallModel class.
 * 
 */
public class LightBallModel extends DynamicGameObjectModel {
    //Attributes:
    private static final double LIGHTBALL_WIDTH = 64;
    private static final double LIGHTBALL_HEIGHT = 64;
    private static final double LIGHTBALL_DELTA_X = 0;
    private static final double LIGHTBALL_DELTA_Y = 0;
    private static final double LIGHTBALL_DELTA_ROTATION = 0;
    private static final OBJECTSTYPE LIGHTBALL_TYPE = OBJECTSTYPE.BOSS;
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
    public LightBallModel(final double x, final double y, final double r) {
        super(x, y, r, LIGHTBALL_TYPE, LIGHTBALL_WIDTH, LIGHTBALL_HEIGHT, LIGHTBALL_DELTA_X, LIGHTBALL_DELTA_Y, LIGHTBALL_DELTA_ROTATION, DAMAGE_ON_CONTACT);
    }
}
