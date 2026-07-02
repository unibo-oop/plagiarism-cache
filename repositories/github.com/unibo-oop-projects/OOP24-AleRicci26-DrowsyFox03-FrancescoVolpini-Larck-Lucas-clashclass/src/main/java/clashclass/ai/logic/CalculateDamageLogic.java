package clashclass.ai.logic;

import clashclass.ecs.GameObject;

/**
 * Represents a single logic which calculates the damage inflicted to an entity.
 */
@FunctionalInterface
public interface CalculateDamageLogic {
    /**
     * Calculates the damage.
     *
     * @param actor the source attacker
     * @param target the entity being attacked
     *
     * @return the damage
     */
    int calculateDamage(GameObject actor, GameObject target);
}
