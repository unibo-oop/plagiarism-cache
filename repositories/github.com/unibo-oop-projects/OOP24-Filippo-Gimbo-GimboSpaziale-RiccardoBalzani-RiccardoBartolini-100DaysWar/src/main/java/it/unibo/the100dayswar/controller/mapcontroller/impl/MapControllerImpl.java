package it.unibo.the100dayswar.controller.mapcontroller.impl;

import it.unibo.the100dayswar.view.map.CellView;
import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.controller.mapcontroller.api.MapController;
import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.tower.api.AdvancedTower;
import it.unibo.the100dayswar.model.tower.api.BasicTower;
import it.unibo.the100dayswar.model.unit.api.Unit;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of the MapController interface.
 */
public class MapControllerImpl implements MapController {

    private static final String SOLDIER_IMAGE_PATH = "/units/soldier/";
    private static final String TOWER_IMAGE_PATH = "/units/towers/";
    private static final String DEFAULT_FORMAT = ".png";
    private static final String OBSTACLE_PATH = "/map/obstacle.png";

    private Pair<Unit, Cell> selectedCell;
    /**
     * Constructor for the MapControllerImpl.
     */
    public MapControllerImpl() {
        this.selectedCell = new Pair<>(null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMapWidth() {
        return (int) The100DaysWar.CONTROLLER.getGameInstance().getMap().getSize().getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMapHeight() {
        return (int) The100DaysWar.CONTROLLER.getGameInstance().getMap().getSize().getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CellView> getCellsView() {
        final List<CellView> cellDataList = new ArrayList<>();
        The100DaysWar.CONTROLLER.getGameInstance().getMap().getAllCells().forEach(cell -> {
            String imagePath = "";

            if (cell.isSpawn()) {
                imagePath = "/map/spawn.png";
            }

            if (cell.getUnit().isPresent()) {
                final Unit unit = cell.getUnit().get();
                if (unit instanceof Soldier) {
                    if (unit.getOwner() instanceof HumanPlayer) {
                        imagePath = SOLDIER_IMAGE_PATH + "soldier" + unit.getLevel() + DEFAULT_FORMAT;
                    } else if (unit.getOwner() instanceof BotPlayer) {
                        imagePath = SOLDIER_IMAGE_PATH + "enemy" + unit.getLevel() + DEFAULT_FORMAT;
                    }
                } else if (unit instanceof BasicTower) {
                    if (unit.getOwner() instanceof HumanPlayer) {
                        imagePath = TOWER_IMAGE_PATH + "human_basic" + unit.getLevel() + DEFAULT_FORMAT;
                    } else if (unit.getOwner() instanceof BotPlayer) {
                        imagePath = TOWER_IMAGE_PATH + "enemy_basic" + unit.getLevel() + DEFAULT_FORMAT;
                    }
                } else if (unit instanceof AdvancedTower) {
                    if (unit.getOwner() instanceof HumanPlayer) {
                        imagePath = TOWER_IMAGE_PATH + "human_advanced" + unit.getLevel() + DEFAULT_FORMAT;
                    } else if (unit.getOwner() instanceof BotPlayer) {
                        imagePath = TOWER_IMAGE_PATH + "enemy_advanced" + unit.getLevel() + DEFAULT_FORMAT;
                    }
                }
            } else if (!cell.isBuildable()) {
                imagePath = OBSTACLE_PATH;
            }
            cellDataList.add(new CellView(cell.getPosition(), imagePath));
        });
        return cellDataList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMap getMap() {
        return The100DaysWar.CONTROLLER.getGameInstance().getMap();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCellClick(final int cellX, final int cellY) {
         final GameMap map = The100DaysWar.CONTROLLER.getGameInstance().getMap();
         final Cell clickedCell = map.getCell(new PositionImpl(cellX, cellY));
         selectedCell = new Pair<>(clickedCell.getUnit().orElse(null), clickedCell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Unit, Cell> getSelectedCell() {
        final var copySelectedCell = new Pair<>(selectedCell.getFirst(), selectedCell.getSecond());
        this.selectedCell = new Pair<>(null, null);
        return copySelectedCell;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Cell> getAdjacentCells(final Cell cell) {
        final List<Cell> adjacentCells = new ArrayList<>();
        final Position position = cell.getPosition();
        final int x = position.getX();
        final int y = position.getY();

        final List<Position> potentialPositions = List.of(
            new PositionImpl(x - 1, y),
            new PositionImpl(x + 1, y),
            new PositionImpl(x, y - 1),
            new PositionImpl(x, y + 1) 
        );
        for (final Position pos : potentialPositions) {
            if (isWithinBounds(pos)) {
                adjacentCells.add(getCellAt(pos));
            }
        }
        return adjacentCells;
    }

    /**
     * Checks if the specified position is within the bounds of the map.
     *
     * @param position the position to check
     * @return true if the position is within the bounds, false otherwise
     */
    private boolean isWithinBounds(final Position position) {
        return position.getX() >= 0 && position.getX() < getMapWidth()
            && position.getY() >= 0 && position.getY() < getMapHeight();
    }

    /**
     * Returns the cell at the specified position.
     *
     * @param position the position of the cell
     * @return the cell at the specified position
     */
    private Cell getCellAt(final Position position) {
        return getMap().getCell(position);
    }
}
