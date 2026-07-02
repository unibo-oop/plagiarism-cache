package clashclass.stats;

import clashclass.ecs.AbstractComponent;

/**
 * Represents a component which holds the base stats for troops.
 */
public class TroopBaseStatsComponent extends AbstractComponent {
    private final int health;
    private final int damage;
    private final int attackSpeed;
    private final int movementSpeed;

    /**
     * Constructs the stats component.
     *
     * @param health the health value
     * @param damage the damage value
     * @param attackSpeed the attack speed value
     * @param movementSpeed the movement speed value
     */
    public TroopBaseStatsComponent(
            final int health,
            final int damage,
            final int attackSpeed,
            final int movementSpeed) {
        this.health = health;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.movementSpeed = movementSpeed;
    }

    /**
     * Gets the health.
     *
     * @return the health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Gets the damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Gets the attack speed.
     *
     * @return the attack speed
     */
    public int getAttackSpeed() {
        return this.attackSpeed;
    }

    /**
     * Gets the movement speed.
     *
     * @return the movement speed
     */
    public int getMovementSpeed() {
        return this.movementSpeed;
    }
}
