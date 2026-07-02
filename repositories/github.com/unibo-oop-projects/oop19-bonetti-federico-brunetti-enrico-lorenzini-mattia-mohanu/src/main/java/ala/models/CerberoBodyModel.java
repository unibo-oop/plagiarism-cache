package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * CerberoBodyModel class.
 * 
 */
public class CerberoBodyModel extends DynamicGameObjectModel {
    //Attributes:
    private static final OBJECTSTYPE TYPE = OBJECTSTYPE.BOSS;
    private static final double START_ROTATION = 0;
    private static final double HEIGHT = 512;
    private static final double WIDTH = 512;
    private static final double D = 0;
    private static final double DAMAGE = 0;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * 
     */
    public CerberoBodyModel(final double x, final double y) {
        super(x, y, START_ROTATION, TYPE, HEIGHT, WIDTH, D, D, D, DAMAGE);
    }
}
