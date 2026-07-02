package it.unibo.aknightstale.models.entity;

public interface AttackEntity extends Entity {
    /**
     * Gets the entity damage.
     * 
     * @return the entity damage.
     */
    double getDamage();

    /**
     * Sets the entity damage.
     * 
     * @param dmg.
     */
    void setDamage(double dmg);

    /**
     * Gets the attack range of entity.
     * 
     * @return the attack range.
     */
    double getAttackRange();

    /**
     * Attacks another entity and deals damage to him.
     * 
     * @param e the other entity.
     */
    void attack(LifeEntity e);
}
