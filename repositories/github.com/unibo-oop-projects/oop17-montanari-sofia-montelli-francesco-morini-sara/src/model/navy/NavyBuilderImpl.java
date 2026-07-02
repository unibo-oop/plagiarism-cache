package model.navy;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.basic_component.Cell;
import model.basic_component.StaticPoint2D;
import model.ship.Ship;

/**
 * Temporary placeholder for the {@link NavyBuilder} interface implementation.
 */
public final  class NavyBuilderImpl implements NavyBuilder {

    /**
     * the navy to be built.
     */
    private final Set<Ship> navy;
    /**
     * the grid where insert the navy.
     */
    private final GridManage grid;
    /**
     * a boolean value that says if the navy had already build.
     */
    private boolean builded;
    /**
     * field for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(NavyBuilderImpl.class.getName());
    /**
     * 
     * @param condition to be verified
     * @param exception the exception to throw if the condition is not verified
     */
    private void checkAndThrow(final boolean condition, final RuntimeException exception) {
        if (condition) {
            throw exception;
        }
    }

    /**
     * @param list the {@link List} of {@link Integer} which represent the... 
     * @param side 
     * @throws IllegalArgumentException if the provided list can't fit in the grid.
     */
    public NavyBuilderImpl(final List<Integer> list, final int side) throws IllegalArgumentException {
        checkAndThrow(side <= 0, new IllegalArgumentException());
        checkAndThrow(!GridManage.checkComposition(side, list), new IllegalArgumentException());
        navy = new HashSet<>();
        builded = false;
        grid = new GridManageImpl(side, list);
        reset();
    }

    @Override
    public void addShip(final Ship ship) throws IllegalArgumentException, IllegalStateException {
        checkAndThrow(builded, new IllegalStateException());
        checkAndThrow(!shipRequired(ship), new IllegalArgumentException());
        checkAndThrow(!ship.getCellSet().stream().allMatch(cells -> grid.getSetFreeCell().contains(cells.getCoordinate())), new IllegalArgumentException());

        LOGGER.trace("Added a new ship {} to builder {}", ship, this);

        grid.placeShip(ship);
        navy.add(ship);
    }

    private boolean shipRequired(final Ship ship) {
        return grid.getRemaining().get(ship.getSize() - 1) > 0;
    }

    @Override
    public void removeShip(final Ship ship) throws IllegalStateException, IllegalArgumentException {
        checkAndThrow(builded, new IllegalStateException());
        checkAndThrow(!navy.contains(ship), new IllegalArgumentException());
        LOGGER.debug("Ship to remove: {}", ship.getCellSet());
        LOGGER.debug("Navy before removal {}", navy);
        navy.remove(ship);
        LOGGER.debug("Navy after removal {}", navy);
        LOGGER.debug("Grid's free cell before removal: {}",  grid.getSetFreeCell());
        grid.removeShip(ship);
        LOGGER.debug("Grid's free cells after removal: {}", grid.getSetFreeCell());
        LOGGER.trace("Removed ship {} from builder {}", ship, this);
    }

    @Override
    public Set<StaticPoint2D> getAvailablePositions() {
        if (builded) {
            return new HashSet<>();
        } else {
            return grid.getSetFreeCell();
        }
    }
    /**
     * Check if the provided point is outside the grid.
     * @param point the point provided for the check
     */
    private void checkPointOutsideGrid(final StaticPoint2D point) {
        checkAndThrow(point.getX() >= grid.getSide(), new IllegalArgumentException());
        checkAndThrow(point.getY() >= grid.getSide(), new IllegalArgumentException());
    }

    @Override
    public Set<StaticPoint2D> getAvailablePositionsSecondCord(final StaticPoint2D firstPoint, final int size) throws IllegalArgumentException {
        checkPointOutsideGrid(firstPoint);
        checkAndThrow(size <= 0, new IllegalArgumentException());
        return grid.getPossiblePositionSecondCoord(firstPoint, size);
    }

    @Override
    public Set<StaticPoint2D> getAvailablePositionsSecondCord(final StaticPoint2D firstPoint) {
        checkPointOutsideGrid(firstPoint);
        return grid.getPossiblePositionSecondCoord(firstPoint);
    }

    @Override
    public Navy buildNavy() throws IllegalStateException {
        checkAndThrow(!canBuild(), new IllegalStateException());
        builded = true;
        return new NavyImpl(navy);
    }

    @Override
    public boolean canBuild() {
        return !builded && grid.getRemaining().stream().allMatch(i -> i == 0);
    }

    @Override
    public void reset() {
        grid.reset();
        navy.clear();
        builded = false;
    }

    @Override
    public List<Integer> getMissingShips() {
        return Collections.unmodifiableList(grid.getRemaining());
    }

    @Override
    public Set<StaticPoint2D> getAllOccupiedPosition() {
        return navy.stream().map(s -> s.getCellSet().stream()).flatMap(e -> e).map(Cell::getCoordinate).collect(Collectors.toSet());
    }

    @Override
    public int getGridSize() {
        return grid.getSide();
    }

    @Override
    public Set<StaticPoint2D> getAvaiablePositions(final int size) {
        return grid.getPositionWithSize(size);
    }
}
