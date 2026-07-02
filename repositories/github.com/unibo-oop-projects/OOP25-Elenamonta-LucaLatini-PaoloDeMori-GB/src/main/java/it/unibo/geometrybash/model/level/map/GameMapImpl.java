package it.unibo.geometrybash.model.level.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.geometrybash.model.level.Coordinate;
import it.unibo.geometrybash.model.level.map.exceptions.GameMapOperationException;

/**
 * Implementation of {@link GameMap} using a nested map structure.
 */
public final class GameMapImpl implements GameMap {

    private final Map<Integer, Map<Integer, Cell>> gameMap = new HashMap<>();

    /**
     * Creates new map with the given initial cells.
     *
     * @param initialCell a map of coordinates (the key of the map) to cells.
     */
    public GameMapImpl(final Map<Coordinate, Cell> initialCell) {
        if (initialCell != null) {
            initialCell.forEach((coordinate, cell) -> this.gameMap.computeIfAbsent(coordinate.x(), k -> new HashMap<>())
                    .put(coordinate.y(), cell));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cell getPresentCell(final Coordinate c) throws GameMapOperationException {
        final Map<Integer, Cell> column = this.gameMap.get(c.x());
        if (column == null) {
            throw new GameMapOperationException();
        }
        final Cell cell = column.get(c.y());
        if (cell == null) {
            throw new GameMapOperationException();
        }

        return cell;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Cell> getCellInRange(final int startX, final int endX) {
        final List<Cell> cellInRange = new ArrayList<>();

        final int minX = Math.min(startX, endX);
        final int maxX = Math.max(startX, endX);

        for (int x = minX; x <= maxX; x++) {
            final Map<Integer, Cell> column = this.gameMap.get(x);
            if (column != null && !column.entrySet().isEmpty()) {
                cellInRange.addAll(column.values());
            }
        }
        return cellInRange;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCoordinateValid(final Coordinate c) {
        final Map<Integer, Cell> column = this.gameMap.get(c.x());
        return column != null && column.containsKey(c.y());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Cell> getCell(final Coordinate c) {
        final Map<Integer, Cell> column = this.gameMap.get(c.x());
        if (column == null) {
            return Optional.empty();
        }
        final Cell cell = column.get(c.y());
        if (cell == null) {
            return Optional.empty();
        }

        return Optional.of(cell);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Cell> getAllCells() {
        return this.gameMap.values().stream()
        .flatMap(column -> column.values().stream())
        .toList();
    }

}
