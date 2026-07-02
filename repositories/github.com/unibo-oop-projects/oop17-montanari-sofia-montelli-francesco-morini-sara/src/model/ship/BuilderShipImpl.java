package model.ship;

import java.util.Optional;

import model.basic_component.StaticPoint2D;

/**
 * Builder Ship Class.
 *
 */
public final class BuilderShipImpl implements BuilderShip {
    /**
     * size of the ship to be built.
     */
    private Optional<Integer> size;
    /**
     * starting point of the ship to be built.
     */
    private Optional<StaticPoint2D> first;
    /**
     * end point of the ship to be built.
     */
    private Optional<StaticPoint2D> second;
    /**
     * Constructor.
     */
    public BuilderShipImpl() {
        reset();
    }

    @Override
    public void setSizeShip(final int size) throws IllegalArgumentException, IllegalStateException {
        checkAndThrow(second.isPresent() && second.get().getDistance(first.get()) != size - 1, new IllegalStateException());
        checkAndThrow(size > Ship.MAX_SHIP_SIZE || size <= 0, new IllegalArgumentException());
        this.size = Optional.of(size);
    }

    @Override
    public void setFirstCoord(final StaticPoint2D coord) throws IllegalStateException {
        checkAndThrow(second.isPresent(), new IllegalStateException());
        first = Optional.of(coord);
    }

    @Override
    public void setSecondCoord(final StaticPoint2D coord) throws IllegalArgumentException, IllegalStateException {
        checkAndThrow(!first.isPresent(), new IllegalStateException());
        checkAndThrow(!first.get().alligned(coord) || size.isPresent() && first.get().getDistance(coord) != size.get() - 1, new IllegalArgumentException());
        second = Optional.of(coord);
        if (!size.isPresent()) {
            size = Optional.of(first.get().getDistance(second.get()) + 1);
        }
    }

    @Override
    public Optional<Integer> getSize() {
        return size;
    }

    @Override
    public Optional<StaticPoint2D> getFirstCoord() {
        return first;
    }

    @Override
    public Optional<StaticPoint2D> getSecondCoord() {
        return second;
    }

    @Override
    public Ship build() throws IllegalStateException {
        checkAndThrow(!first.isPresent(), new IllegalStateException());
        Ship ship;
        if (second.isPresent()) {
            checkAndThrow(first.get().getDistance(second.get()) + 1 != size.get(), new IllegalStateException());
            ship = new ShipImpl(first.get(), second.get());
        } else {
            ship = new ShipImpl(first.get());
        }
        reset();
        return ship;
    }

    @Override
    public void reset() {
        size = Optional.empty();
        first = Optional.empty();
        second = Optional.empty();
    }

    private void checkAndThrow(final boolean filter, final RuntimeException exception) {
        if (filter) {
            throw exception;
        }
    }

    @Override
    public void resetCoord() {
        first = Optional.empty();
        second = Optional.empty();
    }

    @Override
    public void removeSecondCoord() {
        second = Optional.empty();
    }

    @Override
    public boolean canBuild() {
        return first.isPresent() && (!size.isPresent() || size.get() == 1)
               ||
               first.isPresent() && second.isPresent();
    }
}
