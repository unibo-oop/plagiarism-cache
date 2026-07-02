package model.navy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.basic_component.StaticPoint2D;
import model.basic_component.StaticPoint2DImpl;
import model.ship.Ship;

/**
 * Basic implementation of a {@link GridManage}.
 */
class GridManageImpl implements GridManage {
    /**
     * field for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(GridManageImpl.class.getName());
    /**
     * The internal representation of the grid, each point 
     * is associated with information about it's status.
     */
    private final Map<StaticPoint2D, Status> grid = new HashMap<>();
    /**
     * Information about the side of the grid.
     */
    private final int side;
    /**
     * The required composition of the Navy.
     */
    private List<Integer> initialComp = new ArrayList<>();
    /**
     * The remaining ship to add.
     */
    private final List<Integer> remainingComp = new ArrayList<>();
    /**
     * @param side it is the size of one side of the square grid side*side
     * @param composition list of the fleet composition 
     * @throws IllegalArgumentException if side is lower than 1
     */
    protected GridManageImpl(final int side, final List<Integer> composition) throws IllegalArgumentException {
        if (GridManage.checkComposition(side, composition)) {
            if (side <= 0) {
                throw new IllegalArgumentException();
            } else {
                LOGGER.trace("Create a new grid manage where the composition is correct");
                this.side = side;
                remainingComp.addAll(composition);
                initialComp = Collections.unmodifiableList(composition);
                reset();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getSide() {
        return side;
    }

    @Override
    public List<Integer> getRemaining() {
        return remainingComp;
    }

    @Override
    public List<Integer> getInitial() {
        return initialComp;
    }
    /**
     * 
     * @return a set of {@link StaticPoint2D} of the cell that are {@link Status} FREE
     */
    private Set<StaticPoint2D> getFreeCell() {
        return grid.entrySet()
                .stream()
                .filter(p -> p.getValue().equals(Status.FREE))
                .map(p -> p.getKey())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<StaticPoint2D> getSetFreeCell() {
        return getFreeCell().stream()
                            .filter(p -> !getPossiblePositionSecondCoord(p).isEmpty())
                            .collect(Collectors.toSet());
    }

    @Override
    public Set<StaticPoint2D> getUnavailableCell() {
        final Set<StaticPoint2D> set = grid.entrySet()
                                     .stream()
                                     .map(p -> p.getKey())
                                     .collect(Collectors.toSet());
        set.removeAll(getSetFreeCell());
        return set;
    }

    @Override
    public Set<StaticPoint2D> getPositionWithSize(final int size) {
        return getFreeCell().stream()
                            .filter(p -> !getPossiblePositionSecondCoord(p, size).isEmpty())
                            .collect(Collectors.toSet());
    }
    /**
     * Given the first point checks if a ship of the given size can be placed.
     * @param firstPoint is the initial point of the ship
     * @param xMod is the modifier to apply on the x coordinate in order to move
     * @param yMod is the modifier to apply on the y coordinate in order to move 
     * @param size dimension of the ship to place
     * @return the result of the described check
     */
    private boolean direction(final StaticPoint2D firstPoint, final int xMod, final int yMod, final int size) {
        return Stream.iterate(new StaticPoint2DImpl(firstPoint.getX(), firstPoint.getY()), 
                              p -> new StaticPoint2DImpl(p.getX() + xMod, p.getY() + yMod))
                     .limit(size)
                     .allMatch(p -> grid.getOrDefault(p, Status.BOUND).equals(Status.FREE));
    }

    @Override
    public Set<StaticPoint2D> getPossiblePositionSecondCoord(final StaticPoint2D first, final int size) throws IllegalArgumentException {
        if (size <= 0 || size > Ship.MAX_SHIP_SIZE || !grid.containsKey(first)) {
            throw new IllegalArgumentException();
        }
        final Set<StaticPoint2D> set = new HashSet<>();
        /**
         * Dx  --> x increments.
         */
        if (direction(first, 1, 0, size)) {
           set.add(new StaticPoint2DImpl(first.getX() + size - 1, first.getY()));
        }
        /**
         * Sx --> x decrease.
         */
        if (direction(first, -1, 0, size)) {
            set.add(new StaticPoint2DImpl(first.getX() - size + 1, first.getY()));
        }
        /**
         * Up --> y decrease.
         */
        if (direction(first, 0, -1, size)) {
            set.add(new StaticPoint2DImpl(first.getX(), first.getY() - size + 1));
        }
        /**
         * Down --> y increase.
         */
        if (direction(first, 0, 1, size)) {
            set.add(new StaticPoint2DImpl(first.getX(), first.getY() + size - 1));
        }

        return Collections.unmodifiableSet(set);
    }

    @Override
    public Set<StaticPoint2D> getPossiblePositionSecondCoord(final StaticPoint2D first) {
        final Set<StaticPoint2D> set = new HashSet<>();
        for (int i = 0; i < Ship.MAX_SHIP_SIZE; i++) {
            if (remainingComp.get(i) > 0) {
                set.addAll(getPossiblePositionSecondCoord(first, i + 1));
            }
        }
        return set;
    }

    private void modifyRemainingComp(final Ship ship, final int mod) {
        remainingComp.set(ship.getSize() - 1, remainingComp.get(ship.getSize() - 1) + mod);
    }

    @Override
    public void placeShip(final Ship ship) throws IllegalArgumentException {
        if (!getSetFreeCell().containsAll(ship.getCellSet().stream()
                             .map(c -> c.getCoordinate())
                             .collect(Collectors.toSet()))) {
            LOGGER.error("Illegal Argument");
            throw new IllegalArgumentException();
        }
        LOGGER.trace("Placemente of a Ship");
        modifyForPlacementeOrRemove(ship, Status.FREE, Status.SHIP, Status.FREE, Status.BOUND);
        modifyRemainingComp(ship, -1);
        //remainingComp.set(ship.getSize() - 1, remainingComp.get(ship.getSize() - 1) - 1);
    } 

    @Override
    public void removeShip(final Ship ship) throws IllegalArgumentException {
        if (ship.getCellSet()
                .stream()
                .map(c -> c.getCoordinate())
                .allMatch(p -> grid.containsKey(p) && grid.get(p).equals(Status.SHIP))) {
            LOGGER.trace("Removal of a ship");
            modifyForPlacementeOrRemove(ship, Status.SHIP, Status.FREE, Status.BOUND, Status.FREE);
            modifyRemainingComp(ship, 1);
            //remainingComp.set(ship.getSize() - 1, remainingComp.get(ship.getSize() - 1) + 1);
        } else {
            LOGGER.error("Illegal Argument");
            throw new IllegalArgumentException();
        }
    }

    @Override
    public final void reset() {
        LOGGER.trace("Reset of the grid");
        Stream.iterate(new StaticPoint2DImpl(0, 0), p -> new StaticPoint2DImpl(p.getY() < side - 1 ? p.getX() : p.getX() + 1,
                                                                                   p.getY() < side - 1 ? p.getY() + 1 : 0))
              .limit(side * side)
              .forEach(p -> grid.put(p, Status.FREE));
        remainingComp.clear();
        remainingComp.addAll(initialComp);
    }

    /**
     * Update the {@link Ship} status and it's border.
     * @param ship
     * @param oldStatus of the ship 
     * @param newStatus of the ship 
     * @param oldBorder status of the border
     * @param newBorder status of the border
     */
    private void modifyForPlacementeOrRemove(final Ship ship, final Status oldStatus, final Status newStatus, final Status oldBorder, final Status newBorder) {
        ship.getCellSet().stream()
                         .map(c -> c.getCoordinate())
                         .forEach(p -> {
                             grid.replace(p, oldStatus, newStatus);
                             putBorder(p, oldBorder, newBorder);
                         });
    }
    /**
     * 
     * @param c the point to border
     * @param oldBorder
     * @param newBorder
     */
    private void putBorder(final StaticPoint2D cell, final Status oldBorder, final Status newBorder) {
        Stream.iterate(new StaticPoint2DImpl(cell.getX() - 1, cell.getY() - 1),
                       p -> new StaticPoint2DImpl(p.getY() < cell.getY() + 1 ? p.getX() : p.getX() + 1,
                                                      p.getY() < cell.getY() + 1 ? p.getY() + 1 : cell.getY() - 1))
              .limit(3 * 3)
              .forEach(point -> grid.replace(point, oldBorder, newBorder));
    }
}
