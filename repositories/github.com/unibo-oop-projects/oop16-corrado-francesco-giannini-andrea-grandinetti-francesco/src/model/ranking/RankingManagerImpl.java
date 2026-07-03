package model.ranking;

import java.util.List;
import java.util.stream.Collectors;
import model.car.Car;
import utility.Driver;
import utility.Position;

/**
 * Class that implements the rank of the car in the game.
 */
public class RankingManagerImpl implements RankingManager {

    private final List<Car> carList;

    /**
     * Constructor. 
     * @param carList list of cars that are in the game
     */
    public RankingManagerImpl(final List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public void update(final Car car) {
        int index = this.carList.indexOf(car);
        if (index > 0) {
            Car prevCar = this.carList.get(index - 1);
            while (isAhead(car, prevCar) && index > 0) {
                index--;
                if (index > 0) {
                    prevCar = this.carList.get(index - 1);
                }
            }
            this.carList.remove(car);
            this.carList.add(index, car);
        }
    }

    @Override
    public List<Driver> getRank() {
        return this.carList.stream()
                           .map(x -> x.getDriver())
                           .collect(Collectors.toList());
    }

    @Override
    public void carRetired(final Car car) {
        this.carList.remove(car);
        int index = this.carList.size();
        boolean found;
        do {
            index--;
            found = !this.carList.get(index).isRetired();
        } while (!found && index >= 0);
            this.carList.add(index + 1, car);
    }

    private boolean isAhead(final Car car1, final Car car2) {
        final Position pos1 = car1.getPosition();
        final Position pos2 = car2.getPosition();
        if (car1.getLapsRemaining() == car2.getLapsRemaining()
                                    && car1.getLapsRemaining() != 0) {
            if (car1.isInBox() || car2.isInBox()) {
                return car1.isInBox();
            } else if (pos1.getTrackNumber() == pos2.getTrackNumber()) {
                return pos1.getX() >= pos2.getX();
            } else {
                return pos1.getTrackNumber() > pos2.getTrackNumber();
            }
        } else {
            return car1.getLapsRemaining() < car2.getLapsRemaining();
        }
    }
}
