package model.navy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.EqualsAndHashCode;
import model.basic_component.StaticPoint2D;
import model.basic_component.StaticPoint2DImpl;
import model.ship.Ship;

/**
 * Basic implementation of the {@link Navy} interface.
 */
@EqualsAndHashCode
public final class NavyImpl implements Navy {

    /**
     * A {@link Navy} is a {@link Set} of {@link Ship}.
     */
    private Set<Ship> ships = new HashSet<Ship>();

    /**
     * @param navy is the {@link Navy} from which to copy the {@link Ship}
     */
    protected NavyImpl(final Navy navy) {
        this.setShips(new HashSet<>(navy.getShips()));
    }

    /**
     * @param ships is the {@link Set} of {@link Ship} for the {@link Navy}
     */
    protected NavyImpl(final Set<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public Set<Ship> getShips() {
        return ships;
    }

    @Override
    public boolean interact(final int coordX, final int coordY) throws IllegalStateException {
        return ships.stream().anyMatch(ship -> ship.interact(new StaticPoint2DImpl(coordX, coordY)));
    }

    @Override
    public boolean interact(final StaticPoint2D point) throws IllegalStateException {
        return interact(point.getX(), point.getY());
    }

    @Override
    public Navy getNavyCopy() {
        return new NavyImpl(getShips());
    }

    private void setShips(final Set<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public List<Integer> getNavySpecification() {
        final List<Integer> specification = Arrays.asList(0, 0, 0, 0);
        ships.forEach(ship -> specification.set(ship.getSize() - 1, specification.get(ship.getSize() - 1) + 1));
        return specification;
    }
}
