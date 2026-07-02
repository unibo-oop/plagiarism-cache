package model.ship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.EqualsAndHashCode;
import model.basic_component.Cell;
import model.basic_component.CellImpl;
import model.basic_component.StaticPoint2D;

/**
 * Basic implementation of the {@link Ship} interface.
 */
@EqualsAndHashCode
public final class ShipImpl implements Ship {
    /**
     * A {@link Ship} is a composition of {@link Cell}.
     */
    private final Set<Cell> cells = new HashSet<>();
    /**
     * A {@link Ship} has to know it's {@link Ship.Orientation}.
     */
    private final Orientation orientation;
    /**
     * field for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(ShipImpl.class.getName());

    private void checkAndThrow(final boolean condition, final RuntimeException exception) {
        if (condition) {
            throw exception;
        }
    }
    /**
     * The constructor for a {@link Ship} of size greater than 1.
     * @param begin is the {@link StaticPoint2D} from which we have
     *        to start build the {@link Ship}
     * @param end is the last {@link StaticPoint2D} of the ship
     * @throws IllegalArgumentException if the two point are misaligned
     */
    protected ShipImpl(final StaticPoint2D begin, final StaticPoint2D end) throws IllegalArgumentException {
        checkAndThrow(!begin.alligned(end), new IllegalArgumentException());
        checkAndThrow(begin.getDistance(end) >= MAX_SHIP_SIZE, new IllegalArgumentException());
        orientation = begin.getX() == end.getX() ? Orientation.VERTICAL : Orientation.HORIZZONTAL;
        LOGGER.debug("Ship from {} to {} is {}", begin, end, orientation);
        switch (orientation) {
        case HORIZZONTAL:
            IntStream.rangeClosed(0, Math.abs(begin.getX() - end.getX())).forEach(elem -> {
                cells.add(new CellImpl(Math.min(begin.getX(), end.getX()) + elem, begin.getY(), Cell.Status.OCCUPIED));
            });
            break;
        case VERTICAL:
            IntStream.rangeClosed(0, Math.abs(begin.getY() - end.getY())).forEach(elem -> {
                cells.add(new CellImpl(begin.getX(), Math.min(begin.getY(), end.getY()) + elem, Cell.Status.OCCUPIED));
            });
            break;
        default:
            break;
        }
    }

    /**
     * Constructor for a {@link Ship} of size 1.
     * @param point the only {@link StaticPoint2D} of the {@link Ship}
     */
    protected ShipImpl(final StaticPoint2D point) {
        orientation = Orientation.INDEFINABLE;
        cells.add(new CellImpl(point.getX(), point.getY(), Cell.Status.OCCUPIED));
    }

    @Override
    public int getSize() {
        return cells.size();
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public Set<Cell> getCellSet() {
        return Collections.unmodifiableSet(cells);
    }

    @Override
    public Status getStatus() {
        LOGGER.debug("Ship's cell status: {}", getCellSet().stream().map(Cell::getStatus).collect(Collectors.toSet()));
        return getCellSet().stream().allMatch(cell -> cell.getStatus().equals(Cell.Status.OCCUPIED_AND_TARGETED)) ? Status.SUNK 
               :
               getCellSet().stream().anyMatch(cell -> cell.getStatus().equals(Cell.Status.OCCUPIED_AND_TARGETED)) ? Status.HIT : Status.ALIVE;
    }

    @Override
    public boolean adjacent(final StaticPoint2D point) {
        return  getCellSet().stream()
                            .map(Cell::getCoordinate) //for every cell i get the coordinate
                            .anyMatch(cell -> cell.getDistance(point) <= 1); //a point is adjacent to the ship if his distance from a cell of the ship is <= 1
    }

    @Override
    public boolean adjacent(final Ship ship) {
        return ship.getCellSet().stream()
                                .map(Cell::getCoordinate)
                                .anyMatch(cell -> this.adjacent(cell));
    }
    @Override
    public boolean interact(final StaticPoint2D point) {
        final Optional<Cell> cell = cells.stream().filter(s_cell -> s_cell.getCoordinate().equals(point)).findFirst();
        LOGGER.debug("Ship: {}", this);
        LOGGER.debug("Cell: {}", cell);
        if (cell.isPresent()) {
            LOGGER.debug("Cell is present");
            cells.stream().filter(s_cell -> s_cell.equals(cell.get())).forEach(c -> {
                checkAndThrow(c.getStatus().equals(Cell.Status.TARGETED), new IllegalStateException());
                checkAndThrow(c.getStatus().equals(Cell.Status.OCCUPIED_AND_TARGETED), new IllegalStateException());
                LOGGER.debug("Cell before interaction: {}", c);
                c.interact();
                LOGGER.debug("Cell after interaction: {}", c);
                LOGGER.debug("Ship after interaction: {}", this);
            });
            return true;
        } else {
            LOGGER.debug("Cell is not present in the ship");
            // the shot didn't occoured in the ship
            return false;
        }

    }

    @Override
    public String toString() {
        return "ShipImpl [cells=" + cells + "]";
    }
}
