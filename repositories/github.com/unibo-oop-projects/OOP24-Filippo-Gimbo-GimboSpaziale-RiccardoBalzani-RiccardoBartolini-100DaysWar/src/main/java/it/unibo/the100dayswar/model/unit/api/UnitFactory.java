package it.unibo.the100dayswar.model.unit.api;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.tower.api.TowerType;

/**
 * Interface that rapresent a factory that manages the creation
 * of the types of Unit in the game.
 */
public interface UnitFactory {
    /**
     * Creates a soldier.
     * 
     * @param player the player that own the soldier
     * @return the soldier created
     */
    Soldier createSoldier(Player player);

    /**
     * Creates a tower.
     * 
     * @param player the owner of the tower
     * @param type the type of the tower
     * @param position the position of the tower
     * @return the tower created
     */
    Tower createTower(Player player, TowerType type, Cell position);
}
