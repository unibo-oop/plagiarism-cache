package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;


/**
 * HellWalkingEnemyModel class.
 * 
 */
public class HellWalkingEnemyModel extends StandardEnemyModel {
    //Attributes:
    private static final OBJECTSTYPE TYPE = OBJECTSTYPE.WALKING_ENEMY;
    private static final double HEALTH = 10;
    private static final double DAMAGE_ON_CONTACT = 1;
    private static final double R = 0;
    private static final double DX = 2; 
    private static final double DY = 0;
    private static final double DR = 0;
    private static final double WIDTH = 256;
    private static final double HEIGHT = 128;

    //Constructors:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * 
     */
    public HellWalkingEnemyModel(final double x, final double y) {
        super(x, y, R, DX, DY, DR, TYPE, HEALTH, DAMAGE_ON_CONTACT, WIDTH, HEIGHT);
    }

    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * @param r
     * @param dx
     * @param dy
     * @param dr
     * @param type
     * @param health
     * @param damageOnContact
     * 
     */
    public HellWalkingEnemyModel(final double x, final double y, final double r, final double dx, final double dy, final double dr, final OBJECTSTYPE type, final double health, final double damageOnContact) {
        super(x, y, r, dx, dy, dr, type, health, damageOnContact, WIDTH, HEIGHT);
    }
}
