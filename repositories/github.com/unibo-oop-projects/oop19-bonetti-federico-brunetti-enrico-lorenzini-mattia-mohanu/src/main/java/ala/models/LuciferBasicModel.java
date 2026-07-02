package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * LuciferBasicModel class.
 * 
 */
public abstract class LuciferBasicModel extends GameObjectAliveModel {

    //Attributes:
    private static final double LUCIFER_MAX_HEALTH = 100;
    private static final double LUCIFER_DAMAGE_ON_CONTACT = 0;
    private static final double START_D = 0;
    private static final double LUCIFER_WIDTH = 86;
    private static final double LUCIFER_HEIGHT = 172;

    private int lastDirection = 0;
    private static final double LUCIFER_R = 0;
    private static final OBJECTSTYPE LUCIFER_TYPE = OBJECTSTYPE.LUCIFER;
    private static final double LUCIFER_MAX_X = 1280 - LUCIFER_WIDTH;
    private static final double LUCIFER_MIN_X = 0;
    //Ranges Attack damage:
    private static final double RANGED_ATTACK_DAMAGE = 10;
    //Ranged Attack speed:
    private static final double RANGED_ATTACK_SPEED = 12.0;
    //Ranged Attack charge Variables
    private static final double RANGED_ATTACK_CHARGE_TIME = 45; // the cannon can fire every n frames
    private static final double MELEE_ATTACK_CHARGE_TIME = 30;

    private static final double FINAL_ATTACK_CHARGE_TIME = 600; // 10 seconds
    private double rangedAttackChargeCounter = RANGED_ATTACK_CHARGE_TIME; // initially the cannon is charged
    private double meleeAttackChargeCounter = MELEE_ATTACK_CHARGE_TIME;
    private double finalAttackChargeCounter = FINAL_ATTACK_CHARGE_TIME; // initially the cannon is charged

    private static final double ANIMATION_TIME = 60;
    private double animationCounter = ANIMATION_TIME;
    //Ranged Attack Bonus Variables(Possible Final Attack)
    private static final double RANGED_ATTACKS = 8; // number of bullets which the cannon can fire in 1 shot (center, left, right)
    private static final double RANGED_ATTACKS_SPREAD = 0.6; // dx of left and right bullets

    /**
     * cos'è.
     */
    public static final int LUCIFER_ON_PLATFORM_DELTA_BALANCING = 112;
    /**
     * cos'è.
     */
    public static final double LUCIFER_STAMINA_CHARGE_DELTA = 0.2;
    /**
     * cos'è.
     */
    public static final int STAMINA_BAR_DELTA_MOVEMENT = 20;
    /**
     * cos'è.
     */
    public static final int FINAL_ATTACK_BAR_DELTA_MOVEMENT = 40;

    //Constructor:
    /**
     * Constructor 1.
     * 
     * @param x
     * @param y
     * 
     */
    LuciferBasicModel(final double x, final double y) {
        super(x, y, LUCIFER_R, LUCIFER_TYPE, LUCIFER_WIDTH, LUCIFER_HEIGHT, START_D, START_D, START_D, LUCIFER_MAX_HEALTH, LUCIFER_DAMAGE_ON_CONTACT);
    }

    /**
     * Constructor 2.
     * 
     * @param x
     * @param y
     * @param dx
     * @param dy
     * 
     */
    LuciferBasicModel(final double x, final double y, final double dX, final double dY) {
        super(x, y, LUCIFER_R, LUCIFER_TYPE, LUCIFER_WIDTH, LUCIFER_HEIGHT, dX, dY, START_D, LUCIFER_MAX_HEALTH, LUCIFER_DAMAGE_ON_CONTACT);
    }

    public final double getLuciferMaxHealth() {
        return LUCIFER_MAX_HEALTH;
    }

    public final double getLuciferDamageOnContact() {
        return LUCIFER_DAMAGE_ON_CONTACT;
    }

    public final double getStartD() {
        return START_D;
    }

    public final double getLuciferWidth() {
        return LUCIFER_WIDTH;
    }

    public final double getLuciferHeight() {
        return LUCIFER_HEIGHT;
    }

    public final int getLastDirection() {
        return lastDirection;
    }

    public final double getLuciferMaxX() {
        return LUCIFER_MAX_X;
    }

    public final double getLuciferMinX() {
        return LUCIFER_MIN_X;
    }

    public static final double getRangedAttackDamage() {
        return RANGED_ATTACK_DAMAGE;
    }

    public static final double getRangedAttackSpeed() {
        return RANGED_ATTACK_SPEED;
    }

    public static final double getRangedAttackChargeTime() {
        return RANGED_ATTACK_CHARGE_TIME;
    }

    public static final double getFinalAttackChargeTime() {
        return FINAL_ATTACK_CHARGE_TIME;
    }

    public final double getRangedAttackChargeCounter() {
        return rangedAttackChargeCounter;
    }

    public final double getFinalAttackChargeCounter() {
        return finalAttackChargeCounter;
    }

    public static double getAnimationTime() {
        return ANIMATION_TIME;
    }

    public final double getAnimationCounter() {
        return animationCounter;
    }

    public static double getRangedAttacks() {
        return RANGED_ATTACKS;
    }

    public static double getRangedAttacksSpread() {
        return RANGED_ATTACKS_SPREAD;
    }

    public final void setLastDirection(final int lastDirection) {
        this.lastDirection = lastDirection;
    }

    public final void setRangedAttackChargeCounter(final double rangedAttackChargeCounter) {
        this.rangedAttackChargeCounter = rangedAttackChargeCounter;
    }

    public final void setFinalAttackChargeCounter(final double finalAttackChargeCounter) {
        this.finalAttackChargeCounter = finalAttackChargeCounter;
    }

    public final void setAnimationCounter(final double animationCounter) {
        this.animationCounter = animationCounter;
    }

    public final double getMeleeAttackChargeCounter() {
        return meleeAttackChargeCounter;
    }

    public final void setMeleeAttackChargeCounter(final double meleeAttackChargeCounter) {
        this.meleeAttackChargeCounter = meleeAttackChargeCounter;
    }

    public static double getMeleeAttackChargeTime() {
        return MELEE_ATTACK_CHARGE_TIME;
    }

    //Methods:
    /**
     * check bounds to avoid Lucifer to exit from screen.
     * 
     * @param isLeftPressed
     * @param isRightPressed
     * @return boolean
     * 
     */
    public final boolean checkBounds(final boolean isLeftPressed, final boolean isRightPressed) {
        double newX = this.getX();
        // horizontal
        if (Double.compare(newX, this.getLuciferMinX()) <= 0 && isLeftPressed) {
          this.setX(this.getLuciferMinX());
          this.setDx(0);
          return true;
        } else if (Double.compare(newX, this.getLuciferMaxX()) >= 0 && isRightPressed) {
          this.setX(this.getLuciferMaxX());
          this.setDx(0);
          return true;
        } else {
            return false;
        }
    }
}
