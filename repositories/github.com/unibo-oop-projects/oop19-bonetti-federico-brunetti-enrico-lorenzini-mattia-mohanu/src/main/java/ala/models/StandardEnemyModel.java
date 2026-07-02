package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * StandardEnemyModel class.
 * 
 */
public abstract class StandardEnemyModel extends GameObjectAliveModel {

    private static final double MAX_DISTANCE = 100;
    private static final int STANDARD_DEATH_ANIMATION_COUNTER_VALUE = 0;
    private static final int ATTACK_RANGE = 650;

    /**
     * cos'è.
     */
    public static final int DEATH_ANIMATION_TIME = 60;
    /**
     * cos'è.
     */
    public static final int FIRE_RATE = 60;
    /**
     * cos'è.
     */
    public static final int MELEE_RATE = 20;
    /**
     * cos'è.
     */
    public static final int PERCENT_OF_MOVEMENT_BEFORE_FIRING = 50;

    private int deathAnimationCounter;
    private int currentPosition;
    private boolean ranged;

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
     * @param width
     * @param height
     * 
     */
    public StandardEnemyModel(final double x, final double y, final double r, final double dx, final double dy, final double dr, final OBJECTSTYPE type, final double health, final double damageOnContact, final double width, final double height) {
        super(x, y, r, type, width, height, dx, dy, dr, health, damageOnContact);
        this.deathAnimationCounter = STANDARD_DEATH_ANIMATION_COUNTER_VALUE;
        this.currentPosition = 0;
        this.ranged = false;
    }

    /**
     * 
     * @return currentPosition
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

    public static int getStandardDeathAnimationCounterValue() {
        return STANDARD_DEATH_ANIMATION_COUNTER_VALUE;
    }

    public final int getDeathAnimationCounter() {
        return deathAnimationCounter;
    }

    public final void setDeathAnimationCounter(final int deathAnimationCounter) {
        this.deathAnimationCounter = deathAnimationCounter;
    }

    public final void setCurrentPosition(final int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public static double getMaxDistance() {
        return MAX_DISTANCE;
    }

    public final boolean isRanged() {
        return ranged;
    }

    public final void setRanged(final boolean ranged) {
        this.ranged = ranged;
    }

    /**
     * check if Lucifer is in range of enemy attacks.
     * 
     * @param luciferY
     *        take trace of Lucifer y position
     * 
     */
    public final void checkRanged(final double luciferY) {
        if (Math.abs(luciferY) - Math.abs(this.getY()) < StandardEnemyModel.ATTACK_RANGE) {
            this.setRanged(true);
        } else {
            this.setRanged(false);
        }
    }

}

