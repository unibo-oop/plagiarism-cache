package it.unibo.the100dayswar.model.fight.impl;

import it.unibo.the100dayswar.model.fight.api.BattleCommand;
import it.unibo.the100dayswar.model.unit.api.Combatant;

/** 
 * Implementation of the BattleCommand interface that represents a battle between two combatants.
 * 
 * @param <T> the type of the first combatant
 * @param <U> the type of the second combatant
 */
public class GenericBattleCommand<T extends Combatant, U extends Combatant> implements BattleCommand<T, U> {
    /** 
     * execute action between combatant.
     */
    @Override
    public void execute(final T combatant1, final U combatant2) {
        BattleFactory.createBattle(combatant1, combatant2).startFight(combatant1, combatant2);
    }
}
