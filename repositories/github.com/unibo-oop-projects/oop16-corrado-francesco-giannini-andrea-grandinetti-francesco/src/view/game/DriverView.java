package view.game;

import utility.Driver;
import utility.Position;
/**
 * Interface to manage position of drivers in the circuit.
 *
 */
public interface DriverView {
    /**
     * move driver in another slot.
     * @param slot is new slot
     * @param width of game panel
     * @param height of game panel
     */
    void moveDriver(Slot slot, double width, double height);
    /**
     * 
     * @return driver of this DriverView
     */
    Driver getDriver();
    /**
     * 
     * @return position of driver
     */
    Position getPosition();
}
