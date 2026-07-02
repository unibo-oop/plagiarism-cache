package it.unibo.the100dayswar.model.playeraction.api;

import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Movable;

/**
 * An extension of the command pattern that represents the movement of a movable object.
 */
public interface MovementCommand extends GenericPlayerCommand<Pair<Movable, Cell>> {
    /** 
     * Moves the unit of the player.
     * 
     * @param player the player that moves the unit.
     * @param pair a pair that contains the unit to move and the cell to move to.
     * @throws IllegalArgumentException if the unit is not owned by the player.
     */
    @Override
    void execute(Player player, Pair<Movable, Cell> pair);
}
