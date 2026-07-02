package it.unibo.the100dayswar.model.unit.api;

import it.unibo.the100dayswar.commons.patterns.Observable;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;

/** 
 * An interface for a generic unit of the game.
 */
public interface Unit extends Buyable, Combatant, Observable<Pair<Unit, Cell>> {
  /**
   * This method returns the owner of the unit.
   * 
   * @return the owner of the unit
   */
  Player getOwner();
  /**
  * This method return the cell of the map where the unit is located.
  *
  * @return the current cell of the unit
  */
  Cell getPosition();
  /**
  * Notifies all observers of this Soldier that it has moved to a new cell.
  * 
  * @param target the cell the Soldier has moved to
  */
  void notifyObservers(Cell target);
  /**
   * Increment the level of the Unit by one.
   */
  void incrementLevel();
}
