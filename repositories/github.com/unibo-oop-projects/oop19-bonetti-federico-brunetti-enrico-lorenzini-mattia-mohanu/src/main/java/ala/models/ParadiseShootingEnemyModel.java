package ala.models;

/**
 * ParadiseShootingEnemyModel class.
 * 
 */
import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

public class ParadiseShootingEnemyModel extends StandardEnemyModel implements ParadiseEnemiesAbilitiesModel {
    //Attributes:
    private static final OBJECTSTYPE TYPE = OBJECTSTYPE.SHOOTING_ENEMY;
    private static final double HEALTH = 10;
    private static final double DAMAGE_ON_CONTACT = 0;
    private static final double R = 0;
    private static final double DX = 2;
    private static final double DY = 0;
    private static final double DR = 0;
    private static final double WIDTH = 64;
    private static final double HEIGHT = 128;

    private boolean firing;

    //Constructors:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * 
     */
    public ParadiseShootingEnemyModel(final double x, final double y) {
        super(x, y, R, DX, DY, DR, TYPE, HEALTH, DAMAGE_ON_CONTACT, WIDTH, HEIGHT);
        this.firing = false;
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
    public ParadiseShootingEnemyModel(final double x, final double y, final double r, final double dx, final double dy, final double dr, final OBJECTSTYPE type, final double health, final double damageOnContact) {
        super(x, y, R, dx, dy, dr, type, health, damageOnContact, WIDTH, HEIGHT);
        this.firing = false;
    }

    //Getters&Setters:
    public final boolean isFiring() {
        return firing;
    }

    public final  void setFiring(final boolean firing) {
        this.firing = firing;
    }

    //Methods:
    /**
     * make paradise shooting enemies move right and left up to a maximum distance.
     * 
     */
   @Override
    public void moveEnemy() {
        if (this.isAlive() && !this.firing) {
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
