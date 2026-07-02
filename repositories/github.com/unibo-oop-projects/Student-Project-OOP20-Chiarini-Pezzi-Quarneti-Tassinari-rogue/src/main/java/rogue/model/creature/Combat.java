package rogue.model.creature;

/**
 * 
 *   An interface modeling the combat between the {@link Player} and the {@link Monster}.
 */
public interface Combat {

    enum Result {

       /** 
        * Miss if the attacker miss the defender.
        */
        MISS,

        /** 
         * Hit if the attacker hit the defender.
         */
        HIT,

        /** 
         * Poison if the attacker hit the defender and did another damage with poison.
         */
        POISON,

        /** 
         * Poison if the attacker hit the defender and he steal health points.
         */
        DRAINLIFE,

        /** 
         * Dead if the attacker hit the defender and the defender is dead.
         */
        DEAD;
     }

    /**
     * 
     * @param attacker
     *          who made the attack
     * @param defender
     *         who defends himself from the attack
     * @return the result of the attack
     */
     Result attack(Creature<?> attacker, Creature<?> defender);

}
