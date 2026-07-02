package zombieversity.model.entities.weapon;

import zombieversity.model.entities.ActiveEntity;

/**
 * Representation of an attack to damage enemies that can either have an end or not.
 */
public interface Attack extends ActiveEntity {

    /**
     * @return
     *          The characteristic damage of this attack.
     */
    int getDamage();

    /**
     * @return
     *          True if the attack has ended its trajectory, False otherwise.
     */
    boolean hasEnded();

}
