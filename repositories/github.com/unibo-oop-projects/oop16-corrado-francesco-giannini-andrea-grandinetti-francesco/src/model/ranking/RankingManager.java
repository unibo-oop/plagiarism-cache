package model.ranking;

import java.util.List;

import model.car.Car;
import utility.Driver;

/**
 * Interface for the ordered list of car present in the game.
 */
public interface RankingManager {

    /**
     * Updates the position in the rank of the car given.
     * @param car the car to update the position of.
     */
    void update(Car car);

    /**
     * Getter.
     * @return the rank of the race
     */
    List<Driver> getRank();

    /**
     * Move the retired car at the end of the rank.
     * @param car the car retired
     */
    void carRetired(Car car);

}