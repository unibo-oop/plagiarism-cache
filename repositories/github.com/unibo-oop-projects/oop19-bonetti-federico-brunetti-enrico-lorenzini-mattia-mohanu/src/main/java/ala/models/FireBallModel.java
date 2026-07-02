package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * FireBall class.
 * 
 */
public class FireBallModel extends DynamicGameObjectModel {
    //Attributes:
    private static final double FIREBALL_WIDTH = 128;
    private static final double FIREBALL_HEIGHT = 72;
    private static final double FIREBALL_DELTA_ROTATION = 0;
    private static final double FIREBALL_DELTA_X = 0;
    private static final double FIREBALL_DELTA_Y = 0;
    private static final double DAMAGE_ON_CONTACT = 2;

    //Constructors:
    public FireBallModel() { }

    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * @param r
     * @param type
     * @param dx
     * @param dy
     * @param damageOnContact
     * 
     */
    public FireBallModel(final double x, final double y, final double r, final OBJECTSTYPE type, final double dx, final double dy, final double damageOnContact) {
        super(x, y, r, type, FIREBALL_WIDTH, FIREBALL_HEIGHT, dx, dy, FIREBALL_DELTA_ROTATION, damageOnContact);
    }

    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * @param r
     * @param type
     * 
     */
    public FireBallModel(final double x, final double y, final double r, final OBJECTSTYPE type) {
        super(x, y, r, type, FIREBALL_WIDTH, FIREBALL_HEIGHT, FIREBALL_DELTA_X, FIREBALL_DELTA_Y, FIREBALL_DELTA_ROTATION, DAMAGE_ON_CONTACT);
    }
}
