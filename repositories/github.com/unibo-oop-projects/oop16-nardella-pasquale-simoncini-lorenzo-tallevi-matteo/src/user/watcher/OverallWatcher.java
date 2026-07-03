package user.watcher;

/**
 * This class represent the overall watcher that keep tracks of the player ship
 * stats.
 */
public final class OverallWatcher {

    private static OverallWatcher overWatch = new OverallWatcher();
    private static final int FACTOR_SCORE = 100;
    private static final int FACTOR_TIME = 30;
    private int currentLevel; // = 0;
    private int life = 3;
    private int health; // = 0;
    private int damage; // = 0;
    private int speed; // = 0;
    private int projectilePerSecond; // = 0;
    private int expendablePoints = 3;

    private OverallWatcher() {
    };

    /**
     * the constructor of the Singleton OverallWatcher.
     * 
     * @return the unique overall watcher.
     */
    public static OverallWatcher getWatcher() {
        return overWatch;
    }

    /**
     * this method resets the watcher at its initial state.
     */
    public void reset() {
        currentLevel = 0;
        life = 3;
        health = 0;
        damage = 0;
        speed = 0;
        projectilePerSecond = 0;
        expendablePoints = 3;
    }

    /**
     * returns value of current level.
     * 
     * @return value of currentLevel.
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * This method sets the variable currentLevel.
     * 
     * @param currentLevel
     *            the current level of the ship.
     */
    public void setCurrentLevel(final int currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * returns value of life.
     * 
     * @return value of life.
     */
    public int getLife() {
        return life;
    }

    /**
     * This method sets the variable life.
     * 
     * @param life
     *            the current life of the ship.
     */
    public void setLife(final int life) {
        this.life = life;
    }

    /**
     * returns value of health.
     * 
     * @return value of health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * This method sets the variable health.
     * 
     * @param health
     *            the current health of the ship.
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * returns value of damage.
     * 
     * @return value of damage.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * This method sets the variable damage.
     * 
     * @param damage
     *            the current damage of the ship.
     */
    public void setDamage(final int damage) {
        this.damage = damage;
    }

    /**
     * returns value of speed.
     * 
     * @return value of speed.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * This method sets the variable speed.
     * 
     * @param speed
     *            the current speed of the ship.
     */
    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    /**
     * returns value of attack speed (projectile per second).
     * 
     * @return value of projectile per second.
     */
    public int getProjectilePerSecond() {
        return projectilePerSecond;
    }

    /**
     * This method sets the variable projectilePerSpeed.
     * 
     * @param projectilePerSecond
     *            the current attack speed of the ship.
     */
    public void setProjectilePerSecond(final int projectilePerSecond) {
        this.projectilePerSecond = projectilePerSecond;
    }

    /**
     * returns value of expendable points.
     * 
     * @return value of expendable points.
     */
    public int getExpendablePoints() {
        return expendablePoints;
    }

    /**
     * This method sets the variable expendablePoints.
     * 
     * @param expendablePoints
     *            the current expendable points of the ship.
     */
    public void setExpendablePoints(final int expendablePoints) {
        this.expendablePoints = expendablePoints;
    }

    /**
     * This method translate the level score to expendable points.
     * 
     * @param score
     *            the level score.
     * 
     * @param time
     *            the level time.
     * 
     */
    public void scoreToExpendablePoints(final int score, final int time) {
        setExpendablePoints(score / FACTOR_SCORE - time / FACTOR_TIME);
    }
}