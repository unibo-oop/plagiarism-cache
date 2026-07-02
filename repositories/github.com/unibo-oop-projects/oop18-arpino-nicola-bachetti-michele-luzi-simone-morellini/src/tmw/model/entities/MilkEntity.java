package tmw.model.entities;

/**
 * This interface represents the main character of the game. This interface
 * shows methods to recover the default value of the entity which are used by
 * the items.
 * 
 */
public interface MilkEntity extends GameEntity {

    /**
     * This method is used to know if the main character if the game is finished,
     * meaning if the main character is still alive.
     * 
     * @return true if the game is ended, false otherwise
     */
    boolean isThisTheEnd();

    /**
     * Returns the max value of hp.
     * 
     * @return the max value of hp
     */
    int getMaxHp();

    /**
     * Heals the main character.
     * 
     * @param amount - the amount of hp that the main character recover
     */
    void heal(int amount);

    /**
     * Returns the default time for reloading.
     * 
     * @return the default reload time
     */
    int getDefaultTimeForReload();

    /**
     * Set the default reload time.
     */
    void setDefaultTimeForReload();

    /**
     * Returns the actually time for reloading.
     * 
     * @return the reload time at that moment
     */
    int getTimeForReload();

    /**
     * Set the time for reload a new bullet to shoot.
     * 
     * @param timeForReload - the reload time to be set
     */
    void setTimeForReload(int timeForReload);

    /**
     * Returns the default damage of the bullet shot by the main character.
     * 
     * @return the default damage of the main character's bullet
     */
    int getDefaultDamage();

    /**
     * Set the default reload bullet's damage.
     */
    void setDefaultDamage();

    /**
     * Returns the bullet's damage shot by the main character.
     * 
     * @return the bullet's damage
     */
    int getDamage();

    /**
     * Set the bullet's damage shot by the main character.
     * 
     * @param newDamage - the bullet's damage to be set
     */
    void setDamage(int newDamage);

    /**
     * Returns the default speed.
     * 
     * @return the default speed
     */
    double getDefaultSpeed();

    /**
     * Set the default speed.
     */
    void setDefaultSpeed();

}
