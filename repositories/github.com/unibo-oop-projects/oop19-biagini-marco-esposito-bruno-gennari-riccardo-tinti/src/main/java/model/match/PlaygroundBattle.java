package model.match;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import model.util.Pair;

/**
 * Interface that handle the playground of battleship.
 */
public interface PlaygroundBattle {

    /**
     * Position the ship in this playground starting from passed cell. If a ship
     * will position over another one, it will be throw exception. In this
     * situation, position not be successful.
     * 
     * @param ship      - Ship to position in playground
     * @param firstCell - The first cell (higher and more left used by ship)
     * 
     * @throws CellsFilledException - When ship to position is over existing
     *                                ships.
     */
    void positionShip(Ship ship, Pair<Integer, Integer> firstCell) throws CellsFilledException;
    

    /**
     * Remove ship that cross this cell.
     * 
     * @param cell - Cell crossed by ship to remove
     */
    void removeShipWithCell(Pair<Integer, Integer> cell);

    /**
     * Remove ship passed from playground.
     * 
     * @param ship - Ship to remove.
     */
    void removeShipWithShip(Ship ship);

    /**
     * Remove all ships from playground.
     */
    void removeAllShips();

    /**
     * Get true if cell passed is crossed by a ship.
     * 
     * @param cell - Cell of which you want to know if occupied by a ship
     * 
     * @return true if cell is used
     */
    boolean isCellUsedByShip(Pair<Integer, Integer> cell);

    /**
     * Method to know if cell in grid logic is used.
     * 
     * @param cell - Cell inside grid logic
     * 
     * @return true if in grid logic cell is used, false otherwise
     */
    boolean isCellUsed(Pair<Integer, Integer> cell);

    /**
     * Get a map containing list of occupied cells as key and relative ship.
     * 
     * @return a map of cells occupied and relative ship
     */
    Map<List<Pair<Integer, Integer>>, Ship> getShips();

    /**
     * Method to hit a cell inside grid, it will be throw an exception if cell has been shot previously.
     * An optional containing information of ship is returned if a ship in this cell is hit, it is empty otherwise.
     * 
     * @param cell - Cell where shot
     * @return An Optional containing an Entry of Map with a list of cells ad key and ship hit as valued
     * @throws CellAlreadyShotException if cell passed has been already shot
     */
    Optional<Entry<List<Pair<Integer, Integer>>, Ship>> shipHitted(Pair<Integer, Integer> cell) throws CellAlreadyShotException;

    /**
     * Get an Optional containing true if ship crossing passed cells is sunk, false
     * otherwise. Ship has to cross all cells passed and only them.
     * 
     * @param cells - Cells crossed by ship
     * 
     * @return an optional containing a Boolean equal to true if ship is sunk, 
     *         if cells are not used by a ship optional will be empty. 
     */
    Optional<Boolean> shipSunk(List<Pair<Integer, Integer>> cells);

    /**
     * Get damage inflicted to this playground. Cell used by ship hit inflict one
     * damage point.
     * 
     * @return damage inflicted to playground
     */
    int getDamage();

    /**
     * Getter ships' number still alive.
     * 
     * @return number of alive ship
     */
    int getNumberOfAliveShip();


    /**
     * Getter of logic grid of playground. It could be used to know which cells are
     * crossed by ships during position and which are already hit during battle.
     * 
     * @return the logic grid of playground
     */
    List<List<Boolean>> getLogicGrid();

    /**
     * Reset the logic grid of playground. Use it to know if a cell is used.
     */
    void resetLogicGrid();
}
