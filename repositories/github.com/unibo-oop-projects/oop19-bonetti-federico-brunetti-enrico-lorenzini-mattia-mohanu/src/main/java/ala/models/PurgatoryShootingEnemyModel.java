package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * PurgatoryShootingEnemyModel class.
 * 
 */
public class PurgatoryShootingEnemyModel extends StandardEnemyModel {

    //Attributes:
    private static final OBJECTSTYPE TYPE = OBJECTSTYPE.SHOOTING_ENEMY;
    private static final double HEALTH = 10;
    private static final double DAMAGE_ON_CONTACT = 0;
    private static final double R = 0;
    private static final double DX = 0;
    private static final double DY = 0;
    private static final double DR = 0;
    private static final double WIDTH = 250;
    private static final double HEIGHT = 250;

    //Constructors:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * 
     */
    public PurgatoryShootingEnemyModel(final double x, final double y) {
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
    public PurgatoryShootingEnemyModel(final double x, final double y, final double r, final double dx, final double dy, final double dr, final OBJECTSTYPE type, final double health, final double damageOnContact) {
        super(x, y, R, dx, dy, dr, type, health, damageOnContact, WIDTH, HEIGHT);
    }
}
