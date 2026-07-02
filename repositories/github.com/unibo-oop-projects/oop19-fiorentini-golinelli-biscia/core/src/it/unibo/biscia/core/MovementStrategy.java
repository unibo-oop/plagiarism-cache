package it.unibo.biscia.core;

import java.util.List;

@FunctionalInterface
interface MovementStrategy {

    /**
     * perform a movement on list of cells.
     * 
     * @param level     space available for movement
     * @param cells     list of cells to move
     * @param direction direction of movement
     * @return the new list of cells moved
     */
    List<Cell> move(Level level, List<Cell> cells, Direction direction);
}
