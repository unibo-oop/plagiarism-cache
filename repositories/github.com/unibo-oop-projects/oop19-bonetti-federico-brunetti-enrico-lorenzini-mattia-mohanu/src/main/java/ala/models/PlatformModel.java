package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;
/**
 * PlatformModel class.
 * 
 */
public class PlatformModel extends GameObjectModel {
    private static final double PLATFORM_HEIGHT = 100;
    private static final double PLATFORM_WIDTH = 540;
    private static final int PLATFORM_DAMAGE_ON_CONTACT = 0;
    private static final double PLATFORM_R = 0;
    private static final OBJECTSTYPE PLATFORM_TYPE = OBJECTSTYPE.PLATFORM;

    /**
     * animation setting variable.
     */
    public static final int PLATFORM_HITBOX_X_OFFSET = 540;
    /**
     * animation setting variable.
     */
    public static final int PLATFORM_HITBOX_Y_OFFSET = 100;
    /**
     * animation setting variable.
     */
    public static final int PLATFORM_Y_OFFSET = 20;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * 
     */
    public PlatformModel(final double x, final double y) {
        super(x, y, PLATFORM_R, PLATFORM_TYPE, PLATFORM_WIDTH, PLATFORM_HEIGHT, PLATFORM_DAMAGE_ON_CONTACT);
    }
}
