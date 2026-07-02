package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * HellShootingMiniBossModel class.
 * 
 */
public class HellShootingMiniBossModel extends HellShootingEnemyModel {
    //Attributes:
    private static final OBJECTSTYPE TYPE = OBJECTSTYPE.BOSS_SHOOTING_ENEMY;
    private static final double HEALTH = 20;
    private static final double DAMAGE_ON_CONTACT = 0;
    private static final double R = 0;
    private static final double DX = 0;
    private static final double DY = 0;
    private static final double DR = 0;

    /**
     * range alert distance.
     */
    public static final int SHOOTING_RANGE_ALERT = 50;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * 
     */
    public HellShootingMiniBossModel(final double x, final double y) {
        super(x, y, R, DX, DY, DR, TYPE, HEALTH, DAMAGE_ON_CONTACT);
    }
}
