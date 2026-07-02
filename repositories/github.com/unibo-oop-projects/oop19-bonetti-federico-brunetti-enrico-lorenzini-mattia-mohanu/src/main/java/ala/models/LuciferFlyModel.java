package ala.models;

/**
 * LuciferFlyModel class.
 * 
 */
public class LuciferFlyModel extends LuciferBasicModel {

    private static final double LUCIFER_X_SPEED = 15;
    private static final double LUCIFER_Y_SPEED = -1;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * 
     */
    public LuciferFlyModel(final double x, final double y) {
        super(x, y, LUCIFER_X_SPEED, LUCIFER_Y_SPEED);
    }

    //Getters:
    public static double getLuciferXSpeed() {
        return LUCIFER_X_SPEED;
    }

    public static double getLuciferYSpeed() {
        return LUCIFER_Y_SPEED;
    }
}
