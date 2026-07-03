package model.entities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents all the types of characters of the game and their properties.
 */
public enum CharacterType {

    /**
     * The player. The character played by the user.
     */
    PLAYER(30, 7, 0, 10, 16, 30),

    /**
     * The turret. A weak enemy.
     */
    TURRET(25, 0, 1, 3, 30, 0),

    /**
     * The tank. It is more difficult to kill.
     */
    TANK(42, 3, 1, 5, 45, 0),

    /**
     * The final boss of the game.
     */
    BOSS(54, 4, 2, 100, 13, 0);

    private static final List<CharacterType> VALUES = Collections.unmodifiableList(Arrays.asList(TURRET, TANK));
    private static final Random RANDOM = new Random();

    private final double radius;
    private final double steps;
    private final double damage;
    private final double life;
    private final double fireRate;
    private final double knockbackDelay;

    CharacterType(final double radius, final double steps, final double damage, final double life,
            final double fireRate, final double knockBackDelay) {
        this.radius = radius;
        this.steps = steps;
        this.damage = damage;
        this.life = life;
        this.fireRate = fireRate;
        this.knockbackDelay = knockBackDelay;
    }

    /**
     * Get the radius this type of character.
     * 
     * @return The radius.
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Get the steps, which represents the speed, of this type of character.
     * 
     * @return The radius.
     */
    public double getSteps() {
        return this.steps;
    }

    /**
     * Get the collision's damage of this type of character. This damage is made
     * by the character when it collide with another character.
     * 
     * @return The collision's damage.
     */
    public double getDamage() {
        return this.damage;
    }

    /**
     * Get the life or health this type of character.
     * 
     * @return The life.
     */
    public double getLife() {
        return this.life;
    }

    /**
     * Get the fire rate this type of character.
     * 
     * @return The fire rate.
     */
    public double getFireRate() {
        return this.fireRate;
    }

    /**
     * Get the knockback delay of this type of character. This delay makes the
     * character invulnerable for a certain amount of time after that it has
     * been damaged.
     * 
     * @return The knockback delay.
     */
    public double getKnockbackDelay() {
        return this.knockbackDelay;
    }

    /**
     * Select a random EnemyType.
     * 
     * @return A random EnemyType value.
     */
    public static CharacterType getRandomEnemy() {
        return VALUES.get(RANDOM.nextInt(VALUES.size()));
    }

}