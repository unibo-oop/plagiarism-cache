package model.lane;

import java.util.List;

import model.mod.ModAlreadyPresentException;
import model.mod.ModObstacle;
import model.obstacle.GameObject;

/**
 * Lane interface represents the concept of a lane in the game world.
 * A lane can have obstacles that move at a given speed and mods which do not move but
 * can change parameters of the game itself.
 * There are also different lane types (street lanes or river lanes for example).
 */
public interface Lane {

    /**
     * Move obstacles.
     */
    void update();

    /**
     * @return The type of the lane.
     */
    LaneType getLaneType();

    /**
     * @return a list of obstacles present in the lane.
     */
    List<GameObject> getObstacle();

    /**
     * @return a list of present mods in the lane.
     */
    List<ModObstacle> getMods();

    /**
     * Adds a mod to the lane.
     * @param m The mod to add.
     * @throws ModAlreadyPresentException if trying to add a second mod to a lane.
     */
    void addMod(ModObstacle m) throws ModAlreadyPresentException;

    /**
     * Removes a mod from the lane.
     * @param m The mod to remove.
     * @throws IllegalStateException when mod is not present on the lane.
     */
    void removeMod(ModObstacle m) throws IllegalStateException;

    /**
     * @return the speed of obstacles in the lane. 
     */
    double getSpeed();

    /**
     * Sets the speed of obstacles in the lane.
     * @param speed is the new speed.
     */
    void setSpeed(double speed);

}
