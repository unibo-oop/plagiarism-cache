package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * MicheleModel class.
 * 
 */
public class MicheleModel extends GameObjectAliveModel {
    //Attributes:
    private static final OBJECTSTYPE MICHELE_TYPE = OBJECTSTYPE.BOSS;
    private static final double MICHELE_HEIGHT = 256;
    private static final double MICHELE_WIDTH = 256;
    private static final double START_D = 0;
    private static final double MICHELE_ROTATION = 0;
    private static final double MICHELE_MAX_HEALTH = 500;
    private static final double DAMAGE_ON_CONTACT = 0.5;

    //Graphic and animation balancing variables:
    /**
     * cos'è.
     */
    public static final int X_OFFSET4 = 700;
    /**
     * cos'è.
     */
    public static final int Y_OFFSET4 = 1525;
    /**
     * cos'è.
     */
    public static final int Y_OFFSET3 = 1125;
    /**
     * cos'è.
     */
    public static final int X_OFFSET3 = 500;
    /**
     * cos'è.
     */
    public static final int X_OFFSET = 650;
    /**
     * cos'è.
     */
    public static final int X_OFFSET2 = 625;
    /**
     * cos'è.
     */
    public static final int Y_OFFSET = -100;
    /**
     * cos'è.
     */
    public static final int Y_OFFSET2 = 300;

    private LightBallModel lightBallModel;

    //Animation and graphic variables:
    /**
     * cos'è.
     */
    public static final int MICHELE_X_OFFSET = 400;
    /**
     * cos'è.
     */
    public static final int MICHELE_Y_OFFSET = -450;
    /**
     * cos'è.
     */
    public static final int LIGHTBALL_Y_OFFSET = -200;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * @param lightBallModel
     * 
     */
    public MicheleModel(final double x, final double y, final LightBallModel lightBallModel) {
        super(x, y, MICHELE_ROTATION, MICHELE_TYPE, MICHELE_WIDTH, MICHELE_HEIGHT, START_D, START_D, START_D, MICHELE_MAX_HEALTH, DAMAGE_ON_CONTACT);
        this.lightBallModel = lightBallModel;
    }

    //Getters&setters:
    public final LightBallModel getLightBallModel() {
        return lightBallModel;
    }
}
