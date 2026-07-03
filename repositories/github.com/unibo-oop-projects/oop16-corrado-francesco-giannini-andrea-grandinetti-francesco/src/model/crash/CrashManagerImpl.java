package model.crash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.car.Car;
import model.PositionImpl;
import model.exception.CrashException;
import utility.Direction;
import utility.Driver;
import utility.Position;
import utility.Pair;

/**
 * Implementation of CrashManager interface.
 */
public class CrashManagerImpl implements CrashManager {
    private final Map<Car, Pair<Car, Car>> map;
    private final List<Car> crashedCar = new ArrayList<>();

    /**
     * Constructor.
     * @param list list
     */
    public CrashManagerImpl(final List<Car> list) {
        map = new HashMap<>();
        list.forEach(x -> map.put(x, new Pair<>(null, null)));
    }

    @Override
    public void checkCrash(final Car car, final boolean leftClear, final boolean rightClear) throws CrashException {
       this.crashedCar.clear();
        Car checkCar;
        if (leftClear) {
            checkCar = finalCheck(car, Direction.LEFT);
            final Pair<Car, Car> pair = this.map.get(car);
            this.map.put(car, new Pair<>(checkCar, pair.getY()));
        } else {
            final Pair<Car, Car> pair = this.map.get(car);
            this.map.put(car, new Pair<>(null, pair.getY())); 
        }
        if (rightClear) {
            checkCar = finalCheck(car, Direction.RIGHT);
            final Pair<Car, Car> pair = this.map.get(car);
            this.map.put(car, new Pair<>(pair.getX(), checkCar));
        } else {
            final Pair<Car, Car> pair = this.map.get(car);
            this.map.put(car, new Pair<>(pair.getX(), null)); 
        }
        if (this.crashedCar.size() > 0) {
            this.crashedCar.add(car);
            final Map<Driver, Boolean> crashMap = new HashMap<>(); 
            this.crashedCar.forEach(x -> {
                x.crash();
                crashMap.put(x.getDriver(), x.isRetired());
            });
            throw new CrashException(crashMap, car.getPosition());
      }
    }

    private Car checkCarSide(final Car car, final Direction dir) {
        int y =  car.getPosition().getY();
        if (dir == Direction.LEFT) {
            y--;
        } else if (dir ==  Direction.RIGHT) {
            y++;
        } else {
            throw new IllegalArgumentException();
        }
        final Position pos = new PositionImpl(car.getPosition().getX(), y, car.getPosition().getTrackNumber());
        for (final Car elem : map.keySet()) {
            if (elem.getPosition().equals(pos)) {
                return elem;
            }
        }
        return null;
    }

    private Car finalCheck(final Car car, final Direction dir) {
        final Car checkCar = checkCarSide(car, dir);
        if (checkCar != null) {
            Car dirCar;
            final Pair<Car, Car> pair = this.map.get(checkCar);
            if (dir == Direction.LEFT) {
                dirCar = this.map.get(car).getX();
                this.map.put(checkCar, new Pair<>(pair.getX(), car));
            } else {
                dirCar = this.map.get(car).getY(); 
                this.map.put(checkCar, new Pair<>(car, pair.getY()));
            }
            if (checkCar.equals(dirCar)) {
                crashedCar.add(checkCar);
            } 
        }
        return checkCar;
    }
}
