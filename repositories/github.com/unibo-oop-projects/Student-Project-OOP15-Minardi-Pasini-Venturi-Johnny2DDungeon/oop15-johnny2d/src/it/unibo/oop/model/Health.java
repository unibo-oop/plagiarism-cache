package it.unibo.oop.model;

/**
 * Class that models the health of the {@link MainCharacter}.
 */
public class Health {

    public static final int DEFAULT_HEALTH = 3;
    public static final int DEFAULT_MIN_HEALTH = 0;

    private final int maxHealth;
    private final int minHealth;

    private int currentHealth;

    /**
     * Constructor that gives you the possibility to generate a custom
     * {@link Health}
     * 
     * @param minHealth
     *            The value of {@link Health} when the character is considered
     *            Dead
     * @param maxHealth
     *            The value of the max {@link Health}
     * @param initialHealth
     *            The starting {@link Health}
     */
    public Health(final int minHealth, final int maxHealth, final int initialHealth) {
        this.minHealth = minHealth;
        this.maxHealth = maxHealth;
        this.currentHealth = initialHealth;
    }

    /**
     * Constructor that create the {@link Health} of a character
     * 
     * @param maxHealth starting max {@link Health}
     */
    public Health(final int maxHealth) {
        this(DEFAULT_MIN_HEALTH, maxHealth, maxHealth);
    }

    /**
     * Constructor that create the {@link Health} of a character with a default
     * value
     */
    public Health() {
        this(DEFAULT_HEALTH);
    }

    /**
     * Decrease the {@link Health} of the {@link MainCharacter} if it gets
     * damaged by an {@link Enemy}
     * 
     * @param damage
     *            The damage taken
     */
    public void decreaseHealth(final int damage) {
        this.currentHealth -= damage;
        currentHealth = (currentHealth < minHealth ? minHealth : currentHealth);
    }

    /**
     * Increase the {@link Health} of the {@link MainCharacter} if he collects a
     * special {@link Collectable}
     * 
     * @param heal
     *            The heal taken
     */
    public void increaseHealth(final int heal) {
        this.currentHealth += heal;
        currentHealth = (currentHealth > maxHealth ? maxHealth : currentHealth);
    }

    /**
     * Getter for the current {@link Health}
     * @return the current {@link Health}
     */
    public int getCurrentHealth() {
        return this.currentHealth;
    }

    /**
     * This returns true if the {@link Health} is ended and the
     * {@link MainCharacter} is dead
     * @return If the entity is dead
     */
    public boolean isDead() {
        return (this.currentHealth <= minHealth);
    }
}
