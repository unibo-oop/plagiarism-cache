package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * ParadiseWalkingMiniBossModel class.
 * 
 */
public class ParadiseWalkingMiniBossModel extends ParadiseWalkingEnemyModel {

    //Attributes:
    private static final OBJECTSTYPE TYPE = OBJECTSTYPE.BOSS_WALKING_ENEMY;
    private static final double HEALTH = 20;
    private static final double DAMAGE_ON_CONTACT = 2;
    private static final double R = 0;
    private static final double DX = 2;
    private static final double DY = 0;
    private static final double DR = 0;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * 
     */
    public ParadiseWalkingMiniBossModel(final double x, final double y) {
        super(x, y, R, DX, DY, DR, TYPE, HEALTH, DAMAGE_ON_CONTACT);
    }
}
