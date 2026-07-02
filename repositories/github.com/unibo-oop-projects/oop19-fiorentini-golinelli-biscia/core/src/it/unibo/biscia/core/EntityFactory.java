package it.unibo.biscia.core;

import it.unibo.biscia.core.Entity.EntityManaged;
import it.unibo.biscia.core.Entity.EntityManaged.Movable;

import java.util.List;
import java.util.Optional;

/**
 * factory for entity on level.
 *
 */
interface EntityFactory {
    /**
     * make a snake in start position and intial lenght.
     * 
     * @param runEver if true the snake allways run else snake move only after
     *                command
     * @return a Directable entity
     */
    Entity.EntityManaged.Movable.Directable makeBabySnake(boolean runEver);

    /**
     * make a snake with cells, energy and direction passed (for reprise after load
     * a saved game).
     * 
     * @param cells     cells of body
     * @param energy    energy initial
     * @param direction direction to move at start
     * @param runEver   if true the snake allways run else snake move only after
     *                  command
     * @return a Directable entity
     */
    Entity.EntityManaged.Movable.Directable makeAdultSnake(List<Cell> cells, int energy, Optional<Direction> direction,
            boolean runEver);

    /**
     * make a food in a casual position on level.
     * 
     * @param energy energy of food
     * @return a new food
     */
    Entity.EntityManaged.Eatable makeCasualFood(int energy);

    /**
     * make a wall around the level.
     * 
     * @return wall
     */
    EntityManaged makeEdge();

    /**
     * make a linear wall from position to length ondirection passed.
     * 
     * @param start     starting cell
     * @param length    length of wall
     * @param direction direction of wall
     * @return wall
     */
    EntityManaged makeLinearWall(Cell start, int length, Direction direction);

    /**
     * make a wall on cells passed.
     * 
     * @param cells list of cell to compose wall
     * @return wall
     */
    EntityManaged makeWall(List<Cell> cells);

    /**
     * make a mobile wall on cells passed.
     * 
     * @param cells            list of cell to compose wall
     * @param direction        direction to move
     * @param movementType     movement type
     * @param movementInterval step interval from a movmemnt and next
     * @return
     */
    Movable makeMovableWall(List<Cell> cells, Direction direction, MovementType movementType, int movementInterval);

}
