package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.car.Car;
import model.car.CarImpl;
import model.circuit.Circuit;
import model.circuit.CircuitImpl;
import model.crash.CrashManager;
import model.crash.CrashManagerImpl;
import model.exception.BlockException;
import model.exception.CanBoxException;
import model.exception.CrashException;
import model.exception.EndRaceException;
import model.ranking.RankingManager;
import model.ranking.RankingManagerImpl;
import utility.Direction;
import utility.Driver;
import utility.Pair;
import utility.Position;
import utility.TyreType;

/**
 *Race Direction interface implementation.
 */
public class RaceDirectionImpl implements RaceDirection {
    /**
     * position at the end of the race.
     */
    public static final Position END_POS = new PositionImpl(-1, -1, -1);
    /**
     * position at the box.
     */
    public static final Position BOX_POS = new PositionImpl(-1, -1, 0);
    private final Map<Driver, Car> map;
    private final Circuit circuit;
    private RankingManager rank;
    private final CrashManager crashManager;
    /**
     * Constructor.
     * @param file the circuit's file
     * @throws FileNotFoundException 
     */
    public RaceDirectionImpl(final BufferedReader file) throws FileNotFoundException {
        this.map = new HashMap<>();
        Stream.of(Driver.values()).forEach(x->this.map.put(x, new CarImpl(x)));
        this.crashManager = new CrashManagerImpl(this.map.values().stream().collect(Collectors.toList()));
        this.circuit = new CircuitImpl(file);
        }

    @Override
    public Map<Driver, Position> setDriverForRace(final List<Driver> drv, final int laps) {
        Car actualCar;
        final Map<Driver, Position> pos = new HashMap<>();
        this.rank = new RankingManagerImpl(this.map.values().stream().collect(Collectors.toList()));
        int x = circuit.getTrackLengthByTrackNumber(circuit.getStartLine());
        int y = 0;
        for (final Driver elem : drv) {
            actualCar = this.findCarByDriver(elem);
            actualCar.resetCar(laps + 1);
            actualCar.setPosition(new PositionImpl(x, y, circuit.getStartLine()));
            x--;
            y = y == 0 ? circuit.getTrackWidthByTrackNumber(circuit.getStartLine()) - 1 : 0;
            pos.put(elem, actualCar.getPosition());
        }
        this.map.values().forEach(elem -> rank.update(elem));
        return pos;
    }

    @Override
    public void setInitialTyre(final Driver drv, final TyreType tyre) {
        this.findCarByDriver(drv).setInitialType(tyre);
    }
    @Override
    public TyreType getTyreType(final Driver drv) {
       return findCarByDriver(drv).getTyreType();
    }

    @Override
    public double getDeg(final Driver drv) {
        return findCarByDriver(drv).getDeg();
    }

    @Override
    public Position getPosition(final Driver drv) {
        return findCarByDriver(drv).getPosition();
    }

    @Override
    public int trowDice(final Driver drv) throws CanBoxException {
        final Car actualCar = findCarByDriver(drv);
        final int min = actualCar.getDiceNumber().getX();
        final int max = actualCar.getDiceNumber().getY();
        final int randomNumber = new Random().nextInt(max - min) + 1 + min;
        if (this.canBox(randomNumber, actualCar)) {
            throw new CanBoxException(randomNumber);
        }
        return randomNumber;
    }

    @Override
    public Position move(final Driver drv, final Direction dir, final int movement) throws EndRaceException, BlockException, CrashException {
        final Car actualCar = findCarByDriver(drv);
        if (actualCar.getLapsRemaining() == 0) {
            throw new IllegalArgumentException();
        }
        if (actualCar.getPosition().equals(END_POS)) {
            actualCar.setPosition(
            new PositionImpl(0, circuit.getTrackWidthByTrackNumber(0) / 2, CircuitImpl.INITIAL_TRACK));
        }
        int y = actualCar.getPosition().getY();
        int newMovement = movement;
        if (dir != Direction.STRAIGHT) {
            if (!this.dirClear(actualCar, dir) || dir == Direction.BOX) {
                throw new IllegalArgumentException();
            } else {
                newMovement--;
                if (dir == Direction.LEFT) {
                    y--;
                } else {
                    y++;
                }
                actualCar.move(0, y);
            }
        }
        Car carAhead = actualCar;
        if (this.rank != null) {
            carAhead = circuit.prevCarAligned(actualCar, this.map.values()
                                                                 .stream()
                                                                 .collect(Collectors.toList()));
        }
        newMovement = this.updateMovement(actualCar, carAhead, newMovement);
        Position pos = null;
        BlockException block = null;
        try {
             pos = check(newMovement, y, actualCar);
        } catch (BlockException e) {
             block = e;
        }
        if (rank != null) {
            rank.update(actualCar);
        } 
        try {
        crashManager.checkCrash(actualCar, y > 0, y < circuit.getTrackWidthByCar(actualCar) - 1);
        } catch (CrashException e) {
            e.getCrashMap().keySet().stream()
                                    .map(elem -> this.findCarByDriver(elem))
                                    .filter(x -> x.isRetired())
                                    .forEach(z -> this.rank.carRetired(z));
            throw e;
        }
        if (block != null) {
            throw block;
        }
        return pos;
    }
    @Override
    public void box(final Driver drv, final TyreType tyre) {
        final Car actualCar = this.findCarByDriver(drv);
        if (circuit.isBoxTrack(actualCar)) {
            try {
                crashManager.checkCrash(actualCar, false, false);
            } catch (CrashException e) {
                System.out.println("This will never happen");
            }
            actualCar.box(tyre);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean lapInBox(final Driver drv) throws EndRaceException {
        final Car actualCar = this.findCarByDriver(drv);
        if (actualCar.decLapsInBox()) {
           final Position boxExit = new PositionImpl(1,
                   circuit.getTrackWidthByTrackNumber(CircuitImpl.INITIAL_TRACK) - 1,
                   CircuitImpl.INITIAL_TRACK);
            for (final Car elem : this.map.values()) {
                if (elem.getPosition().equals(boxExit)) {
                    actualCar.notOutOfBox();
                    return false;
                }
            }
            actualCar.lapEnd();
            actualCar.setPosition(boxExit);
            try {
                crashManager.checkCrash(actualCar, true, false);
            } catch (CrashException e) {
                System.out.println("This will never happen");
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Driver> getPlacement() {
        return this.rank.getRank();
    }

    @Override
    public Pair<Boolean, Boolean> checkDirections(final Driver drv) {
        final Car actualCar = findCarByDriver(drv);
        if (actualCar.getPosition().equals(END_POS) || actualCar.getPosition().equals(BOX_POS)) {
            return new Pair<>(false, false);
        }
        return new Pair<>(this.dirClear(actualCar, Direction.LEFT), this.dirClear(actualCar, Direction.RIGHT));
    }

    @Override
    public int getLapsRemainingByDriver(final Driver drv) {
        return findCarByDriver(drv).getLapsRemaining();
    }

    @Override
    public int getCarFrameByDriver(final Driver drv) {
        return this.map.get(drv).getCarFrame();
    }

    private Position check(final int x, final int y, final Car car) throws EndRaceException, BlockException {
        if (hasToChangeTrack(x, car)) {
            int newMvt = car.getPosition().getX() + x;
           do {
                newMvt -= circuit.getTrackLengthByCar(car);
                if (circuit.isAtFinishLine(car)) {
                    car.lapEnd();
                } else {
                    car.changeTrack();
                }
                final int track = car.getPosition().getTrackNumber();
                if (circuit.isATurn(track) 
                            && newMvt > circuit.getTrackLengthByTrackNumber(track)) {
                        car.tyreDeg();
                        final Position blockPos = car.move(circuit.getTrackLengthByCar(car), y);
                        throw new BlockException(blockPos);
                }
            } while (newMvt > circuit.getTrackLengthByCar(car)); 
            return car.move(newMvt, y);
        } else {
            return car.move(x, y);
        }
    }

    private Car findCarByDriver(final Driver driver) {
        if (this.map.get(driver) != null) {
                return this.map.get(driver);
            }
        throw new NoSuchElementException();
    }

    private int updateMovement(final Car car, final Car carAhead, final int movement) {
        int newMovement = movement;
        if (!carAhead.equals(car)) {
            int distance = carAhead.getPosition().getX() - car.getPosition().getX();
            int trackX = car.getPosition().getTrackNumber();
            int trackY = carAhead.getPosition().getTrackNumber();
            if (distance != 0 || trackX != trackY) {
                distance--;
                int lapX = car.getLapsRemaining();
                final int lapY = carAhead.getLapsRemaining();
                if (lapX >= lapY) {
                    while (lapX > lapY && trackX != trackY) {
                        distance += circuit.getTrackLengthByTrackNumber(trackX);
                        if (trackX == circuit.getTotalTracks() - 1) {
                            lapX--;
                            trackX = CircuitImpl.INITIAL_TRACK;
                        } else {
                            trackX++;
                        }
                    }
                    while (trackX < trackY) {
                        distance += circuit.getTrackLengthByTrackNumber(trackX);
                        trackX++;
                    }
                } else {
                    while (lapY > lapX && trackX != trackY) {
                        distance += circuit.getTrackLengthByTrackNumber(trackX);
                        if (trackX == circuit.getTotalTracks() - 1) {
                            lapX++;
                            trackX = CircuitImpl.INITIAL_TRACK;
                        } else {
                            trackX++;
                        }
                    }
                    while (trackY < trackX) {
                        distance += circuit.getTrackLengthByTrackNumber(trackY);
                        trackY++;
                    }
                }
            }
            if (distance < movement) {
                newMovement = distance;
            }
        }
        return newMovement;
    }

    private boolean hasToChangeTrack(final int num, final Car car) {
        return car.getPosition().getX() + num > circuit.getTrackLengthByCar(car);
    }

    private boolean canBox(final int num, final Car car) {
        return circuit.isBoxTrack(car) && hasToChangeTrack(num, car);
    }

    private boolean dirClear(final Car car, final Direction dir) {
        int y = car.getPosition().getY();
        if (dir == Direction.LEFT) {
            if (y == 0) {
                return false;
            }
            y--;
        } else {
            if (y == circuit.getTrackWidthByCar(car) - 1) {
                return false;
            }
            y++;
        }
        for (final Car elem : this.map.values()) {
            if (this.areParallel(car, elem, y)) {
                return false;
            }
        }
        return true;
    }

    private boolean areParallel(final Car car1, final Car car2, final int y) {
        return car2.getPosition().getY() == y 
               && car1.getPosition().getX() == car2.getPosition().getX()
               && car1.getPosition().getTrackNumber() == car2.getPosition().getTrackNumber();
    }

}
