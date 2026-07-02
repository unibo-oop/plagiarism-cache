package it.unibo.the100dayswar.model.fight.impl;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.fight.api.BattleTowerSoldier;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.tower.api.Tower;
/**
 * Implementation of the battle between a soldier (attacker) and a tower (defender).
 */
public class BattleTowerSoldierImpl implements BattleTowerSoldier {
    private static final int TOWER_RANGE = 2;
    /**
     * start a fight between a tower and a soldier.
     */
    @Override
    public void startFight(final Tower attacker, final Soldier defender) {
        if (calculateDistance(attacker, defender)) {
            final int damage = attacker.getDamage();
            defender.takeDamage(damage);
        }
    }
    /**
     * calcualte the distance between the tower (attacker) and the soldier (defender).
     * @param tower
     * @param soldier
     * @return true if the tower is in the range, false otherwise
     */
    private boolean calculateDistance(final Tower tower, final Soldier soldier) {
        final Cell towerCell = tower.getPosition();
        final Cell soldierCell = soldier.getPosition();
        final int distanceX = Math.abs(towerCell.getPosition().getX() - soldierCell.getPosition().getX());
        final int distanceY = Math.abs(towerCell.getPosition().getY() - soldierCell.getPosition().getY());
        return distanceX <= TOWER_RANGE && distanceY <= TOWER_RANGE;
    }
}
