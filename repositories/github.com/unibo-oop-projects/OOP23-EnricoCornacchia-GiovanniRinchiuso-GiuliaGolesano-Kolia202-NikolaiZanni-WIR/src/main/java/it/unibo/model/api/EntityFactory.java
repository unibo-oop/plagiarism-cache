package it.unibo.model.api;

import it.unibo.common.Pair;

/**
 * EntityFactory.
 */
public interface EntityFactory {
    /**
     * Create Felix.
     * 
     * @param pos starting Felix's position.
     * @return the created Felix entity.
     */
    Entity createFelix(Pair<Double, Double> pos);

    /**
     * Create Ralph.
     * 
     * @param pos starting Ralph's position.
     * @return the created Ralph entity.
     */
    Entity createRalph(Pair<Double, Double> pos);

    /**
     * Create the windows.
     * 
     * @param pos starting windows's position.
     * @param state windows state.
     * @return the created windows entity.
     */
    Entity createWindows(Pair<Double, Double> pos, boolean state);

    /**
     * Create the brick.
     * @param pos starting brick's position.
     * @return the created brick entity.
     */
    Entity createBrick(Pair<Double, Double> pos);
    /**
     * create the cake.
     * @param pos starting brick's position.
     * @return the created brick entity.
     */
    Entity createCake(Pair<Double, Double> pos);
    /**
     * create the Bird.
     * @param pos starting brick's position.
     * @return the created brick entity.
     */
    Entity createBird(Pair<Double, Double> pos);
}
