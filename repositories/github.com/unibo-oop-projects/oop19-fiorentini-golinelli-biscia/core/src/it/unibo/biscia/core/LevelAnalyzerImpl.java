package it.unibo.biscia.core;

import it.unibo.biscia.core.Entity.EntityManaged.Movable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

class LevelAnalyzerImpl implements it.unibo.biscia.core.LevelAnalyzer {
    private final Level.LevelManaged level;

    LevelAnalyzerImpl(final Level.LevelManaged level) {
        this.level = level;
    }

    @Override
    public List<Entity.EntityManaged> getIntersecatedEntity() {
        return this.entitiesOnCells(this.getIntersectionsCells()).stream().collect(Collectors.toList());
    }

    @Override
    public List<Cell> getIntersectionsCells() {
        // tutte le celle con più di una entità dentro più tutte le celle presenti più
        // volte in una entità
        final List<Cell> cells = new LinkedList<>(this.getOccupiedCells().stream()
                .filter(cell -> this.entitiesOnCell(cell).size() > 1).collect(Collectors.toList()));
        for (final var e : this.level.getEntities()) {
            if (e.getCells().size() != e.getCells().stream().distinct().count()) {
                for (final Cell c : e.getCells()) {
                    if (e.getCells().indexOf(c) != e.getCells().lastIndexOf(c)) {
                        cells.add(c);
                    }
                }
            }
        }
        return cells;
    }

    @Override
    public List<Entity.EntityManaged> entitiesOnCells(final List<Cell> cells) {
        return Collections.unmodifiableList(
                cells.stream().flatMap(c -> this.entitiesOnCell(c).stream()).distinct().collect(Collectors.toList()));
    }

    @Override
    public final List<Entity.EntityManaged> entitiesOnCell(final Cell cell) {
        return Collections.unmodifiableList(this.level.getEntities().stream()
                .filter(e -> e instanceof Entity.EntityManaged).map(e -> (Entity.EntityManaged) e)
                .filter(e -> e.getCells().contains(cell)).collect(Collectors.toList()));
    }

    @Override
    public final List<Cell> getOccupiedCells() {
        return Collections.unmodifiableList(this.level.getEntities().stream().flatMap(e -> e.getCells().stream())
                .distinct().collect(Collectors.toList()));
    }

    @Override
    public final List<Cell> getFreeCells() {
        final var occ = this.getOccupiedCells();
        return Collections.unmodifiableList(
                this.level.getCells().stream().filter(c -> !(occ.contains(c))).collect(Collectors.toList()));
    }

    @Override
    public final List<Entity.EntityManaged.Eatable> getEatables() {
        return this.level.getEntities().stream().filter(e -> e instanceof Entity.EntityManaged.Eatable)
                .map(e -> (Entity.EntityManaged.Eatable) e).collect(Collectors.toList());
    }

    @Override
    public List<Entity.EntityManaged.Eater> getEater() {
        return this.level.getEntities().stream().filter(e -> e instanceof Entity.EntityManaged.Eater)
                .map(e -> (Entity.EntityManaged.Eater) e).collect(Collectors.toList());
    }

    @Override
    public final List<Entity> getEntityOfType(final EntityType entityType) {

        return this.level.getEntities().stream().filter(e -> e.getType().equals(entityType))
                .collect(Collectors.toList());

    }

    private boolean isFreeNearest(final Cell cell) {
        final Set<Cell> occ = this.getOccupiedCells().stream().collect(Collectors.toSet());
        for (int c = -1; c < 2; c++) {
            for (int r = -1; r < 2; r++) {
                if (occ.contains(this.level.getCell(cell.getCol() + c, cell.getRow() + r))) {
                    return false;
                }

            }
        }
        return true;
    }

    @Override
    public final Cell getNewSnakePosition() {
        final Cell cell;
        if (this.getDirectables().isEmpty()) {
            cell = this.level.getCell(this.level.getCols() / 3 * 2, this.level.getRows() / 2);
        } else {
            cell = this.level.getCell(this.level.getCols() / 3, this.level.getRows() / 2);
        }
        if (!this.getOccupiedCells().contains(cell) && this.isFreeNearest(cell)) {
            return cell;
        }
        final List<Cell> cells = this.getFreeCells();
        List<Cell> cellsFree;
        cellsFree = cells.stream().filter(c -> this.isFreeNearest(c)).collect(Collectors.toList());
        if (cellsFree.isEmpty()) {
            cellsFree = cells.stream().filter(c -> cells.contains(this.level.getSideCell(c, Direction.LEFT)))
                    .filter(c -> cells.contains(this.level.getSideCell(c, Direction.RIGHT)))
                    .collect(Collectors.toList());
        }
        if (cellsFree.isEmpty()) {
            cellsFree = cells;
        }
        return cells.get(new Random().nextInt(cells.size()));
    }

    @Override
    public List<Movable> getMovables() {
        return this.level.getEntities().stream().filter(e -> e instanceof Entity.EntityManaged.Movable)
                .map(e -> (Entity.EntityManaged.Movable) e).collect(Collectors.toList());

    }

    @Override
    public List<Entity.EntityManaged.Movable.Directable> getDirectables() {
        return this.getMovables().stream().filter(e -> e instanceof Entity.EntityManaged.Movable.Directable)
                .map(e -> (Entity.EntityManaged.Movable.Directable) e).collect(Collectors.toList());
    }

    @Override
    public List<Cell> getFreeAreas(final int width, final int height) {
        return this.level.getCells().stream().filter(c -> this.isFreeArea(c, width, height))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cell> getFreeSquares(final int side) {
        return this.getFreeAreas(side, side);
    }

    @Override
    public boolean isFreeArea(final Cell cell, final int width, final int height) {
        if (!this.isFreeCell(cell)) {
            return false;
        }
        Cell c = cell;
        for (int i = 0; i < width; i++) {
            if (!this.isFreeSide(c, Direction.DOWN, height)) {
                return false;
            }
            c = this.level.getSideCell(c, Direction.RIGHT);
        }
        return true;
    }

    @Override
    public boolean isFreeSide(final Cell cell, final Direction direction, final int length) {

        if (direction.getStepCol() != 0 && length >= this.level.getCols()) {
            return false;
        }
        if (direction.getStepRow() != 0 && length >= this.level.getRows()) {
            return false;
        }

        if (!this.isFreeCell(cell)) {
            return false;
        }

        if (this.getOccupiedCells().contains(cell)) {
            return false;
        }
        if (length == 1) {
            return true;
        }
        return this.isFreeSide(this.level.getSideCell(cell, direction), direction, length - 1);
    }

    @Override
    public boolean isFreeCell(final Cell cell) {
        return this.level.getEntities().stream().flatMap(e -> e.getCells().stream()).filter(c -> c.equals(cell))
                .findAny().isEmpty();
    }

}
