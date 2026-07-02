package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * ParadiseWalkingEnemyModel class.
 * 
 */
public class ParadiseWalkingEnemyModel extends StandardEnemyModel implements ParadiseEnemiesAbilitiesModel {
    //Attributes:
    private static final OBJECTSTYPE TYPE = OBJECTSTYPE.WALKING_ENEMY;
    private static final double HEALTH = 20;
    private static final double DAMAGE_ON_CONTACT = 1;
    private static final double R = 0;
    private static final double DX = 2;
    private static final double DY = 0;
    private static final double DR = 0;
    private static final double WIDTH = 64;
    private static final double HEIGHT = 128;

    private boolean attacking;

    //Constructors:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * 
     */
    public ParadiseWalkingEnemyModel(final double x, final double y) {
        super(x, y, R, DX, DY, DR, TYPE, HEALTH, DAMAGE_ON_CONTACT, WIDTH, HEIGHT);
        this.attacking = false;
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
    public ParadiseWalkingEnemyModel(final double x, final double y, final double r, final double dx, final double dy, final double dr, final OBJECTSTYPE type, final double health, final double damageOnContact) {
        super(x, y, R, dx, dy, dr, type, health, damageOnContact, WIDTH, HEIGHT);
        this.attacking = false;
    }

    //Getters&Setters:
    public final boolean isAttacking() {
        return attacking;
    }

    public final void setAttacking(final boolean attacking) {
        this.attacking = attacking;
    }

    //Methods:
    /**
     * make paradise walking enemies move right and left if Lucifer is not engaged.
     * 
     */
   @Override
    public final void moveEnemy() {
        if (this.isAlive() && !this.attacking) {
            if (this.getDx() < 0) {
                this.setCurrentPosition(this.getCurrentPosition() - 1);
                if (this.getCurrentPosition() < -StandardEnemyModel.getMaxDistance()) {
                    this.setDx(DX);
                }
            } else if (this.getDx() > 0) {
                this.setCurrentPosition(this.getCurrentPosition() + 1);
                if (this.getCurrentPosition() > StandardEnemyModel.getMaxDistance()) {
                    this.setDx(-DX);
                }
            }
        }
    }
}
