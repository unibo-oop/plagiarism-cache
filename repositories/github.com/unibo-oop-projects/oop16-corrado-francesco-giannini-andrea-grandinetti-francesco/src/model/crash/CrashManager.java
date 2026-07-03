package model.crash;

import model.car.Car;
import model.exception.CrashException;

/**
 * Interface for the management of crashes between cars.
 */
public interface CrashManager {
    /**
     * Check if the car had an accident.
     * @param car the car you want to check
     * @param leftClear if it's possible to use the left
     * @param rightClear if it's possible to use the right
     * @throws CrashException if two or more cars have an accident 
     */
    void checkCrash(Car car, boolean leftClear, boolean rightClear) throws CrashException;

}