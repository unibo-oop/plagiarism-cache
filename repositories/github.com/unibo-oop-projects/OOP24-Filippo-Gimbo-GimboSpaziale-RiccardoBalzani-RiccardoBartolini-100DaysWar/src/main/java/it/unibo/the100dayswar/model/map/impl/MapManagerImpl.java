package it.unibo.the100dayswar.model.map.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.awt.Dimension;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.BonusCell;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.api.GameMapBuilder;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * the implementation of the MapManager.
 */
public class MapManagerImpl implements MapManager {
    private static final long serialVersionUID = 1L;

    private final GameMapBuilder builder;
    private final GameMap map;
    private static final Logger LOGGER = Logger.getLogger(MapManagerImpl.class.getName());
    private final Map<Player, Set<Cell>> playersCells;
    private final List<Observer<Pair<Player, Unit>>> observers = new ArrayList<>();

    /**
     * the builder of the map.
     * @param builder
     */
    public MapManagerImpl(final GameMapBuilder builder) {
        this.builder = builder;
        map = createMap();
        playersCells = new HashMap<>();
    }

    /**
     * the builder of the mapManager with all objects.
     * @param original is the original mapManager.
     */
    public MapManagerImpl(final MapManager original) {
        this.builder = new GameMapBuilderImpl(0, 0);
        final int height = original.getMapDimension().height;
        final int width = original.getMapDimension().width;

        this.map = new GameMapImpl(width, height, MapManager.createMapFromStream(width, height, original.getMapAsAStream()));

        this.playersCells = new HashMap<>(original.getPlayersCells());
    }

    /**
     * the builder of the map with all objects.
     * @return the map
    */
    private GameMap createMap() {
        return builder
                .initializeBuildableCells()
                .addSpawnCells()
                .addBonusCell(8)
                .addObstacles(10)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Pair<Unit, Cell> source) {
            if (isSoldierWantsToMove(source)) {
                soldierMovement(source);
            } else if (isNewSoldier(source)) {
                createSoldier(source);
            } else if (isTower(source)) {
                createTower(source);
            } else {
                updateUnitStatus(source);
            }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Player, Set<Cell>> getPlayersCells() {
        return new HashMap<>(playersCells); 
    }

    /**
     * add the cell to the player.
     * @param player is the player.
     * @param targetCell is the cell.
     */
    private void addCell(final Player player, final Cell targetCell) {
        final Set<Cell> cells = playersCells.computeIfAbsent(player, p -> new HashSet<>());
        cells.add(targetCell);
    }

    /**
     * remove the cell from the player.
     * @param player is the player.
     * @param targetCell is the cell.
     */
    private void removeCell(final Player player, final Cell targetCell) {
        final Set<Cell> cells = playersCells.get(player);
        if (cells != null && cells.contains(targetCell)) {
            cells.remove(targetCell);
        }
    }

    /**
     * create a new soldier.
     * @param source is the pair of the soldier and the cell.
     */
    private void createSoldier(final Pair<Unit, Cell> source) {
        final Soldier soldier = (Soldier) source.getFirst();
        soldier.setJustCreated();
        final Cell targetCell = this.map.getCell(source.getSecond().getPosition());

        if (!targetCell.isSpawn()) {
            LOGGER.log(Level.WARNING, "Target cell is not a spawn cell for soldier creation: {0}", targetCell.getPosition());
            throw new IllegalStateException("Target cell is not a spawn cell for soldier creation.");
        }
        if (targetCell.getUnit().isPresent()) {
            LOGGER.log(Level.WARNING, "Spawn cell is occupied. Move the existing soldier: {0}", targetCell.getPosition());
            throw new IllegalStateException("Spawn cell is occupied. Move the existing soldier.");
        }
        map.setOccupationOnCell(targetCell, Optional.of(soldier));
        addCell(soldier.getOwner(), targetCell);
    }

    /**
     * create a new tower.
     * @param source is the pair of the tower and the cell.
     */
    private void createTower(final Pair<Unit, Cell> source) {
        final Tower tower = (Tower) source.getFirst();
        final Cell targetCell = this.map.getCell(source.getSecond().getPosition());
        if (!targetCell.isBuildable()) {
            LOGGER.log(Level.WARNING, "Target cell is not buildable for tower placement: {0}", targetCell.getPosition());
            throw new IllegalStateException("Target cell is not buildable for tower placement.");
        }
        if (targetCell.isFree()) {
            map.setOccupationOnCell(targetCell, Optional.of(tower));
            new HashMap<>(playersCells).forEach((p, s) -> {
                if (p.equals(tower.getOwner())) {
                    addCell(p, targetCell);
                } else if (s.contains(targetCell)) {
                    removeCell(p, targetCell);
                }
            });
        } else {
            LOGGER.log(Level.WARNING, "Target cell is not free for tower placement: {0}", targetCell.getPosition());
            throw new IllegalStateException("Target cell is not free for tower placement.");
        }
    }

    /**
     * Move the soldier.
     * @param source is the pair of the soldier and the cell.
     */
    private void soldierMovement(final Pair<Unit, Cell> source) {
        final Soldier soldier = (Soldier) source.getFirst();
        final Cell targetCell = this.map.getCell(source.getSecond().getPosition());
        final Cell currentCell = this.map.getCell(soldier.getPosition().getPosition());
        if (!targetCell.isFree()) {
            LOGGER.log(Level.WARNING, "Target cell is not free for soldier movement: {0}", targetCell.getPosition());
            throw new IllegalStateException("Target cell is not free for soldier movement.");
        }
        if (currentCell.isAdiacent(targetCell)) {
            soldier.move(targetCell);
            map.setOccupationOnCell(currentCell, Optional.empty());
            map.setOccupationOnCell(targetCell, Optional.of(soldier));
            new HashMap<>(playersCells).forEach((p, s) -> {
                if (p.equals(soldier.getOwner())) {
                    addCell(p, targetCell);
                } else if (s.contains(targetCell)) {
                    removeCell(p, targetCell);
                }
            });
            if (targetCell instanceof BonusCell) {
                ((BonusCell) targetCell).notify(soldier.getOwner());
            }
        }
    }

    /**
     * update the unit status.
     * @param source is the pair of the unit and the cell.
     */
    private void updateUnitStatus(final Pair<Unit, Cell> source) {
        final Unit unit = source.getFirst();
        final Cell currentCell = this.map.getCell(source.getSecond().getPosition());
        if (unit.currentHealth() <= 0) {
            map.setOccupationOnCell(currentCell, Optional.empty());
            notifyObservers(new Pair<>(unit.getOwner(), unit));
        }
    }

    /**
     * check if the soldier is new.
     * @param source is the pair of the soldier and the cell.
     * @return true if the soldier is new.
     */
    private boolean isNewSoldier(final Pair<Unit, Cell> source) {
        return source.getFirst() instanceof Soldier
        && source.getSecond().equals(((Soldier) source.getFirst()).getPosition())
        && ((Soldier) source.getFirst()).getOwner().getSpawnPoint().equals(source.getSecond())
        && ((Soldier) source.getFirst()).isJustCreated();
    }

    /**
     * check if the unit is a tower.
     * @param source is the pair of the unit and the cell.
     * @return true if the unit is a tower.
     */
    private boolean isTower(final Pair<Unit, Cell> source) {
        return source.getFirst() instanceof Tower
            && map.getCell(source.getSecond().getPosition()).isFree()
            && source.getFirst().getPosition().equals(source.getSecond());
    }

    /**
     * check if the soldier wants to move.
     * @param source is the pair of the soldier and the cell.
     * @return true if the soldier wants to move.
     */
    private boolean isSoldierWantsToMove(final Pair<Unit, Cell> source) {
        return source.getFirst() instanceof Soldier
        && !source.getSecond().equals(((Soldier) source.getFirst()).getPosition());
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Cell getBotSpawn() {
        return map.getAllCells()
            .filter(Cell::isSpawn)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No spawn cells found in the map."));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Cell getPlayerSpawn() {
        return map.getAllCells()
            .filter(Cell::isSpawn)
            .filter(c -> !c.equals(getBotSpawn()))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No spawn cells found in the map."));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Stream<Cell> getMapAsAStream() {
        return map.getAllCells();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Dimension getMapDimension() {
        return new Dimension(map.getSize().width, map.getSize().height).getSize();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void attach(final Observer<Pair<Player, Unit>> observer) {
        observers.add(observer);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void detach(final Observer<Pair<Player, Unit>> observer) {
        observers.remove(observer);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers(final Pair<Player, Unit> source) {
        observers.forEach(o -> o.update(source));
    }
}
