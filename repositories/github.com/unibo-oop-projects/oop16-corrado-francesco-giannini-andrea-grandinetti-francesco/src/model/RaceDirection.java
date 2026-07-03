package model;

import java.util.List;
import java.util.Map;

import model.exception.BlockException;
import model.exception.CanBoxException;
import model.exception.CrashException;
import model.exception.EndRaceException;
import utility.Direction;
import utility.Driver;
import utility.Pair;
import utility.Position;
import utility.TyreType;

/** 
 * Interface that manage the dice throw and the drivers move.
 */
public interface RaceDirection {
    /**
     * Setter.
     * @param drv ordinated drivers' list to start the race 
     * @param laps laps of the race
     * @return a map of drivers and the relative position
     */
    Map<Driver, Position> setDriverForRace(List<Driver> drv, int laps);
    /**
     * Setter.
     * @param drv driver
     * @param tyre tyre to change into
     */
    void setInitialTyre(Driver drv, TyreType tyre);
    /**
     * 
     * @param drv the driver
     * @return the type of the tyre of the driver
     */
    TyreType getTyreType(Driver drv);
    /**
     * Throw the dice.
     * @param drv driver that is about to be moved.
     * @return the number of moves.
     * @throws CanBoxException if the drive can box
     */
    int trowDice(Driver drv) throws CanBoxException;
    /**
     * Moves the driver in the track.
     * @param drv driver that is about to be moved.
     * @param dir the direction the car wants to take
     * @param movement the movement is given by the previous method. 
     * @return the new position of the car
     * @throws EndRaceException if the driver finishes the lap 
     * @throws BlockException if the driver blocks
     * @throws CrashException if some drivers crash
     */
    Position move(Driver drv, Direction dir, int movement) throws EndRaceException, BlockException, CrashException;
    /**
     * Make the driver go to the pitlane and stop for some turns.
     * @param drv driver.
     * @param tyre tyre to change into 
     */
    void box(Driver drv, TyreType tyre);
    /**
     * decrease the laps the driver has to spend in box.
     * @param drv the driver
     * @return if the driver is currently out of the box
     * @throws EndRaceException 
     */
    boolean lapInBox(Driver drv) throws EndRaceException;
    /**
     * Rank of the drivers.
     * @return a set of the position of all drivers. 
     */
    List<Driver> getPlacement();
    /**
     * Check if the driver can move left or right.
     * @param drv the driver
     * @return a pair of boolean that indicates if the car can move left or right
     */
    Pair<Boolean, Boolean> checkDirections(Driver drv);
    /**
     * Get the level of worn out.
     * @param drv the driver
     * @return the actual tyres' level of worn out.
     */
    double getDeg(Driver drv);
    /**
     * Get the remaining laps.
     * @param drv the driver
     * @return the remaining laps
     */
    int getLapsRemainingByDriver(Driver drv);
    /**
     * Getter.
     * @param drv the driver
     * @return carFrame points which represent the state of the driver's car
     */
    int getCarFrameByDriver(Driver drv);
    /**
     * Getter.
     * @param drv the driver
     * @return the driver position
     */
    Position getPosition(Driver drv);
}
