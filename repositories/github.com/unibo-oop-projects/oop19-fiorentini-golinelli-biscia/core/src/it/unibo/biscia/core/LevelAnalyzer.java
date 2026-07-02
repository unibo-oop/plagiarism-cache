package it.unibo.biscia.core;

import java.util.List;

/**
 * read various information from level.
 *
 */
interface LevelAnalyzer {

    List<Cell> getIntersectionsCells();

    List<Entity.EntityManaged> getIntersecatedEntity();

    List<Entity.EntityManaged.Eatable> getEatables();

    List<Entity.EntityManaged.Eater> getEater();

    List<Entity.EntityManaged.Movable> getMovables();

    List<Entity.EntityManaged.Movable.Directable> getDirectables();

    List<Entity> getEntityOfType(EntityType entityType);

    List<Cell> getOccupiedCells();

    List<Entity.EntityManaged> entitiesOnCell(Cell cell);

    List<Entity.EntityManaged> entitiesOnCells(List<Cell> cells);

    List<Cell> getFreeCells();

    List<Cell> getFreeAreas(int width, int height);

    List<Cell> getFreeSquares(int side);

    boolean isFreeCell(Cell cell);

    boolean isFreeArea(Cell cell, int width, int height);

    boolean isFreeSide(Cell cell, Direction direction, int length);

    Cell getNewSnakePosition();
}
