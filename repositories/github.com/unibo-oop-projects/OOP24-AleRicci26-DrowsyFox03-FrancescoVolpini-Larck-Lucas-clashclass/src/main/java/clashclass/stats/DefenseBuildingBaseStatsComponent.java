package clashclass.stats;

import clashclass.ecs.AbstractComponent;

/**
 * Represents a component which holds the base stats for defense buildings.
 */
public class DefenseBuildingBaseStatsComponent extends AbstractComponent {
    private final int health;
    private final int damage;
    private final int attackSpeed;
    private final int attackRange;

    /**
     * Constructs the stats component.
     *
     * @param health the health value
     * @param damage the damage value
     * @param attackSpeed the attack speed value
     * @param attackRange the attack range value
     */
    public DefenseBuildingBaseStatsComponent(
            final int health,
            final int damage,
            final int attackSpeed,
            final int attackRange) {
        this.health = health;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.attackRange = attackRange;
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
     * Gets the attack range.
     *
     * @return the attack range
     */
    public int getAttackRange() {
        return this.attackRange;
    }
}
