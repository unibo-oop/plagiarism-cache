package it.unibo.the100dayswar.model.fight.impl;

import it.unibo.the100dayswar.model.fight.api.Battle;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.unit.api.Combatant;
import it.unibo.the100dayswar.model.soldier.api.Soldier;

/**
 * inteface for creating istance of battle using the factory pattern.
 */
public final class BattleFactory {
    /**
     * create an istance of battle.
     */

     private BattleFactory() {

     }

     /**
      * is the function for creating the battle.
      * @param <T>
      * @param <U>
      * @param attacker
      * @param defender
      * @return return the new type of battle required
      */
    @SuppressWarnings("unchecked")
    public static <T extends Combatant, U extends Combatant> Battle<T, U> createBattle(final T attacker, final U defender) {
        if (attacker instanceof Soldier && defender instanceof Soldier) {
            return (Battle<T, U>) new BattleSoldierSoldierImpl();
        } else if (attacker instanceof Soldier && defender instanceof Tower) {
            return (Battle<T, U>) new BattleSoldierTowerImpl();
        } else if (attacker instanceof Tower && defender instanceof Soldier) {
            return (Battle<T, U>) new BattleTowerSoldierImpl();
        } else {
            throw new IllegalArgumentException("Unsupported combatant types");
        }
    }
}
