package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;


/**
 * MoonModel class.
 * 
 */
public class MoonModel extends DynamicGameObjectModel {
    private static final OBJECTSTYPE MOON_TYPE = OBJECTSTYPE.BOSS;
    private static final double MOON_HEIGHT = 100;
    private static final double MOON_WIDTH = 1280;
    private static final double MOON_D = 0;
    private static final double MOON_ROTATION = 0;
    private static final double MOON_DAMAGE_ON_CONTACT = 0;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * 
     */
    public MoonModel(final double x, final double y) {
        super(x, y, MOON_ROTATION, MOON_TYPE, MOON_WIDTH, MOON_HEIGHT, MOON_D, MOON_D, MOON_D, MOON_DAMAGE_ON_CONTACT);
    }
}
