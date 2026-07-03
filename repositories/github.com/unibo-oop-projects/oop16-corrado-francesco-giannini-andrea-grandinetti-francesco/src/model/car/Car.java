package model.car;


import model.exception.EndRaceException;
import utility.Driver;
import utility.Pair;
import utility.Position;
import utility.TyreType;
/**
 * Interface to manage the car strategy during the race an eventual crash or pit stop. 
 * Gives back the actual position of the car.
 */
public interface Car {
    /**
     * Call the function tyreDeg on Tyre interface 
     * that decrease the level of worn out every time the dice is thrown.
     */
    void tyreDeg();
    /**
     * Call the function getDeg on Tyre interface.
     * @return the worn out level.
     */
    double getDeg();
    /**
     * Make the car go to the pitlane and stop for some turns.
     * @param tyre tyre to change into
     */
    void box(TyreType tyre);
    /**
     * Tell the car that an accident has occurred. 
     */
    void crash();
    /**
     * @return actual position of the car.
     */
    Position getPosition();
    /**
     * Move the car by the input coordinate.
     * @param x coordinate of the move (horizontal)
     * @param y coordinate of the move (vertical)
     * @return the new position
     */
    Position move(int x, int y);
    /**
     * 
     * @return the driver
     */
    Driver getDriver();
    /**
     * 
     * @return the type of the tyre.
     */
    TyreType getTyreType();
    /**
     * decrease the lap left for the car to end the race.
     * @throws EndRaceException 
     */
    void lapEnd() throws EndRaceException;
    /**
     * change the track to the next one.
     */
    void changeTrack();
    /**
     * Set the laps.
     * @param laps that the car has to do.
     */
    void setLaps(int laps);
    /**
     * decrease the laps the car has to spend in box.
     * @return if the car is currently out of the box
     */
    boolean decLapsInBox();
    /**
     * Getter.
     * @return the laps the car has yet to do
     */
    int getLapsRemaining();
    /**
     * Getter.
     * @return if the car is in box
     */
    boolean isInBox();
    /**
     * Setter.
     * @param pos the new position
     */
    void setPosition(Position pos);
    /**
     * 
     * @return min and max values for the dice
     */
    Pair<Integer, Integer> getDiceNumber();
    /**
     * Method to manage the exit out of box if there is a car blocking the exit.
     */
    void notOutOfBox();
    /**
     * Method to reset car after qualifying session.
     * @param laps laps of the race
     */
    void resetCar(int laps);
    /**
     * Getter.
     * @return carFrame points which represent the state of the car
     */
    int getCarFrame();
    /**
     * Getter.
     * @return if the car is retired
     */
    boolean isRetired();
    /**
     * Setter.
     * @param tyre initial tyre 
     */
    void setInitialType(TyreType tyre);
}
