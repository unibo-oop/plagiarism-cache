package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * ArrowModel class.
 * 
 */
public class ArrowModel extends DynamicGameObjectModel {
    //Attributes:
    private static final double R = 0;
    private static final double WIDTH = 64;
    private static final double HEIGHT = 64;
    private static final double DX = 0;
    private static final double DY = 2;
    private static final double DR = 0;
    private static final double DAMAGE_ON_CONTACT = 5;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * @param type
     * 
     */
    public ArrowModel(final double x, final double y, final OBJECTSTYPE type) {
        super(x, y, R, type, WIDTH, HEIGHT, DX, DY, DR, DAMAGE_ON_CONTACT); 
    }
}
