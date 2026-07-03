package model.circuit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import model.car.Car;
import utility.Driver;
import utility.Position;

/**
 * Circuit interface implementation.
 *
 */

public class CircuitImpl implements Circuit {
    /**
     * number of the initial track.
     */
    public static final int INITIAL_TRACK = 0;
    private static final int WIN = 3;
    private final int startLine;
    private static final String ERROR_STATE = "The initial track hasn't got the right dimensions";
    private final  List<Track> trackList;

    /**
     * Constructor.
     * @param file the file of the circuit to use
     * @throws FileNotFoundException 
     */
    public CircuitImpl(final BufferedReader file) throws FileNotFoundException {
        this.trackList = CircuitCreatorImpl.getSingleton().createCircuit(file);
        this.startLine = this.trackList.size() - 1;
        if (this.trackList.get(startLine).getLength() < Driver.values().length 
                || this.trackList.get(startLine).getWidth() < 2) {
            throw new IllegalStateException(ERROR_STATE);
        }
    }

    @Override
    public int getTotalTracks() {
        return trackList.size();
    }

    @Override
    public int getTrackWidthByCar(final Car car) {
        return this.trackList.get(car.getPosition().getTrackNumber()).getWidth();
    }
    @Override
    public int getTrackWidthByTrackNumber(final int num) {
        return this.trackList.get(num).getWidth();
    }

    @Override
    public int getTrackLengthByTrackNumber(final int trackNumber) {
        return this.trackList.get(trackNumber).getLength();
    }

    @Override
    public int getTrackLengthByCar(final Car car) {
        return this.getTrackLengthByTrackNumber(car.getPosition().getTrackNumber());
    }

    @Override
    public boolean isATurn(final int trackNumber) {
        return this.trackList.get(trackNumber).isATurn();
    }

    @Override
    public boolean isBoxTrack(final Car car) {
        return car.getPosition().getTrackNumber() == trackList.size() - 2;
    }

    @Override
    public boolean isAtFinishLine(final Car car) {
       return  car.getPosition().getTrackNumber() == startLine;
    }

    @Override
    public int getStartLine() {
        return this.startLine;
    }

    @Override
    public Car prevCarAligned(final Car car, final List<Car> carList) {
        final List<Car> carsAhead = new ArrayList<>();
        int index = 0;
        int count = 0;
        Car prevCar;
        while (count < carList.size()) {
            prevCar = carList.get(index++);
            if (this.areAligned(car, prevCar) && isPhisicallyAhead(prevCar, car) 
                    && !prevCar.equals(car)) {
                carsAhead.add(prevCar);
            }
            if (index == 0) {
                index = carList.size();
            }
          count++;
        }
        if (carsAhead.isEmpty()) {
            return car;
        } else {
            prevCar = carsAhead.get(0);
            for (final Car elem : carsAhead) {
                if (isPhisicallyAhead(prevCar, elem)) {
                    prevCar = elem;
                }
            }
            return prevCar;
        }
    }

    private boolean isPhisicallyAhead(final Car car1, final Car car2) {
        final Position pos1 = car1.getPosition();
        final Position pos2 = car2.getPosition();
        if (car1.getLapsRemaining() == car2.getLapsRemaining()
                && car1.getLapsRemaining() != 0) {
            if (car1.isInBox() || car2.isInBox()) {
                return !car1.isInBox();
            } else if (pos1.getTrackNumber() == pos2.getTrackNumber()) {
                return pos1.getX() >= pos2.getX();
            } else {
                return pos1.getTrackNumber() > pos2.getTrackNumber();
            }
        } else if (car1.getLapsRemaining() != car2.getLapsRemaining()) {
            if (pos1.getTrackNumber() == pos2.getTrackNumber()) {
                return pos1.getX() >= pos2.getX();
            } else if (pos1.getTrackNumber() < WIN 
                    && pos2.getTrackNumber() > this.trackList.size() - WIN - 1) {
                return true;
            }  else if (pos2.getTrackNumber() < WIN
                    && pos1.getTrackNumber() > this.trackList.size() - WIN - 1) {
                return false;
            } else {
                return pos1.getTrackNumber() > pos2.getTrackNumber();
            }
        } else {
            return false;
        }
    }

    private boolean areAligned(final Car car1, final Car car2) {
        final Position pos1 = car1.getPosition();
        final Position pos2 = car2.getPosition();
        return pos1.getY() == pos2.getY();
    }

}
