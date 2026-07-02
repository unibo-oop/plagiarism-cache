package it.unibo.the100dayswar.model.fight.api;

import it.unibo.the100dayswar.model.unit.api.Combatant;

/**
 * Represents a command that executes a battle between two combatant units.
 * 
 * @param <T> type of attacker
 * @param <U> type of defender
 */
public interface BattleCommand<T extends Combatant, U extends Combatant> {
    /**
     * execute the action between combatants.
     * 
     * @param attacker the unit attacker
     * @param defender the unit defender
     */
    void execute(T attacker, U defender);
}
