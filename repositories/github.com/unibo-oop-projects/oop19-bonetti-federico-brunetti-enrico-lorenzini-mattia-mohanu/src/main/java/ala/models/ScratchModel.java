package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * ScratchModel class.
 * 
 */
public class ScratchModel extends DynamicGameObjectModel {
    //Attributes:
    private static final double SCRATCH_WIDTH = 58;
    private static final double SCRATCH_HEIGHT = 166;
    private static final double SCRATCH_DELTA_ROTATION = 0;
    private static final double SCRATCH_DELTA_X = 0;
    private static final double SCRATCH_DELTA_Y = 0;
    private static final double DAMAGE_ON_CONTACT = 20;
    private static final OBJECTSTYPE TYPE = OBJECTSTYPE.LUCIFER;

    /**
     * animation variable setting.
     */
    public static final int SCRATCH_LEFT_ROTATION = -90;

    public ScratchModel() { }

    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * @param r
     * 
     */
    public ScratchModel(final double x, final double y, final double r) {
        super(x, y, r, TYPE, SCRATCH_WIDTH, SCRATCH_HEIGHT, SCRATCH_DELTA_X, SCRATCH_DELTA_Y, SCRATCH_DELTA_ROTATION, DAMAGE_ON_CONTACT);
    }
}
