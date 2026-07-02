package it.unibo.the100dayswar.model.fight.impl;

import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.fight.api.BattleSoldierTower;

/**
 * Implementation of the battle between a soldier (attacker) and a tower (defender).
 */
public class BattleSoldierTowerImpl implements BattleSoldierTower {

    private static final int DEFAULT_DAMAGE = 30;
    /** 
     * start a fight between a soldier and a tower.
     */
    @Override
    public void startFight(final Soldier attacker, final Tower defender) {
        final int damage = DEFAULT_DAMAGE * attacker.getLevel();
        defender.takeDamage(damage);
    }
}
