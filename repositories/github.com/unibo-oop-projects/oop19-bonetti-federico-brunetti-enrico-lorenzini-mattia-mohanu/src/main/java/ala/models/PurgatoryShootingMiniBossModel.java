package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * PurgatoryShootingMiniBossModel class.
 * 
 */
public class PurgatoryShootingMiniBossModel extends PurgatoryShootingEnemyModel {

    private static final OBJECTSTYPE TYPE = OBJECTSTYPE.BOSS_SHOOTING_ENEMY;
    private static final double HEALTH = 20;
    private static final double DAMAGE_ON_CONTACT = 0;
    private static final double R = 0;
    private static final double DX = 0;
    private static final double DY = 0;
    private static final double DR = 0;

    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * 
     */
    public PurgatoryShootingMiniBossModel(final double x, final double y) {
        super(x, y, R, DX, DY, DR, TYPE, HEALTH, DAMAGE_ON_CONTACT);
    }
}
