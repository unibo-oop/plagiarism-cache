package pvz.model;

import java.util.List;

import pvz.model.entity.Entity;
import pvz.model.entity.plant.PlantType;
import pvz.utility.Pair;

/**
 * This interface describes the generic Model.<br>
 * Any implementation of the <code>Model</code> should be able to compute and
 * return any useful data regarding the game state as time progresses. The Model
 * refers to a coordinate system where the <b>x-axis</b> points <i>right</i>,
 * and the <b>y-axis</b> <i>downwards</i>.
 */
public interface ModelInterface {

    /**
     * Returns a snapshot of all the entities active in the game.
     * 
     * @return a list of entities
     */
    List<Entity> getEntities();

    /**
     * Tells the model to update its status, moving forward by one tick.
     */
    void update();

    /**
     * Places a plant of type <code>plant</code> at the given x and y positions
     * on the grid.<br>
     * The position has to be a percentage of the world's dimension, ranging
     * from 0 to 1.<br>
     * 
     * @param position
     *            plant position
     * @param plant
     *            the type of plant
     */
    void putPlant(Pair<Double, Double> position, PlantType plant);

    /**
     * Removes the plant at the given position.<br>
     * The position has to be a percentage of the world's dimension, ranging
     * from 0 to 1.
     * 
     * @param position
     *            plant position
     */
    void removePlant(Pair<Double, Double> position);

    /**
     * Tells the model to <i>try</i> and remove the "sun" entity and to collect
     * its resources.<br>
     * The position has to be a percentage of the world's dimension, ranging
     * from 0 to 1.
     * 
     * @param position
     *            sun position
     */
    void harvestEnergy(Pair<Double, Double> position);

    /**
     * Returns the amount of energy currently being stored by the player (as an
     * Integer value).
     * 
     * @return current energy
     */
    int getCurrentEnergy();

    /**
     * Returns a value representing the current status of the game.
     * 
     * @return game status
     */
    GameStatus getStatus();

    /**
     * Returns the level currently being played. The Level is represented by an
     * integer value (ID).
     * 
     * @return current level
     */
    int getCurrentLevel();

}
