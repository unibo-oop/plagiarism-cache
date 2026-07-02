package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * CerberoImportantHeadModel class.
 * 
 */
public class CerberoImportantHeadModel extends GameObjectAliveModel {
    private static final OBJECTSTYPE TYPE = OBJECTSTYPE.BOSS;
    private static final double START_ROTATION = 0;
    private static final double HEIGHT = 512;
    private static final double WIDTH = 512;
    private static final double D = 0;
    private static final double MAX_HEALTH = 200;
    private static final double DAMAGE = 2;

    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * 
     */
    public CerberoImportantHeadModel(final double x, final double y) {
        super(x, y, START_ROTATION, TYPE, HEIGHT, WIDTH, D, D, D, MAX_HEALTH, DAMAGE);
    }
}

